# Router Basics

```
flutter pub add go_router
```

`go_router` is a popular routing package for Flutter that provides an easy-to-use API for managing navigation. Here are the core commands and methods related to `go_router`:

### 1. **`GoRouter` Class**
The `GoRouter` class is used to set up and configure routing for your Flutter app.

- **`GoRouter` Constructor**:
  ```dart
  GoRouter(
    routes: [
      // Define your routes here
    ],
    // Other optional parameters like errorBuilder, redirect, etc.
  );
  ```

### 2. **Route Definitions**
You define routes using `GoRoute` or `ShellRoute`.

- **`GoRoute`**:
  ```dart
  GoRoute(
    path: '/path',
    builder: (BuildContext context, GoRouterState state) {
      return MyPage(); // The widget for this route
    },
    // Optional: Additional parameters like redirect, routes, etc.
  );
  ```

- **`ShellRoute`**:
  ```dart
  ShellRoute(
    navigatorKey: _shellNavigatorKey,
    builder: (BuildContext context, GoRouterState state, Widget child) {
      return ScaffoldWithNavBar(child: child);
    },
    routes: [
      GoRoute(
        path: '/nested',
        builder: (BuildContext context, GoRouterState state) {
          return NestedPage(); // The widget for this nested route
        },
      ),
    ],
  );
  ```

### 3. **Navigating Programmatically**
You can navigate using `GoRouter`'s methods.

- **`GoRouter.of(context).go('/path')`**:
  Navigate to a route, replacing the current history entry.
  ```dart
  GoRouter.of(context).go('/path');
  ```

- **`GoRouter.of(context).push('/path')`**:
  Navigate to a route, adding a new entry to the history stack.
  ```dart
  GoRouter.of(context).push('/path');
  ```

- **`GoRouter.of(context).pop()`**:
  Pop the top route from the navigation stack.
  ```dart
  GoRouter.of(context).pop();
  ```

- **`GoRouter.of(context).refresh()`**:
  Refresh the current route, typically used to reload the page.
  ```dart
  GoRouter.of(context).refresh();
  ```

### 4. **Route Matching and Redirection**
You can define redirects and route matchers for dynamic routing.

- **`redirect` Function**:
  Define a redirection rule in your `GoRouter` configuration.
  ```dart
  GoRouter(
    redirect: (state) {
      if (state.location == '/login') {
        return '/home'; // Redirect to home if already logged in
      }
      return null; // No redirection
    },
  );
  ```

- **`errorBuilder`**:
  Define a custom error page if a route is not found or if an error occurs.
  ```dart
  GoRouter(
    errorBuilder: (context, state) {
      return ErrorPage(); // The widget to show on error
    },
  );
  ```

### 5. **Query Parameters and Path Parameters**
Handle query and path parameters in route builders.

- **Path Parameters**:
  ```dart
  GoRoute(
    path: '/profile/:id',
    builder: (BuildContext context, GoRouterState state) {
      final id = state.params['id'];
      return ProfilePage(id: id); // Use path parameter
    },
  );
  ```

- **Query Parameters**:
  ```dart
  GoRoute(
    path: '/search',
    builder: (BuildContext context, GoRouterState state) {
      final query = state.queryParams['query'];
      return SearchPage(query: query); // Use query parameter
    },
  );
  ```

### 6. **Guarding Routes**
Implement route guards to manage access control.

- **`GoRoute` with `redirect`**:
  ```dart
  GoRoute(
    path: '/protected',
    builder: (BuildContext context, GoRouterState state) {
      return ProtectedPage();
    },
    redirect: (state) {
      final isLoggedIn = // check if user is logged in
      if (!isLoggedIn) {
        return '/login'; // Redirect to login if not logged in
      }
      return null; // No redirection
    },
  );
  ```

### 7. **Custom Transitions**
Customize page transitions between routes.

- **`pageBuilder`**:
  Define custom transitions or page animations.
  ```dart
  GoRoute(
    path: '/animated',
    pageBuilder: (BuildContext context, GoRouterState state) {
      return CustomPage(child: AnimatedPage());
    },
  );
  ```

### 8. **Route Guards**
Implement route guards to protect certain routes.

- **`GoRouter` with `guard`**:
  ```dart
  GoRoute(
    path: '/protected',
    builder: (BuildContext context, GoRouterState state) {
      return ProtectedPage();
    },
    redirect: (state) {
      final isLoggedIn = // check if user is logged in
      if (!isLoggedIn) {
        return '/login'; // Redirect to login if not logged in
      }
      return null; // No redirection
    },
  );
  ```

These commands and configurations should cover most of the common scenarios when working with `go_router`. If you have any specific use cases or questions, feel free to ask!