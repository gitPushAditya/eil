## Defining Router:

```dart
import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';

final GoRouter router = GoRouter(
  initialLocation: '/',
  routes: [
    GoRoute(
      path: '/',
      name: 'home',
      builder: (context, state) => const HomeScreen(),
    ),
    GoRoute(
      path: '/settings',
      name: 'settings',
      builder: (context, state) => const SettingsScreen(),
    ),
    GoRoute(
      path: '/profile',
      name: 'profile',
      builder: (context, state) => const ProfileScreen(),
    ),
    GoRoute(
  path: '/profile',
  name: 'profile',
  builder: (context, state) {
    // Get the extra (User object in this case) from the state
    final user = state.extra as User;
    return ProfileScreen(user: user);
  },
)
  ],
  errorBuilder: (context, state) => Scaffold(
    appBar: AppBar(title: const Text('Error')),
    body: const Center(child: Text('Page not found')),
  ),
);
```

Using Router:

```dart
class MyApp extends StatelessWidget {
    @override
    Widget build(BuildContext context) {
      return MaterialApp.router(
        title: 'Your App',
        routerConfig: router,  // Use routerConfig to simplify setup
      );
    }
  }
```

Navigation:

```dart
// Navigate to settings
context.go('/settings');

// Navigate to profile and allow the user to return (push)
context.push('/profile');
```
