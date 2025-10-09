# App File

```dart

class RoutineFlowApp extends ConsumerStatefulWidget {
  /// Default constructor for the application
  const RoutineFlowApp({super.key});

  @override
  ConsumerState<RoutineFlowApp> createState() => _RoutineFlowAppState();
}

class _RoutineFlowAppState extends ConsumerState<RoutineFlowApp>
    with WidgetsBindingObserver {
  // Analytics instance for tracking
  final FirebaseAnalytics _analytics = FirebaseAnalytics.instance;

  // Router configuration
  late final GoRouter _router;

  @override
  void initState() {
    super.initState();

    // Set up the router with analytics observer
    _router = createAppRouter(
      ref: ref,
      observers: [FirebaseAnalyticsObserver(analytics: _analytics)],
    );

    // Register as an observer to detect system theme changes
    WidgetsBinding.instance.addObserver(this);

    // Configure preferred device orientation
    SystemChrome.setPreferredOrientations([
      DeviceOrientation.portraitUp,
      DeviceOrientation.portraitDown,
    ]);

    // Set system UI overlay style for status bar
    _updateSystemUIOverlayStyle();
  }

  @override
  void dispose() {
    // Remove observer when disposing
    WidgetsBinding.instance.removeObserver(this);
    super.dispose();
  }

  @override
  void didChangePlatformBrightness() {
    // Handle system theme changes
    ref.read(themeControllerProvider.notifier).handleSystemThemeChange();
    _updateSystemUIOverlayStyle();
    super.didChangePlatformBrightness();
  }

  // Update system UI style based on current theme
  void _updateSystemUIOverlayStyle() {
    final theme = ref.read(themeControllerProvider);
    final isDark = theme.brightness == Brightness.dark;

    SystemChrome.setSystemUIOverlayStyle(
      isDark
          ? SystemUiOverlayStyle.light.copyWith(
              statusBarColor: Colors.transparent,
              systemNavigationBarColor: theme.colorScheme.background,
              systemNavigationBarIconBrightness: Brightness.light,
            )
          : SystemUiOverlayStyle.dark.copyWith(
              statusBarColor: Colors.transparent,
              systemNavigationBarColor: theme.colorScheme.background,
              systemNavigationBarIconBrightness: Brightness.dark,
            ),
    );
  }

  @override
  Widget build(BuildContext context) {
    // Get the current theme from provider
    final theme = ref.watch(themeControllerProvider);

    // Update system UI when theme changes
    WidgetsBinding.instance.addPostFrameCallback((_) {
      _updateSystemUIOverlayStyle();
    });

    // Build the Material App with the router and theme
    return MaterialApp.router(
      title: 'Routine Flow',
      theme: theme,
      routerConfig: _router,
      debugShowCheckedModeBanner: false,

      // Ensure text scales appropriately for accessibility
      builder: (context, child) {
        return MediaQuery(
          // Limit text scaling for better UI consistency while maintaining accessibility
          data: MediaQuery.of(context).copyWith(
            textScaler: TextScaler.linear(
              (MediaQuery.of(context).textScaler.scale(1.0)).clamp(0.85, 1.3),
            ),
          ),
          child: child!,
        );
      },
    );
  }
}

/// Creates and configures the app router
/// Separated for testing and reusability
GoRouter createAppRouter({
  required WidgetRef ref,
  required List<NavigatorObserver> observers,
}) {
  return AppRouter.create(observers: observers, ref: ref);
}

```

---

Now let's understand what we have done here:

```dart
class RoutineFlowApp extends ConsumerStatefulWidget {
  /// Default constructor for the application
  const RoutineFlowApp({super.key});

  @override
  ConsumerState<RoutineFlowApp> createState() => _RoutineFlowAppState();
}
```

Basic stateful widget setup for the app. It uses `ConsumerStatefulWidget` to allow access to Riverpod's state management features.

---

```dart
// Analytics instance for tracking
  final FirebaseAnalytics _analytics = FirebaseAnalytics.instance;
```

We create an instance of `FirebaseAnalytics` to track user interactions and events within the app. This will be added to the router as an observer which will automatically log page views and other events.

---

```dart
late final GoRouter _router;
```

This line declares a late-initialized variable for the `GoRouter`, which will handle navigation throughout the app.

