# New Flutter App

## Step 1: Create a flutter project

In your designated folder, open terminal and write

```
flutter create app_name
```

This will create a new directory(folder) with app name.

_Note: You will need to install flutter sdk and android studio before this step. For that, go to the link - https://docs.flutter.dev/get-started/install_

---

## Step 2: Create a basic folder structure

Inside app directory, we will create few folders and files to store our code. (This is a personal preference, you can change the names if needed).

### In root(app name) folder:

- Create a folder named 'assets'. This will store all the assets like images, icons, sounds, videos etc. I will recommend you to create a sub-folder for each one of them as you store them.

### In lib folder:

- Create a dart file named 'main.dart'(if not already there). This will have the main entry function to execute the app.
- Create a dart file named 'app.dart'. This will be the entry point of the app(after main.dart). This is what we will open in main function.
- Create a folder named - 'config'. This will initially store two files - 'env.dart' and 'routes.dart'. 'env.dart' will store environment variables. 'routes.dart' will store all routes to different screens(pages).
- Create a folder named - 'core'. This will contain three sub-folders - 'constants' for storing all the constants, 'global_widgets' for storing widgets that will be used app wide, 'util' for storing all the utilities or helper functions.
- Create a folder named - 'data'. This will contain three sub-folders - 'database' for storing all the database related files, 'models' will contain all the model classes, 'providers' will contain the providers related files which are used to store app state.
- Create a folder named - 'ui'. This will also contain three folders initially - 'screens' for storing all the app screens widgets, 'themes' for storing themes and colors and related data, 'widgets' for storing non-global widgets.

![alt text](image.png)

---

## Step 3: Decide colors for your app

_Note: This is only for initial theme of the app. You can have multiple themes following the same steps._

Initially, you need to decide three colors -

- Background Color
- Foreground Color
- Accent Color

Now, get three shades of each one of them that will look good in your app, don't worry, you can change it later.

You have 9 colors now. Create a new file - 'colors.dart' in themes folder. Inside the file, we will create an abstract class to store our colors.

_colors.dart_

```dart
import 'dart:ui';

abstract final class DarkThemeOne {
  static Color accentPrimary = const Color(0xff6735AC);
  static Color accentSecondary = const Color(0xff7E46D9);
  static Color accentTertiary = const Color(0xff9180F9);
  static Color backgroundPrimary = const Color(0xff1D1C2C);
  static Color backgroundSecondary = const Color(0xff34344C);
  static Color backgroundTertiary = const Color.fromARGB(255, 35, 37, 53);
  static Color foregroundPrimary = const Color.fromARGB(255, 236, 245, 255);
  static Color foregroundSecondary = const Color(0xffD9D9D9);
  static Color foregroundTertiary = const Color.fromARGB(255, 168, 168, 168);
}
```

As you can see, there are multiple ways we can store colors.

- Using hex code - Color(0xff(hex code)). Here ff stands for opacity, ff means 100% visible.
- Using RGB value - Color.fromARGB(Alfa value, Red, Green, Blue). Here Alfa value is same as opacity, 255 is the highest number, means 100% visible.

_I have named the class as 'DarkThemeOne' because the colors are based on dark theme, but you can name them anything you want._

Next, you are also going to need some highlight colors in your app, like red or green. These colors are used rarely, like for alerts or confirmation.

Let's create another abstract class for this in the same file -

```dart
abstract final class HighlightColors {
  static Color highlightRed = const Color(0xffC50000);
  static Color highlightOrange = const Color(0xffC79C00);
  static Color highlightGreen = const Color(0xff3DC000);
  static Color highlightGolden = const Color.fromARGB(255, 216, 170, 17);
}
```

## Step 4: Create App Themes

After deciding colors, it's finally time to decide theme for the entire app.

_Note: Again, this is only an initial theme, you can use same process to create multiple themes._

In theme folder, create another file named - app_themes.dart

