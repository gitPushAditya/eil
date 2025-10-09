# Theme Provider

Now, last step is to create a `theme_provider.dart` file in the same directory to manage theme switching.

```dart
import 'package:flutter/material.dart';
import 'package:flutter/scheduler.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../services/shared_prefs_service.dart';
import 'app_theme.dart';

/// Class to handle theme state and persistence
class ThemeController extends StateNotifier<ThemeData> {
  // SharedPreferences instance for persistence
  final SharedPreferences _prefs;

  // Current theme mode
  ThemeMode _themeMode = ThemeMode.system;

  // SharedPreferences key for storing theme preference
  static const String _themeModeKey = 'theme_mode';

  /// Constructor initializes with the appropriate theme
  ThemeController(this._prefs) : super(_initTheme(_prefs));

  /// Initialize theme based on saved preferences or system default
  static ThemeData _initTheme(SharedPreferences prefs) {
    final savedThemeMode = prefs.getString(_themeModeKey);
    ThemeMode themeMode;

    if (savedThemeMode == null) {
      themeMode = ThemeMode.system;
    } else {
      themeMode = ThemeMode.values.firstWhere(
            (mode) => mode.toString() == savedThemeMode,
        orElse: () => ThemeMode.system,
      );
    }

    if (themeMode == ThemeMode.system) {
      final brightness =
          SchedulerBinding.instance.platformDispatcher.platformBrightness;
      return brightness == Brightness.dark ? AppTheme.dark() : AppTheme.light();
    } else {
      return themeMode == ThemeMode.dark ? AppTheme.dark() : AppTheme.light();
    }
  }

  /// Get the current theme mode
  ThemeMode get themeMode => _themeMode;

  /// Set the theme mode and update theme data
  Future<void> setThemeMode(ThemeMode mode) async {
    _themeMode = mode;

    // Save to SharedPreferences
    await _prefs.setString(_themeModeKey, mode.toString());

    // Update the theme data
    if (mode == ThemeMode.system) {
      final brightness =
          SchedulerBinding.instance.platformDispatcher.platformBrightness;
      state = brightness == Brightness.dark
          ? AppTheme.dark()
          : AppTheme.light();
    } else {
      state = mode == ThemeMode.dark ? AppTheme.dark() : AppTheme.light();
    }
  }

  /// Toggle between light and dark theme
  Future<void> toggleTheme() async {
    if (_themeMode == ThemeMode.system) {
      // If system, switch to explicitly light or dark based on current state
      final isDark = state.brightness == Brightness.dark;
      await setThemeMode(isDark ? ThemeMode.light : ThemeMode.dark);
    } else {
      // Just toggle between light and dark
      await setThemeMode(
        _themeMode == ThemeMode.dark ? ThemeMode.light : ThemeMode.dark,
      );
    }
  }

  /// Updates theme based on system brightness changes
  void handleSystemThemeChange() {
    if (_themeMode == ThemeMode.system) {
      final brightness =
          SchedulerBinding.instance.platformDispatcher.platformBrightness;
      state = brightness == Brightness.dark
          ? AppTheme.dark()
          : AppTheme.light();
    }
  }
}

/// Provider for the theme controller
final themeControllerProvider =
StateNotifierProvider<ThemeController, ThemeData>((ref) {
  final prefs = ref.watch(SharedPrefsService.sharedPrefsProvider);
  return ThemeController(prefs);
});

/// Provider for the current theme mode
final themeModeProvider = Provider<ThemeMode>((ref) {
  return ref
      .watch(themeControllerProvider.notifier)
      .themeMode;
});

```

---

Let's understand what we have done here:

```dart
enum ThemeMode { system, light, dark }
```

This enum defines the available theme modes: system (default), light, and dark.

---

```dart
class ThemeController extends StateNotifier<ThemeData>
```

This class extends `StateNotifier` to manage the current theme state.

---

```dart
 // SharedPreferences instance for persistence
final SharedPreferences _prefs;

// Current theme mode
ThemeMode _themeMode = ThemeMode.system;

// SharedPreferences key for storing theme preference
static const String _themeModeKey = 'theme_mode';
```

