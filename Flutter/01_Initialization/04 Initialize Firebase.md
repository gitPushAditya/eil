
## Install Firebase Tools if not installed 

```
npm install -g firebase-tools
```

## Login to firebase

```
firebase login
```

## Run 

```
flutterfire configure --project=<your-project-name>
```

*Note: If it doesn't recognize flutterfire then add it's path  C:\Users\adity\AppData\Local\Pub\Cache\bin* to PATH variable

---
## Add it to Project

flutterfire will already add a file named `firebase_options.dart` to `lib` folder. 

Create a file `firebase_service.dart` in `lib/core/services` and add these line - 

``` dart
import 'package:firebase_core/firebase_core.dart';  
import 'package:flutter/foundation.dart';  
import '../../firebase_options.dart';  
  
class FirebaseService {  
  static Future<void> initialize() async {  
    await Firebase.initializeApp(  
      options: DefaultFirebaseOptions.currentPlatform,  
    );  
    if (kDebugMode) {  
      print('Firebase initialized successfully');  
    }  }}
```

---

In `main.dart`, use this - 

``` dart
void main() async{  
  WidgetsFlutterBinding.ensureInitialized();  
  await FirebaseService.initialize();  
  runApp(const MyApp());  
}
```
--- 