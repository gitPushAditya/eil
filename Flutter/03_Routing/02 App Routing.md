# App Router

This file will include all the routes and navigation logic for your app.

Create a file `app_router.dart` in `lib/core/routing` and add these lines:

```dart
class AppRouter {  
  static GoRouter create({List<NavigatorObserver>? observers, WidgetRef? ref}) {  
    return GoRouter(  
      routes: [  
        GoRoute(  
          path: AppScreens.home.routeName,  
          builder: (context, state) => const HomeScreen(),  
        ),      
    ],    
);  
}}
```

---

To see initialization of the router, refer to `app.dart` file.