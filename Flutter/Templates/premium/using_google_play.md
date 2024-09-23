To implement a premium version of your app using Google Play Store purchases, you'll want to follow these general steps:

### 1. Integrate Google Play Billing Library

First, you need to integrate the Google Play Billing Library into your Flutter app. You can do this using the `in_app_purchase` plugin or the `flutter_billing` package. Here’s how you can set it up:

#### Step 1: Add Dependencies

Add the `in_app_purchase` package to your `pubspec.yaml` file:

```yaml
dependencies:
  flutter:
    sdk: flutter
  in_app_purchase: ^3.0.0  # Check for the latest version
```

#### Step 2: Configure Billing in Google Play Console

1. **Create a Google Play Developer Account** if you haven't already.
2. **Create your app** in the Google Play Console.
3. Navigate to the **"In-app products"** section under your app's settings and create a **premium product**.
4. Set the product ID (e.g., `premium_version`) and specify the product type (managed product for a one-time purchase).

### 2. Implement In-App Purchases

#### Step 1: Initialize the In-App Purchase

In your app, initialize the in-app purchase service and set up listeners:

```dart
import 'package:in_app_purchase/in_app_purchase.dart';

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  final InAppPurchase _inAppPurchase = InAppPurchase.instance;

  @override
  void initState() {
    super.initState();
    _inAppPurchase.purchaseStream.listen((List<PurchaseDetails> purchaseDetailsList) {
      _listenToPurchaseUpdated(purchaseDetailsList);
    });
  }

  void _listenToPurchaseUpdated(List<PurchaseDetails> purchaseDetailsList) {
    for (PurchaseDetails purchaseDetails in purchaseDetailsList) {
      if (purchaseDetails.status == PurchaseStatus.purchased || 
          purchaseDetails.status == PurchaseStatus.restored) {
        // Verify the purchase and grant premium access
        _grantPremiumAccess();
      } else if (purchaseDetails.status == PurchaseStatus.error) {
        // Handle error
      }
    }
  }

  void _grantPremiumAccess() {
    // Grant premium access to the user (e.g., store in local storage)
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(/* your app */);
  }
}
```

#### Step 2: Initiate Purchase

Create a method to initiate the purchase:

```dart
Future<void> _buyPremium() async {
  final ProductDetailsResponse response = await _inAppPurchase.queryProductDetails(['premium_version'].toSet());
  if (response.notFoundIDs.isNotEmpty) {
    // Handle the case when the product is not found
    return;
  }
  final ProductDetails productDetails = response.productDetails.first;
  final PurchaseParam purchaseParam = PurchaseParam(productDetails: productDetails);
  await _inAppPurchase.buyConsumable(purchaseParam: purchaseParam);
}
```

### 3. Persist User's Purchase

To persist the user's purchase, you can use `shared_preferences` or a local database:

1. **Store the purchase state** when the user successfully makes a purchase:

```dart
import 'package:shared_preferences/shared_preferences.dart';

Future<void> _grantPremiumAccess() async {
  SharedPreferences prefs = await SharedPreferences.getInstance();
  await prefs.setBool('isPremiumUser', true);
}
```

2. **Check the purchase state** when the app starts:

```dart
Future<bool> _isPremiumUser() async {
  SharedPreferences prefs = await SharedPreferences.getInstance();
  return prefs.getBool('isPremiumUser') ?? false;
}
```

### 4. Restore Purchases

If the user reinstalls the app or switches devices, they should be able to restore their purchase. You can call the `queryPastPurchases` method:

```dart
void _restorePurchases() async {
  final QueryPurchaseDetailsResponse response = await _inAppPurchase.queryPastPurchases();
  for (PurchaseDetails purchase in response.pastPurchases) {
    if (purchase.productID == 'premium_version' && 
        (purchase.status == PurchaseStatus.purchased || purchase.status == PurchaseStatus.restored)) {
      _grantPremiumAccess();
    }
  }
}
```

### 5. UI Integration

Integrate the purchase and restore functionalities into your UI:

```dart
ElevatedButton(
  onPressed: _buyPremium,
  child: Text('Buy Premium'),
),
ElevatedButton(
  onPressed: _restorePurchases,
  child: Text('Restore Purchases'),
),
```

### Conclusion

With this setup, users who purchase the premium version through Google Play Store will have their access registered in your app. When they use the same Google account on a different device or after reinstalling the app, they can restore their premium access. 
