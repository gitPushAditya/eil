# App Screens

List all the screens in your app here.

Create a file `app_screens.dart` in `lib/core/routing` and add these lines:

```dart
enum AppScreens {  
  home('/', 'Home'),  
  intro('/intro', 'Intro');  
  
  final String routeName;  
  final String displayName;  
  
  const AppScreens(this.routeName, this.displayName);  
}
```

---