# App Themes

Now let's create a `theme.dart` file in the same directory to define light and dark themes using the color palette.

```dart
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:routine_flow/core/theme/color_palette.dart';

class AppTheme {
  // Private constructor to prevent instantiation
  AppTheme._();

  // Font families
  static final _baseTextTheme = GoogleFonts.interTextTheme();
  static final _headlineFont = GoogleFonts.montserrat();

  /// Creates a light theme
  static ThemeData light() {
    return ThemeData(
      useMaterial3: true,
      brightness: Brightness.light,
      colorScheme: ColorScheme(
        brightness: Brightness.light,
        primary: ColorPalette.primary,
        onPrimary: ColorPalette.onPrimary,
        secondary: ColorPalette.secondary,
        onSecondary: ColorPalette.onSecondary,
        error: ColorPalette.error,
        onError: ColorPalette.onError,
        surface: ColorPalette.surfaceLight,
        onSurface: ColorPalette.textPrimaryLight,
        surfaceContainerHighest: ColorPalette.cardLight,
        onSurfaceVariant: ColorPalette.textSecondaryLight,
        tertiary: ColorPalette.accent,
        onTertiary: ColorPalette.onAccent,
      ),

      // Typography - Using larger than default sizes and slightly increased
      // letter spacing to improve readability for neurodivergent users
      textTheme: _createTextTheme(
        _baseTextTheme,
        ColorPalette.textPrimaryLight,
        ColorPalette.textSecondaryLight,
        ColorPalette.textDisabledLight,
      ),

      // Card Theme - Slightly elevated with subtle shadows
      cardTheme: CardThemeData(
        color: ColorPalette.cardLight,
        elevation: 2,
        margin: const EdgeInsets.symmetric(vertical: 8, horizontal: 16),
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(16)),
      ),

      // AppBar Theme - Clean, prominent
      appBarTheme: AppBarTheme(
        backgroundColor: ColorPalette.primary,
        foregroundColor: ColorPalette.onPrimary,
        elevation: 0,
        centerTitle: true,
        titleTextStyle: _headlineFont.copyWith(
          fontSize: 20,
          fontWeight: FontWeight.w600,
          color: ColorPalette.onPrimary,
        ),
        systemOverlayStyle: SystemUiOverlayStyle.light,
      ),

      // Button themes - Distinctive and accessible
      elevatedButtonTheme: ElevatedButtonThemeData(
        style: ElevatedButton.styleFrom(
          foregroundColor: ColorPalette.onPrimary,
          backgroundColor: ColorPalette.primary,
          elevation: 3,
          padding: const EdgeInsets.symmetric(horizontal: 24, vertical: 14),
          textStyle: const TextStyle(fontSize: 16, fontWeight: FontWeight.w600),
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(12),
          ),
        ),
      ),

      outlinedButtonTheme: OutlinedButtonThemeData(
        style: OutlinedButton.styleFrom(
          foregroundColor: ColorPalette.primary,
          side: BorderSide(color: ColorPalette.primary, width: 1.5),
          padding: const EdgeInsets.symmetric(horizontal: 24, vertical: 14),
          textStyle: const TextStyle(fontSize: 16, fontWeight: FontWeight.w600),
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(12),
          ),
        ),
      ),

      textButtonTheme: TextButtonThemeData(
        style: TextButton.styleFrom(
          foregroundColor: ColorPalette.primary,
          padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 10),
          textStyle: const TextStyle(fontSize: 16, fontWeight: FontWeight.w600),
          shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(8)),
        ),
      ),

      // Input decoration - Clear focus states and validation styling
      inputDecorationTheme: InputDecorationTheme(
        filled: true,
        fillColor: ColorPalette.surfaceLight,
        contentPadding: const EdgeInsets.symmetric(
          horizontal: 16,
          vertical: 18,
        ),
        border: OutlineInputBorder(
          borderRadius: BorderRadius.circular(12),
          borderSide: BorderSide(color: ColorPalette.dividerLight, width: 1.5),
        ),
        enabledBorder: OutlineInputBorder(
          borderRadius: BorderRadius.circular(12),
          borderSide: BorderSide(color: ColorPalette.dividerLight, width: 1.5),
        ),
        focusedBorder: OutlineInputBorder(
          borderRadius: BorderRadius.circular(12),
          borderSide: BorderSide(color: ColorPalette.primary, width: 2),
        ),
        errorBorder: OutlineInputBorder(
          borderRadius: BorderRadius.circular(12),
          borderSide: BorderSide(color: ColorPalette.error, width: 1.5),
        ),
        labelStyle: TextStyle(
          color: ColorPalette.textSecondaryLight,
          fontSize: 16,
        ),
        hintStyle: TextStyle(
          color: ColorPalette.textDisabledLight,
          fontSize: 16,
        ),
      ),

      // Dialog theme - More rounded and spacious
      dialogTheme: DialogThemeData(
        backgroundColor: ColorPalette.surfaceLight,
        elevation: 5,
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20)),
        titleTextStyle: TextStyle(
          color: ColorPalette.textPrimaryLight,
          fontSize: 20,
          fontWeight: FontWeight.bold,
        ),
        contentTextStyle: TextStyle(
          color: ColorPalette.textSecondaryLight,
          fontSize: 16,
        ),
      ),

      // Bottom sheet theme
      bottomSheetTheme: BottomSheetThemeData(
        backgroundColor: ColorPalette.surfaceLight,
        shape: const RoundedRectangleBorder(
          borderRadius: BorderRadius.vertical(top: Radius.circular(24)),
        ),
      ),

      // Divider theme
      dividerTheme: DividerThemeData(
        color: ColorPalette.dividerLight,
        thickness: 1,
        space: 16,
      ),

      // Tab bar theme
      tabBarTheme: TabBarThemeData(
        labelColor: ColorPalette.primary,
        unselectedLabelColor: ColorPalette.textSecondaryLight,
        indicatorColor: ColorPalette.primary,
        indicatorSize: TabBarIndicatorSize.label,
        labelStyle: const TextStyle(fontSize: 16, fontWeight: FontWeight.w600),
        unselectedLabelStyle: const TextStyle(fontSize: 16),
      ),

      // Chip theme for tags and selections
      chipTheme: ChipThemeData(
        backgroundColor: ColorPalette.primary.withValues(alpha: 0.1),
        disabledColor: ColorPalette.dividerLight,
        selectedColor: ColorPalette.primary,
        secondarySelectedColor: ColorPalette.secondary,
        padding: const EdgeInsets.symmetric(horizontal: 12, vertical: 8),
        labelStyle: TextStyle(color: ColorPalette.primary, fontSize: 14),
        secondaryLabelStyle: TextStyle(
          color: ColorPalette.onSecondary,
          fontSize: 14,
        ),
        brightness: Brightness.light,
      ),

      // Icon theme
      iconTheme: IconThemeData(color: ColorPalette.textPrimaryLight, size: 24),

      // Progress indicator theme
      progressIndicatorTheme: ProgressIndicatorThemeData(
        color: ColorPalette.primary,
        linearTrackColor: ColorPalette.primary.withValues(alpha: 0.2),
        circularTrackColor: ColorPalette.primary.withValues(alpha: 0.2),
      ),

      // Switch theme for toggles
      switchTheme: SwitchThemeData(
        thumbColor: WidgetStateProperty.resolveWith((states) {
          if (states.contains(WidgetState.disabled)) {
            return ColorPalette.textDisabledLight;
          }
          return states.contains(WidgetState.selected)
              ? ColorPalette.primary
              : Colors.grey;
        }),
        trackColor: WidgetStateProperty.resolveWith((states) {
          if (states.contains(WidgetState.disabled)) {
            return ColorPalette.textDisabledLight.withValues(alpha: 0.5);
          }
          return states.contains(WidgetState.selected)
              ? ColorPalette.primary.withValues(alpha: 0.5)
              : Colors.grey.withValues(alpha: 0.5);
        }),
      ),

      // Bottom navigation bar theme
      bottomNavigationBarTheme: BottomNavigationBarThemeData(
        backgroundColor: ColorPalette.surfaceLight,
        elevation: 8,
        selectedItemColor: ColorPalette.primary,
        unselectedItemColor: ColorPalette.textSecondaryLight,
        selectedIconTheme: const IconThemeData(size: 26),
        selectedLabelStyle: const TextStyle(
          fontSize: 14,
          fontWeight: FontWeight.w500,
        ),
        unselectedLabelStyle: const TextStyle(fontSize: 12),
      ),

      // Slider theme for durations and settings
      sliderTheme: SliderThemeData(
        activeTrackColor: ColorPalette.primary,
        inactiveTrackColor: ColorPalette.primary.withValues(alpha: 0.2),
        thumbColor: ColorPalette.primary,
        overlayColor: ColorPalette.primary.withValues(alpha: 0.2),
        trackHeight: 4,
      ),
    );
  }

  /// Creates a dark theme
  static ThemeData dark() {
    return ThemeData(
      useMaterial3: true,
      brightness: Brightness.dark,
      colorScheme: ColorScheme(
        brightness: Brightness.dark,
        primary: ColorPalette.primary,
        onPrimary: ColorPalette.onPrimary,
        secondary: ColorPalette.secondary,
        onSecondary: ColorPalette.onSecondary,
        error: ColorPalette.error,
        onError: ColorPalette.onError,
        surface: ColorPalette.surfaceDark,
        onSurface: ColorPalette.textPrimaryDark,
        surfaceContainerHighest: ColorPalette.cardDark,
        onSurfaceVariant: ColorPalette.textSecondaryDark,
        tertiary: ColorPalette.accent,
        onTertiary: ColorPalette.onAccent,
      ),

      // Typography - Using larger than default sizes and slightly increased
      // letter spacing to improve readability for neurodivergent users
      textTheme: _createTextTheme(
        _baseTextTheme,
        ColorPalette.textPrimaryDark,
        ColorPalette.textSecondaryDark,
        ColorPalette.textDisabledDark,
      ),

      // Card Theme - Slightly elevated with subtle shadows
      cardTheme: CardThemeData(
        color: ColorPalette.cardDark,
        elevation: 2,
        margin: const EdgeInsets.symmetric(vertical: 8, horizontal: 16),
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(16)),
      ),

      // AppBar Theme - Clean, prominent
      appBarTheme: AppBarTheme(
        backgroundColor: ColorPalette.surfaceDark,
        foregroundColor: ColorPalette.textPrimaryDark,
        elevation: 0,
        centerTitle: true,
        titleTextStyle: _headlineFont.copyWith(
          fontSize: 20,
          fontWeight: FontWeight.w600,
          color: ColorPalette.textPrimaryDark,
        ),
        systemOverlayStyle: SystemUiOverlayStyle.dark,
      ),

      // Button themes
      elevatedButtonTheme: ElevatedButtonThemeData(
        style: ElevatedButton.styleFrom(
          foregroundColor: ColorPalette.onPrimary,
          backgroundColor: ColorPalette.primary,
          elevation: 3,
          padding: const EdgeInsets.symmetric(horizontal: 24, vertical: 14),
          textStyle: const TextStyle(fontSize: 16, fontWeight: FontWeight.w600),
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(12),
          ),
        ),
      ),

      outlinedButtonTheme: OutlinedButtonThemeData(
        style: OutlinedButton.styleFrom(
          foregroundColor: ColorPalette.primary,
          side: BorderSide(color: ColorPalette.primary, width: 1.5),
          padding: const EdgeInsets.symmetric(horizontal: 24, vertical: 14),
          textStyle: const TextStyle(fontSize: 16, fontWeight: FontWeight.w600),
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(12),
          ),
        ),
      ),

      textButtonTheme: TextButtonThemeData(
        style: TextButton.styleFrom(
          foregroundColor: ColorPalette.primary,
          padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 10),
          textStyle: const TextStyle(fontSize: 16, fontWeight: FontWeight.w600),
          shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(8)),
        ),
      ),

      // Input decoration
      inputDecorationTheme: InputDecorationTheme(
        filled: true,
        fillColor: ColorPalette.surfaceDark,
        contentPadding: const EdgeInsets.symmetric(
          horizontal: 16,
          vertical: 18,
        ),
        border: OutlineInputBorder(
          borderRadius: BorderRadius.circular(12),
          borderSide: BorderSide(color: ColorPalette.dividerDark, width: 1.5),
        ),
        enabledBorder: OutlineInputBorder(
          borderRadius: BorderRadius.circular(12),
          borderSide: BorderSide(color: ColorPalette.dividerDark, width: 1.5),
        ),
        focusedBorder: OutlineInputBorder(
          borderRadius: BorderRadius.circular(12),
          borderSide: BorderSide(color: ColorPalette.primary, width: 2),
        ),
        errorBorder: OutlineInputBorder(
          borderRadius: BorderRadius.circular(12),
          borderSide: BorderSide(color: ColorPalette.error, width: 1.5),
        ),
        labelStyle: TextStyle(
          color: ColorPalette.textSecondaryDark,
          fontSize: 16,
        ),
        hintStyle: TextStyle(
          color: ColorPalette.textDisabledDark,
          fontSize: 16,
        ),
      ),

      // Dialog theme
      dialogTheme: DialogThemeData(
        backgroundColor: ColorPalette.surfaceDark,
        elevation: 5,
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20)),
        titleTextStyle: TextStyle(
          color: ColorPalette.textPrimaryDark,
          fontSize: 20,
          fontWeight: FontWeight.bold,
        ),
        contentTextStyle: TextStyle(
          color: ColorPalette.textSecondaryDark,
          fontSize: 16,
        ),
      ),

      // Bottom sheet theme
      bottomSheetTheme: BottomSheetThemeData(
        backgroundColor: ColorPalette.surfaceDark,
        shape: const RoundedRectangleBorder(
          borderRadius: BorderRadius.vertical(top: Radius.circular(24)),
        ),
      ),

      // Divider theme
      dividerTheme: DividerThemeData(
        color: ColorPalette.dividerDark,
        thickness: 1,
        space: 16,
      ),

      // Tab bar theme
      tabBarTheme: TabBarThemeData(
        labelColor: ColorPalette.primary,
        unselectedLabelColor: ColorPalette.textSecondaryDark,
        indicatorColor: ColorPalette.primary,
        indicatorSize: TabBarIndicatorSize.label,
        labelStyle: const TextStyle(fontSize: 16, fontWeight: FontWeight.w600),
        unselectedLabelStyle: const TextStyle(fontSize: 16),
      ),

      // Chip theme for tags and selections
      chipTheme: ChipThemeData(
        backgroundColor: ColorPalette.primary.withValues(alpha: 0.2),
        disabledColor: ColorPalette.dividerDark,
        selectedColor: ColorPalette.primary,
        secondarySelectedColor: ColorPalette.secondary,
        padding: const EdgeInsets.symmetric(horizontal: 12, vertical: 8),
        labelStyle: TextStyle(color: ColorPalette.primary, fontSize: 14),
        secondaryLabelStyle: TextStyle(
          color: ColorPalette.onSecondary,
          fontSize: 14,
        ),
        brightness: Brightness.dark,
      ),

      // Icon theme
      iconTheme: IconThemeData(color: ColorPalette.textPrimaryDark, size: 24),

      // Progress indicator theme
      progressIndicatorTheme: ProgressIndicatorThemeData(
        color: ColorPalette.primary,
        linearTrackColor: ColorPalette.primary.withValues(alpha: 0.2),
        circularTrackColor: ColorPalette.primary.withValues(alpha: 0.2),
      ),

      // Switch theme
      switchTheme: SwitchThemeData(
        thumbColor: WidgetStateProperty.resolveWith((states) {
          if (states.contains(WidgetState.disabled)) {
            return ColorPalette.textDisabledDark;
          }
          return states.contains(WidgetState.selected)
              ? ColorPalette.primary
              : Colors.grey;
        }),
        trackColor: WidgetStateProperty.resolveWith((states) {
          if (states.contains(WidgetState.disabled)) {
            return ColorPalette.textDisabledDark.withValues(alpha: 0.5);
          }
          return states.contains(WidgetState.selected)
              ? ColorPalette.primary.withValues(alpha: 0.5)
              : Colors.grey.withValues(alpha: 0.5);
        }),
      ),

      // Bottom navigation bar theme
      bottomNavigationBarTheme: BottomNavigationBarThemeData(
        backgroundColor: ColorPalette.surfaceDark,
        elevation: 8,
        selectedItemColor: ColorPalette.primary,
        unselectedItemColor: ColorPalette.textSecondaryDark,
        selectedIconTheme: const IconThemeData(size: 26),
        selectedLabelStyle: const TextStyle(
          fontSize: 14,
          fontWeight: FontWeight.w500,
        ),
        unselectedLabelStyle: const TextStyle(fontSize: 12),
      ),

      // Slider theme
      sliderTheme: SliderThemeData(
        activeTrackColor: ColorPalette.primary,
        inactiveTrackColor: ColorPalette.primary.withValues(alpha: 0.2),
        thumbColor: ColorPalette.primary,
        overlayColor: ColorPalette.primary.withValues(alpha: 0.2),
        trackHeight: 4,
      ),
    );
  }

  /// Helper function to create text themes with appropriate colors
  /// Uses larger font sizes and improved spacing for readability
  static TextTheme _createTextTheme(
    TextTheme base,
    Color primaryColor,
    Color secondaryColor,
    Color disabledColor,
  ) {
    return base.copyWith(
      displayLarge: _headlineFont.copyWith(
        fontSize: 32,
        fontWeight: FontWeight.bold,
        letterSpacing: -0.5,
        color: primaryColor,
      ),
      displayMedium: _headlineFont.copyWith(
        fontSize: 28,
        fontWeight: FontWeight.bold,
        letterSpacing: -0.5,
        color: primaryColor,
      ),
      displaySmall: _headlineFont.copyWith(
        fontSize: 24,
        fontWeight: FontWeight.w600,
        letterSpacing: -0.25,
        color: primaryColor,
      ),
      headlineMedium: _headlineFont.copyWith(
        fontSize: 22,
        fontWeight: FontWeight.w600,
        letterSpacing: -0.25,
        color: primaryColor,
      ),
      headlineSmall: _headlineFont.copyWith(
        fontSize: 20,
        fontWeight: FontWeight.w600,
        letterSpacing: -0.25,
        color: primaryColor,
      ),
      titleLarge: base.titleLarge!.copyWith(
        fontSize: 20,
        fontWeight: FontWeight.w600,
        letterSpacing: 0,
        color: primaryColor,
      ),
      titleMedium: base.titleMedium!.copyWith(
        fontSize: 18,
        fontWeight: FontWeight.w500,
        letterSpacing: 0,
        color: primaryColor,
      ),
      titleSmall: base.titleSmall!.copyWith(
        fontSize: 16,
        fontWeight: FontWeight.w500,
        letterSpacing: 0,
        color: primaryColor,
      ),
      bodyLarge: base.bodyLarge!.copyWith(
        fontSize: 16,
        fontWeight: FontWeight.normal,
        letterSpacing: 0.2,
        color: primaryColor,
        height: 1.5,
      ),
      bodyMedium: base.bodyMedium!.copyWith(
        fontSize: 15,
        fontWeight: FontWeight.normal,
        letterSpacing: 0.2,
        color: primaryColor,
        height: 1.5,
      ),
      bodySmall: base.bodySmall!.copyWith(
        fontSize: 14,
        letterSpacing: 0.2,
        color: secondaryColor,
        height: 1.5,
      ),
      labelLarge: base.labelLarge!.copyWith(
        fontSize: 16,
        fontWeight: FontWeight.w500,
        letterSpacing: 0.5,
        color: primaryColor,
      ),
      labelMedium: base.labelMedium!.copyWith(
        fontSize: 14,
        fontWeight: FontWeight.w500,
        letterSpacing: 0.5,
        color: secondaryColor,
      ),
      labelSmall: base.labelSmall!.copyWith(
        fontSize: 12,
        fontWeight: FontWeight.w500,
        letterSpacing: 0.5,
        color: disabledColor,
      ),
    );
  }
}
```

---