```dart
import 'package:flutter/material.dart';

import 'colors.dart';

abstract final class AppThemes {
  static ThemeData darkThemeOne() {
    return ThemeData(
      useMaterial3: true,
      colorScheme: ColorScheme.fromSeed(
        seedColor: DarkThemeOne.accentPrimary,
        brightness: Brightness.dark,
        primary: DarkThemeOne.accentPrimary,
        secondary: DarkThemeOne.accentSecondary,
        tertiary: DarkThemeOne.accentTertiary,
        onPrimaryContainer: DarkThemeOne.foregroundPrimary,
        onSecondaryContainer: DarkThemeOne.foregroundSecondary,
        onTertiaryContainer: DarkThemeOne.foregroundTertiary,
        primaryContainer: DarkThemeOne.backgroundPrimary,
        secondaryContainer: DarkThemeOne.backgroundSecondary,
        tertiaryContainer: DarkThemeOne.backgroundTertiary,
      ),
    );
  }
}
```

As you can see, we are choosing colors from our colors file, so whenever we wanna change a color app wide, all we have to do is change it in colors.dart file.

---

## Step 5: Deciding initial screens.

As we move forward, we are gonna have multiple screens in the app, but it's time to decide few initial screens to show in the app, we can always add more in later. For now, let's say we have three screens.
- Homepage
- Profile
- Settings

Now, in constants folder, create a file named - app_screens.dart  
This file will contain another abstract class to store String values of all the routes for all the pages in the app.

_app_screens.dart_

```dart
abstract final class AppScreens {
  static String homepage = '/';
  static String profile = '/profile';
  static String settings = '/settings';
}
```

Now, we have to add a package called go_router which will help with the navigation from one page to another. And every time we need the address for any page, we can simply use AppScreens.homepage or any other address. 

_'/' in homepage represents the default page of the app_

---

## Step 6: Write base code for all the initial pages.

Now, we need to create dart files for all the screens in screens folder have some base code.  
You can either create a stateful widget or stateless widget based on your need.   

A stateful widget for home page:-

_homepage.dart_

```dart
import 'package:flutter/material.dart';

class Homepage extends StatefulWidget {
  const Homepage({super.key});

  @override
  State<Homepage> createState() => _HomePageState();
}

class _HomepageState extends State<Homepage> {
  @override
  Widget build(BuildContext context) {
    return const Placeholder();
  }
}
```

A stateless widget for settings page:-

```dart
import 'package:flutter/material.dart';

class Settings extends StatelessWidget {
  const Settings({super.key});

  @override
  Widget build(BuildContext context) {
    return const Placeholder();
  }
}
```

---

## Step 7: Add go_router package and routes.

In terminal write the command to add go_router

```
flutter pub add go_router
```

Now, in routes.dart file that already exist in config folder, create a router variable to add routes.

```dart
final router = GoRouter(
  initialLocation: AppScreens.tasks,
  debugLogDiagnostics: true, // TODO : Only for debug purpose, remove it later
  routes: [
    GoRoute(
      name: AppScreens.homepage,
      path: AppScreens.homepage,
      builder: (context, state) => const Homepage(),
    ),
    GoRoute(
      name: AppScreens.profile,
      path: AppScreens.profile,
      builder: (context, state) => const Profile(),
    ),
    GoRoute(
      name: AppScreens.settings,
      path: AppScreens.settings,
      builder: (context, state) => const Settings(),
    ),
  ]
)

```

As you can see, we are accessing the app_screens constants for writing the name and path for each page(Screen) and then in builder, we are directing it to the screen widget we created.  

To navigate to any screen now, all you have to do it write  -

```dart
context.goNamed(AppScreens.homepage) // on any other page
```

## Step 8: Add provider and crate app_state

It's time to create an app wide state initially for theme but you can also use it for storing user name and other information too. 

These info will be stored in local database too, but consider provider as an interaction between database and the app. It takes data from the database and provides it app-wide.

To add it, in terminal write - 

```
flutter pub add provider
```

_app_state.dart_

```dart
class AppState extends ChangeNotifier{
  ThemeData _selectedTheme = AppThemes.darkThemeOne();

  ThemeData get selectedTheme => _selectedTheme;

  void updateSelectedTheme(ThemeData selectedTheme) {
    _selectedTheme = selectedTheme;
    // TODO : Store in local database
    notifyListeners();
  }
}
```

Now, we will use this selectedTheme getter function to select the theme for the app, and when user changes it, we will use update function to update it, and it will be changed app-wide. 

_Note: We haven't stored these data in local database yet(shared preferences). We will do it later._

---


