# Initialize Shared Preferences

In `core/services` create a file - `shared_prefs_service.dart`. 

```dart
import 'package:flutter_riverpod/flutter_riverpod.dart';  
import 'package:shared_preferences/shared_preferences.dart';  
  
class SharedPrefsService {  
  SharedPrefsService._();  
  
  static final sharedPrefsProvider = Provider<SharedPreferences>((ref) {  
    throw UnimplementedError(  
      'SharedPreferences not initialized. Please ensure you have called SharedPreferences.getInstance() before accessing this provider.',  
    );  });}
```

This will create a riverpod provider for global instance of SharedPreferences.

```dart
 SharedPrefsService._();
```

- A dart class with a private constructor, so it can't be instantiated. It's used as a namespace for static members only.

```dart
static final sharedPrefsProvider = Provider<SharedPreferences>((ref) {
    throw UnimplementedError(
      'SharedPreferences not initialized. Please ensure you have called SharedPreferences.getInstance() before accessing this provider.',
    );
```

- Defines a riverpod provider - `sharedPrefsProvider`.
- We can access provider anywhere using  - `SharedPrefsService.sharedPrefsProvider`.
- Type: Provider<SharedPreferences> - means it provides instance of SharedPreferences.

We need to override this with actual SharedPreferences instance otherwise it will throw an error. 

---

In `core/di` create a file name - `providers.dart`.

```dart
Future<List<Override>> globalProviderOverrides() async {
  final prefs = await SharedPreferences.getInstance();

  return [SharedPrefsService.sharedPrefsProvider.overrideWithValue(prefs)];
}

```

This async function initializes the SharedPreferences instance and overrides the provider with it.

--- 

Now, all that's left is to use this override in our main app file.

Edit your `main.dart` file to include the provider overrides:

```dart
final overrides = await globalProviderOverrides();
  runApp(ProviderScope(overrides: overrides, child: const RoutineFlowApp()));
```

Finally, your `main.dart` should look like this:

```dart
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:routine_flow/core/di/providers.dart';
import 'app.dart';
import 'core/services/firebase_service.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await FirebaseService.initialize();
  final overrides = await globalProviderOverrides();
  runApp(ProviderScope(overrides: overrides, child: const RoutineFlowApp()));
}
```

Now you have successfully initialized SharedPreferences in your Flutter app using Riverpod. You can access the `SharedPreferences` instance anywhere in your app by using the `SharedPrefsService.sharedPrefsProvider`.
And watch it using `ref.watch(SharedPrefsService.sharedPrefsProvider)` in your widgets.
