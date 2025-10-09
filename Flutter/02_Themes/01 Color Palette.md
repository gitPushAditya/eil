# Color Palette

Decide basic colors for your app and create a file called `color_palette.dart` in `lib/theme/` directory.

```dart
import 'package:flutter/material.dart';

class ColorPalette {
  // Private constructor to prevent instantiation
  ColorPalette._();

  // Primary colors - Teal family
  // Chosen for its calming, focusing properties while remaining vibrant
  static const Color primary = Color(0xFF00897B);
  static const Color primaryLight = Color(0xFF4EBAAA);
  static const Color primaryDark = Color(0xFF005B4F);
  static const Color onPrimary = Colors.white;

  // Secondary colors - Indigo family
  // Provides contrast to primary while maintaining a cohesive palette
  static const Color secondary = Color(0xFF5C6BC0);
  static const Color secondaryLight = Color(0xFF8E99F3);
  static const Color secondaryDark = Color(0xFF26418F);
  static const Color onSecondary = Colors.white;

  // Accent colors for badges, streaks and achievements
  // Warm colors create excitement and reward sensation
  static const Color accent = Color(0xFFFF8A65); // Warm coral
  static const Color accentLight = Color(0xFFFFBB93);
  static const Color accentDark = Color(0xFFC75B39);
  static const Color onAccent = Colors.white;

  // Status colors
  static const Color success = Color(0xFF66BB6A);
  static const Color error = Color(0xFFE57373);
  static const Color warning = Color(0xFFFFB74D);
  static const Color info = Color(0xFF4FC3F7);

  static const Color onSuccess = Colors.white;
  static const Color onError = Colors.white;
  static const Color onWarning = Color(0xFF442C2E);
  static const Color onInfo = Color(0xFF0D3C56);

  // Freeze token specific colors
  static const Color freezeToken = Color(0xFF90CAF9);
  static const Color onFreezeToken = Color(0xFF0D3C56);

  // Text colors - Light theme
  static const Color textPrimaryLight = Color(0xFF212121);
  static const Color textSecondaryLight = Color(0xFF757575);
  static const Color textDisabledLight = Color(0xFFBDBDBD);

  // Text colors - Dark theme
  static const Color textPrimaryDark = Color(0xFFF5F5F5);
  static const Color textSecondaryDark = Color(0xFFB0BEC5);
  static const Color textDisabledDark = Color(0xFF78909C);

  // Background colors - Light theme
  // Subtle off-white reduces eye strain compared to pure white
  static const Color backgroundLight = Color(0xFFF8F9FA);
  static const Color surfaceLight = Colors.white;
  static const Color cardLight = Colors.white;

  // Background colors - Dark theme
  // Not fully black to reduce eye strain and harsh contrast
  static const Color backgroundDark = Color(0xFF121212);
  static const Color surfaceDark = Color(0xFF1E1E1E);
  static const Color cardDark = Color(0xFF2C2C2C);

  // Dividers and borders
  static const Color dividerLight = Color(0xFFE0E0E0);
  static const Color dividerDark = Color(0xFF424242);

  // Timer colors
  static const Color timerRunning = success;
  static const Color timerPaused = warning;
  static const Color timerOvertime = error;

  // Gradient colors for featured items
  static const List<Color> primaryGradient = [primary, primaryLight];
  static const List<Color> accentGradient = [accent, accentLight];
  static const List<Color> streakGradient = [secondary, accent];

  // Custom color for each routine category (for visual distinction)
  static const Map<String, Color> routineCategories = {
    'Health & Fitness': Color(0xFF66BB6A),
    'Productivity & Focus': Color(0xFF5C6BC0),
    'Learning & Personal Growth': Color(0xFF26A69A),
    'Sleep & Rest': Color(0xFF7986CB),
    'Relationships & Social': Color(0xFFBA68C8),
    'Home & Organization': Color(0xFF4DB6AC),
    'Mental Wellbeing': Color(0xFF9575CD),
    'Other': Color(0xFF90A4AE),
  };
}
```

You can add other colors as needed, but this should cover the basic color palette for your app.

---