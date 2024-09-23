```dart
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

ThemeData darkTheme = ThemeData(
  colorScheme: darkColorScheme,
  appBarTheme: AppBarTheme(
    backgroundColor: darkColorScheme.primaryContainer,
    foregroundColor: darkColorScheme.onPrimaryContainer,
    centerTitle: true,
    titleTextStyle: GoogleFonts.roboto(
      textStyle: const TextStyle(
        fontFamily: 'Roboto',
      ),
    ),
    shadowColor: Colors.black,
    elevation: 5,
  ),
  textTheme: GoogleFonts.robotoTextTheme().copyWith(
    displayLarge: GoogleFonts.roboto(
      color: darkColorScheme.onPrimaryContainer,
    ),
    displayMedium: GoogleFonts.roboto(
      color: darkColorScheme.onPrimaryContainer,
    ),
    displaySmall: GoogleFonts.roboto(
      color: darkColorScheme.onPrimaryContainer,
    ),
    headlineLarge: GoogleFonts.roboto(
      color: darkColorScheme.onPrimaryContainer,
    ),
    headlineMedium: GoogleFonts.roboto(
      color: darkColorScheme.onPrimaryContainer,
    ),
    headlineSmall: GoogleFonts.roboto(
      color: darkColorScheme.onPrimaryContainer,
    ),
    titleLarge: GoogleFonts.roboto(
      color: darkColorScheme.onPrimaryContainer,
    ),
    titleMedium: GoogleFonts.roboto(
      color: darkColorScheme.onPrimaryContainer,
    ),
    titleSmall: GoogleFonts.roboto(
      color: darkColorScheme.onPrimaryContainer,
    ),
    bodyLarge: GoogleFonts.roboto(
      color: darkColorScheme.onPrimaryContainer,
    ),
    bodyMedium: GoogleFonts.roboto(
      color: darkColorScheme.onPrimaryContainer,
    ),
    bodySmall: GoogleFonts.roboto(
      color: darkColorScheme.onPrimaryContainer,
    ),
    labelLarge: GoogleFonts.roboto(
      color: darkColorScheme.onPrimaryContainer,
    ),
    labelMedium: GoogleFonts.roboto(
      color: darkColorScheme.onPrimaryContainer,
    ),
    labelSmall: GoogleFonts.roboto(
      color: darkColorScheme.onPrimaryContainer,
    ),
  ),
  iconTheme: IconThemeData(
    color: darkColorScheme.onPrimaryContainer,
  ),
  bottomSheetTheme: BottomSheetThemeData(
    backgroundColor: darkColorScheme.primaryContainer,
  ),
  listTileTheme: ListTileThemeData(
    contentPadding: const EdgeInsets.symmetric(horizontal: 16),
    dense: true,
    tileColor: darkColorScheme.primaryContainer,
    selectedTileColor: darkColorScheme.onTertiaryContainer,
    shape: RoundedRectangleBorder(
      borderRadius: BorderRadius.circular(10.0),
    ),
  ),
  snackBarTheme: SnackBarThemeData(
    backgroundColor: darkColorScheme.primaryContainer,
    contentTextStyle: GoogleFonts.roboto(
      color: darkColorScheme.onPrimaryContainer,
    ),
    elevation: 1,
    behavior: SnackBarBehavior.floating,
    shape: RoundedRectangleBorder(
      borderRadius: BorderRadius.circular(10.0),
    ),
  ),
  drawerTheme: DrawerThemeData(
    backgroundColor: darkColorScheme.primaryContainer,
  ),
  // Other Theme properties
);
```