This part initializes the `SharedPreferences` instance and sets the default theme mode to system.

---

```dart
  // Constructor initializes with the appropriate theme
ThemeController
(this._prefs) : super(_initTheme(_prefs));

```

- This is the constructor for your ThemeController class.
- It takes a SharedPreferences instance, which was injected via Riverpod.
- It calls the superclass (StateNotifier<ThemeData>) constructor with the result of _initTheme(_prefs).
- Effect: When a ThemeController is created, it immediately initializes its state (i.e., the current theme) based on
  what is stored in SharedPreferences.

---

```dart

/// Initialize theme based on saved preferences or system default

static ThemeData _initTheme
(
SharedPreferences prefs) {
final savedThemeMode = prefs.getString(_themeModeKey);
ThemeMode themeMode;

if (savedThemeMode == null) {
themeMode = ThemeMode.system;
} else {
themeMode = ThemeMode.values.firstWhere(
(mode) => mode.toString() == savedThemeMode,
orElse: () => ThemeMode.system,
);
}

if (themeMode == ThemeMode.system) {
final brightness =
SchedulerBinding.instance.platformDispatcher.platformBrightness;
return brightness == Brightness.dark ? AppTheme.dark() : AppTheme.light();
} else {
return themeMode == ThemeMode.dark ? AppTheme.dark() : AppTheme.light();
}
}
```

- If nothing is saved in SharedPreferences, it defaults to system mode.
- If a saved theme mode exists, it tries to match it with the ThemeMode enum.
- If the theme mode is system, it checks the current platform brightness and returns the appropriate theme.
- If the theme mode is light or dark, it returns the corresponding theme.

---

```dart
/// Get the current theme mode
ThemeMode get themeMode => _themeMode;
```

- Purpose: This is a getter for the private field _themeMode.
- Effect: Allows other code (widgets, providers) to read the current theme mode (system, light, or dark) from the controller.

---

```dart
Future<void> setThemeMode(ThemeMode mode) async {
    _themeMode = mode;

    // Save to SharedPreferences
    await _prefs.setString(_themeModeKey, mode.toString());

    // Update the theme data
    if (mode == ThemeMode.system) {
      final brightness =
          SchedulerBinding.instance.platformDispatcher.platformBrightness;
      state = brightness == Brightness.dark
          ? AppTheme.dark()
          : AppTheme.light();
    } else {
      state = mode == ThemeMode.dark ? AppTheme.dark() : AppTheme.light();
    }
  }
```

- This method sets the theme mode and updates the theme data accordingly.
- It saves the selected theme mode to SharedPreferences.

---

```dart
/// Updates theme based on system brightness changes
  void handleSystemThemeChange() {
    if (_themeMode == ThemeMode.system) {
      final brightness =
          SchedulerBinding.instance.platformDispatcher.platformBrightness;
      state = brightness == Brightness.dark
          ? AppTheme.dark()
          : AppTheme.light();
    }
  }
```

- This method checks the current system brightness and updates the theme if the theme mode is set to system.

---

```dart

/// Provider for the theme controller
final themeControllerProvider =
    StateNotifierProvider<ThemeController, ThemeData>((ref) {
      final prefs = ref.watch(SharedPrefsService.sharedPrefsProvider);
      return ThemeController(prefs);
    });

/// Provider for the current theme mode
final themeModeProvider = Provider<ThemeMode>((ref) {
  return ref.watch(themeControllerProvider.notifier).themeMode;
});
```

- `themeControllerProvider` is a Riverpod provider that creates an instance of `ThemeController` and provides it to the widget tree.
- `themeModeProvider` is a provider that allows other parts of the app to access the current theme mode without needing to directly interact with the `ThemeController`.

Here are few ways to use the theme provider in your app:

```dart
final theme = ref.watch(themeControllerProvider);
```

```dart
final controller = ref.read(themeControllerProvider.notifier);
controller.setThemeMode(...);
```

---

Note: We will apply this theme in `app.dart` file, to see details, refer to setting up App file.

---