---

```dart
@override
  void initState() {
    super.initState();

    // Set up the router with analytics observer
    _router = createAppRouter(
      ref: ref,
      observers: [FirebaseAnalyticsObserver(analytics: _analytics)],
    );

    // Register as an observer to detect system theme changes
    WidgetsBinding.instance.addObserver(this);

    // Configure preferred device orientation
    SystemChrome.setPreferredOrientations([
      DeviceOrientation.portraitUp,
      DeviceOrientation.portraitDown,
    ]);

    // Set system UI overlay style for status bar
    _updateSystemUIOverlayStyle();
  }
```

This method initializes the app state:
- It sets up the router with Firebase Analytics observer to track page views.
- It registers the widget as an observer to listen for system theme changes.
- It configures the app to only support portrait orientations.

```dart
/// Creates and configures the app router
/// Separated for testing and reusability
GoRouter createAppRouter({
  required WidgetRef ref,
  required List<NavigatorObserver> observers,
}) {
  return AppRouter.create(observers: observers, ref: ref);
}
```

This function creates and configures the app router, allowing for easier testing and reusability. It uses the `AppRouter` class to set up routes and navigation logic.


---

```dart
 @override
  void dispose() {
    // Remove observer when disposing
    WidgetsBinding.instance.removeObserver(this);
    super.dispose();
  }
```

This method is called when the widget is removed from the widget tree. It removes the observer to prevent memory leaks.

---

```dart
  @override
  void didChangePlatformBrightness() {
    // Handle system theme changes
    ref.read(themeControllerProvider.notifier).handleSystemThemeChange();
    _updateSystemUIOverlayStyle();
    super.didChangePlatformBrightness();
  }
```

This method is called when the platform brightness changes (e.g., switching between light and dark mode). It updates the theme accordingly and refreshes the system UI overlay style.

```dart
// Update system UI style based on current theme
  void _updateSystemUIOverlayStyle() {
    final theme = ref.read(themeControllerProvider);
    final isDark = theme.brightness == Brightness.dark;

    SystemChrome.setSystemUIOverlayStyle(
      isDark
          ? SystemUiOverlayStyle.light.copyWith(
              statusBarColor: Colors.transparent,
              systemNavigationBarColor: theme.colorScheme.surface,
              systemNavigationBarIconBrightness: Brightness.light,
            )
          : SystemUiOverlayStyle.dark.copyWith(
              statusBarColor: Colors.transparent,
              systemNavigationBarColor: theme.colorScheme.surface,
              systemNavigationBarIconBrightness: Brightness.dark,
            ),
    );
  }
```

This method updates the system UI overlay style based on the current theme. It sets the status bar and navigation bar colors and icon brightness according to whether the app is in dark or light mode.

---

```dart
 @override
  Widget build(BuildContext context) {
    // Get the current theme from provider
    final theme = ref.watch(themeControllerProvider);

    // Update system UI when theme changes
    WidgetsBinding.instance.addPostFrameCallback((_) {
      _updateSystemUIOverlayStyle();
    });

    // Build the Material App with the router and theme
    return MaterialApp.router(
      title: 'Routine Flow',
      theme: theme,
      routerConfig: _router,
      debugShowCheckedModeBanner: false,

      // Ensure text scales appropriately for accessibility
      builder: (context, child) {
        return MediaQuery(
          // Limit text scaling for better UI consistency while maintaining accessibility
          data: MediaQuery.of(context).copyWith(
            textScaler: TextScaler.linear(
              (MediaQuery.of(context).textScaler.scale(1.0)).clamp(0.85, 1.3),
            ),
          ),
          child: child!,
        );
      },
    );
  }
```

This method builds the main widget tree for the app:
- It retrieves the current theme from the `themeControllerProvider`.
- It updates the system UI overlay style after the first frame is rendered.
- It returns a `MaterialApp.router` widget that uses the configured router and theme.
- It ensures that text scaling is limited to maintain UI consistency while still being accessible.
- The `MediaQuery` widget is used to apply the text scaling settings.
- The `debugShowCheckedModeBanner` is set to false to remove the debug banner in release builds.
- The `routerConfig` is set to the `_router` instance, which handles navigation throughout the app.

---


