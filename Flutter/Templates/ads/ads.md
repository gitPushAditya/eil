Integrating ads into your Flutter app can help you monetize your app. You can use various ad networks, but Google AdMob is one of the most popular options for Flutter applications. Here’s how to integrate AdMob ads in your Flutter app:

### Step 1: Create an AdMob Account

1. **Create an AdMob Account**: If you don't already have one, create an account at [Google AdMob](https://admob.google.com/).
2. **Create an App**: After signing in, add your app to the AdMob console and get your Ad Unit IDs for different ad formats (e.g., banner ads, interstitial ads, rewarded ads).

### Step 2: Add the Required Packages

Add the `google_mobile_ads` package to your `pubspec.yaml` file:

```yaml
dependencies:
  flutter:
    sdk: flutter
  google_mobile_ads: ^3.0.0 # Check for the latest version
```

Run `flutter pub get` to install the package.

### Step 3: Configure Android and iOS

#### For Android

1. Open your Android project located in `android/app/build.gradle`.
2. Add the following inside the `android` block:

```gradle
android {
    ...
    defaultConfig {
        ...
        minSdkVersion 21  // Minimum SDK version
    }
}
```

3. In the `android/app/src/main/AndroidManifest.xml`, add the following permissions:

```xml
<manifest>
    <application>
        ...
        <meta-data
            android:name="com.google.android.gms.ads.APPLICATION_ID"
            android:value="YOUR_ADMOB_APP_ID"/> <!-- Replace with your AdMob App ID -->
    </application>
</manifest>
```

#### For iOS

1. Open your iOS project located in `ios/Runner/Info.plist`.
2. Add your AdMob App ID like this:

```xml
<key>GADApplicationIdentifier</key>
<string>YOUR_ADMOB_APP_ID</string> <!-- Replace with your AdMob App ID -->
```

3. Ensure you have the minimum deployment target set to 10.0 or higher.

### Step 4: Initialize Google Mobile Ads

In your main app file, initialize Google Mobile Ads in the `main` function:

```dart
import 'package:flutter/material.dart';
import 'package:google_mobile_ads/google_mobile_ads.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();
  MobileAds.instance.initialize();
  runApp(MyApp());
}
```

### Step 5: Load and Display Ads

#### 1. Banner Ads

To display a banner ad, create a banner ad instance and load it:

```dart
class MyHomePage extends StatefulWidget {
  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  late BannerAd _bannerAd;
  bool _isBannerAdReady = false;

  @override
  void initState() {
    super.initState();
    _loadBannerAd();
  }

  void _loadBannerAd() {
    _bannerAd = BannerAd(
      adUnitId: 'YOUR_BANNER_AD_UNIT_ID', // Replace with your Banner Ad Unit ID
      request: AdRequest(),
      listener: BannerAdListener(
        onAdLoaded: (ad) {
          setState(() {
            _isBannerAdReady = true;
          });
        },
        onAdFailedToLoad: (ad, error) {
          ad.dispose();
        },
      ),
    );
    _bannerAd.load();
  }

  @override
  void dispose() {
    _bannerAd.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('AdMob Example')),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text('Hello, World!'),
            if (_isBannerAdReady)
              Container(
                height: 50,
                child: AdWidget(ad: _bannerAd),
              ),
          ],
        ),
      ),
    );
  }
}
```

#### 2. Interstitial Ads

To show an interstitial ad:

```dart
late InterstitialAd _interstitialAd;

void _loadInterstitialAd() {
  InterstitialAd.load(
    adUnitId: 'YOUR_INTERSTITIAL_AD_UNIT_ID', // Replace with your Interstitial Ad Unit ID
    request: AdRequest(),
    adLoadCallback: InterstitialAdLoadCallback(
      onAdLoaded: (ad) {
        _interstitialAd = ad;
        _interstitialAd.show();
      },
      onAdFailedToLoad: (error) {
        print('Interstitial ad failed to load: $error');
      },
    ),
  );
}
```

You can call `_loadInterstitialAd()` at appropriate points in your app, such as after a button press or when navigating between pages.

#### 3. Rewarded Ads

To implement rewarded ads, follow a similar pattern:

```dart
late RewardedAd _rewardedAd;

void _loadRewardedAd() {
  RewardedAd.load(
    adUnitId: 'YOUR_REWARDED_AD_UNIT_ID', // Replace with your Rewarded Ad Unit ID
    request: AdRequest(),
    adLoadCallback: RewardedAdLoadCallback(
      onAdLoaded: (ad) {
        _rewardedAd = ad;
        _rewardedAd.show(onUserEarnedReward: (ad, reward) {
          print('User earned reward: ${reward.amount}');
        });
      },
      onAdFailedToLoad: (error) {
        print('Rewarded ad failed to load: $error');
      },
    ),
  );
}
```

### Step 6: Testing Ads

To test ads, use test ad unit IDs provided by Google:

- **Banner**: `ca-app-pub-3940256099942544/6300978111`
- **Interstitial**: `ca-app-pub-3940256099942544/1033173712`
- **Rewarded**: `ca-app-pub-3940256099942544/5224354917`

### Step 7: Release Preparation

Before releasing your app, ensure that you replace all test ad unit IDs with your actual ad unit IDs. Additionally, be aware of Google's ad policies to ensure compliance.

### Conclusion

With these steps, you should have a basic integration of Google AdMob ads in your Flutter app. Be sure to refer to the [official documentation](https://pub.dev/packages/google_mobile_ads) for more details and advanced configurations. Let me know if you have any questions or need further assistance!
