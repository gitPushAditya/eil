# Simply Do

A basic to-do app for adding everyday task.  
USP - Simple User Interface with all the basic functionalities.

## Ideas and Concepts:-

1. **Add Task**:- Simple interface to add task.
2. **View Task**:- A clean and organised view of all the tasks.
3. **Checkbox**:- Ability to mark complete or incomplete.
4. **Delete**:- Ability to delete a task.
5. **Edit**:- Ability to eidt a task.
6. **Due Date**:- Add due date and view task based on that. Also have unplanned task(with no due dates).
7. **Priority**:- Ability to set priority of a task.
8. **Reminders/Notification**:- A reminder at the starting of day to remind user for number of task he/she has planned for today. Ability to set reminder for each task.
9. **Recurring Tasks**:- Task can be repeated daily, weekly, monthly or custom days.
10. **Light/Dark Theme**:- Option to switch between multiple themes.
11. **Widgets**:- Widget for home screen.
12. **Timeline**:- A specific tab for all the activities done so far.
13. **Analytics**:- A detailed analytics page.

---

## Planning:-

- **Sidebar** contains User Profile Info(if set) and links to different pages. Those pages will be Tasks(default page), Timeline, Analytics, Profile, Premium, Contact and Other Apps.
- **Task Page** App bar at the top with title in middle and premium option at right. In bottomAppBar, 4 options - My Day, Planned, All, Completed. Floating action button will allow to add new task.
- **My Day** will contain all the tasks due today sorted according to priorities.
- **Planned** will contain all the tasks that is planned for some date. They will be sorted by date with date dropdown tag and then for each date, sorted by priorities.
- **All** will contain all the incomplete tasks, planned or otherwise. First Unplanned with dropdown tag and then planned with date dropdown tag.
- **Completed** will contain all the completed task. Users can mark them incomplete, or clear them all with a clear tag on the top.
- **Timeline** page will contain a timeline of all the activities that are performed. New task added, task completed, marked incomplete etc.
- **Analytics** page will contain a detailed analysis of percentage and number of task completed everyday(graph), average, most productive day, most productive hours(divided in the slot of three hours).
- **Settings** page will contain options related to profiles and settings. These options are Profile Info - Username, email, Notifications options(start of the day time). Email notifications(if possible - for premium), Subscription and Billing,Themes(premium), Database management - Clear all data, Clear all tasks, Clear timeline, clear analytics. Support - Contact.

---

![alt text](<App Wireframe.png>)

## Design and Development

### Step 1: Create a new project - simply_do

Open a designated folder for flutter project and in command prompt type -

```
flutter create simply_do
```

---

### Step 2: Create basic folder structure -

assets file in root folder  
Inside lib folder -  
config - env.dart, routes.dart  
core - constants, global_widgets, utils  
data - database, models, providers  
ui - screens, themes, widgets  
app.dart  
main.dart

---

### Step 3: Create colors.dart in themes folder

Create an abstract class for colors to store all the colors.

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

abstract final class HighlightColors {
  static Color highlightRed = const Color(0xffC50000);
  static Color highlightOrange = const Color(0xffC79C00);
  static Color highlightGreen = const Color(0xff3DC000);
  static Color highlightGolden = const Color.fromARGB(255, 216, 170, 17);
}

```

---

### Step 4: Create app_themes.dart for themes in themes folder

Create another abstract class for themes -

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

---

### Step 5: Create a app_screens.dart file in constants folder

This will store an abstract class for all the string variable for all the screens that will be used.

- tasks
- timeline
- analytics
- settings
- premium
- tutorial
- support
- add-task
- edit-task

_app_screens.dart_

```dart
abstract final class AppScreens {
  static String tasks = '/';
  static String timeline = '/timeline';
  static String analytics = '/analytics';
  static String settings = '/settings';
  static String premium = '/premium';
  static String tutorial = '/tutorial';
  static String contact = '/support';
  static String addTask = '/add-task';
  static String editTask = '/edit-task';
}
```

---

### Step 6: Create basic files for all pages

Create different files in screens folder for all the pages -

- tasks - stateful
- timeline - stateful
- analytics - stateful
- settings - stateless
- premium - stateless
- tutorial - stateless
- support - stateless
- add_task - stateful
- edit_task - stateful

_tasks.dart_

```dart
import 'package:flutter/material.dart';

class TasksScreen extends StatefulWidget {
  const TasksScreen({super.key});

  @override
  State<TasksScreen> createState() => _TasksScreenState();
}

class _TasksScreenState extends State<TasksScreen> {
  @override
  Widget build(BuildContext context) {
    return const Placeholder();
  }
}
```

_timeline.dart_

```dart
import 'package:flutter/material.dart';

class TimelineScreen extends StatefulWidget {
  const TimelineScreen({super.key});

  @override
  State<TimelineScreen> createState() => _TimelineScreenState();
}

class _TimelineScreenState extends State<TimelineScreen> {
  @override
  Widget build(BuildContext context) {
    return const Placeholder();
  }
}
```

_analytics.dart_

```dart
import 'package:flutter/material.dart';

class AnalyticsScreen extends StatefulWidget {
  const AnalyticsScreen({super.key});

  @override
  State<AnalyticsScreen> createState() => _AnalyticsScreenState();
}

class _AnalyticsScreenState extends State<AnalyticsScreen> {
  @override
  Widget build(BuildContext context) {
    return const Placeholder();
  }
}
```

_settings.dart_

```dart
import 'package:flutter/material.dart';

class SettingsScreen extends StatelessWidget {
  const SettingsScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return const Placeholder();
  }
}
```

_premium.dart_

```dart
import 'package:flutter/material.dart';

class PremiumScreen extends StatelessWidget {
  const PremiumScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return const Placeholder();
  }
}
```

_tutorial.dart_

```dart
import 'package:flutter/material.dart';

class TutorialScreen extends StatelessWidget {
  const TutorialScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return const Placeholder();
  }
}
```

_support.dart_

```dart
import 'package:flutter/material.dart';

class SupportScreen extends StatelessWidget {
  const SupportScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return const Placeholder();
  }
}
```

_add_task.dart_

```dart
import 'package:flutter/material.dart';

class AddTaskScreen extends StatefulWidget {
  const AddTaskScreen({super.key});

  @override
  State<AddTaskScreen> createState() => _AddTaskScreenState();
}

class _AddTaskScreenState extends State<AddTaskScreen> {
  @override
  Widget build(BuildContext context) {
    return const Placeholder();
  }
}
```

_edit_task.dart_

```dart
import 'package:flutter/material.dart';

class EditTaskScreen extends StatefulWidget {
  const EditTaskScreen({super.key});

  @override
  State<EditTaskScreen> createState() => _EditTaskScreenState();
}

class _EditTaskScreenState extends State<EditTaskScreen> {
  @override
  Widget build(BuildContext context) {
    return const Placeholder();
  }
}
```

---

### Step 7: Add go_router and routes.

Import router and create router variable in routes.dart in config folder. We already have String constants for all the pages in app_screens.dart

```commandline
flutter pub add go_router
```

_routes.dart_

```dart
import 'package:go_router/go_router.dart';
import 'package:simply_do/ui/screens/add_task.dart';
import 'package:simply_do/ui/screens/analytics.dart';
import 'package:simply_do/ui/screens/edit_task.dart';
import 'package:simply_do/ui/screens/premium.dart';
import 'package:simply_do/ui/screens/settings.dart';
import 'package:simply_do/ui/screens/support.dart';
import 'package:simply_do/ui/screens/tasks.dart';
import 'package:simply_do/ui/screens/timeline.dart';
import 'package:simply_do/ui/screens/tutorial.dart';

import '../core/constants/app_screens.dart';

final router = GoRouter(
  initialLocation: AppScreens.tasks,
  debugLogDiagnostics: true, // TODO : Only for debug purpose, remove it later
  routes: [
    GoRoute(
      name: AppScreens.tasks,
      path: AppScreens.tasks,
      builder: (context, state) => const TasksScreen(),
    ),
    GoRoute(
      name: AppScreens.timeline,
      path: AppScreens.timeline,
      builder: (context, state) => const TimelineScreen(),
    ),
    GoRoute(
      name: AppScreens.analytics,
      path: AppScreens.analytics,
      builder: (context, state) => const AnalyticsScreen(),
    ),
    GoRoute(
      name: AppScreens.settings,
      path: AppScreens.settings,
      builder: (context, state) => const SettingsScreen(),
    ),
    GoRoute(
      name: AppScreens.premium,
      path: AppScreens.premium,
      builder: (context, state) => const PremiumScreen(),
    ),
    GoRoute(
      name: AppScreens.tutorial,
      path: AppScreens.tutorial,
      builder: (context, state) => const TutorialScreen(),
    ),
    GoRoute(
      name: AppScreens.support,
      path: AppScreens.support,
      builder: (context, state) => const SupportScreen(),
    ),
    GoRoute(
      name: AppScreens.addTask,
      path: AppScreens.addTask,
      builder: (context, state) => const AddTaskScreen(),
    ),
    GoRoute(
      name: AppScreens.editTask,
      path: AppScreens.editTask,
      builder: (context, state) => const EditTaskScreen(),
    ),
  ],
);
```

---

### Step 8: Add app_state.dart in providers folder

Import provider and add app_state.dart in provider folder and initially take selected theme and premium subscriber and user_name as state.

```
flutter pub add provider
```

_app_state.dart_

```dart
import 'package:flutter/material.dart';
import 'package:simply_do/ui/themes/app_themes.dart';

class AppState extends ChangeNotifier {
  // User Info

  String _userName = "User";

  String get userName => _userName;

  void updateUserName(String name) {
    _userName = name;
    // TODO : Update in local database
    notifyListeners();
  }

  // Subscription Info

  bool _isPremiumSubscriber = false;

  bool get isPremiumSubscriber => _isPremiumSubscriber;

  void updateSubscription(bool isSubscribed) {
    _isPremiumSubscriber = isSubscribed;
    // TODO : Update in online database
    notifyListeners();
  }

  ThemeData _selectedTheme = AppThemes.darkThemeOne();

  ThemeData get selectedTheme => _selectedTheme;

  void updateSelectedTheme(ThemeData selectedTheme) {
    _selectedTheme = selectedTheme;
    // TODO : Store in local database
    notifyListeners();
  }
}
```

---

### Step 9: Edit app.dart

Add all the required code in app.dart file with Material app, router and provider.

_app.dart_

```dart
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:simply_do/config/routes.dart';
import 'package:simply_do/data/providers/app_state.dart';

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        ChangeNotifierProvider(create: (_) => AppState()),
      ],
      child: Builder(
        builder: (context) {
          final appState = context.watch<AppState>();
          return MaterialApp.router(
            routerConfig: router,
            debugShowCheckedModeBanner: false,
            theme: appState.selectedTheme,
          );
        },
      ),
    );
  }
}
```

---

### Step 10: Edit main.dart

Add app class in main.dart file and run the app once.

_main.dart_

```dart
import 'package:flutter/material.dart';
import 'app.dart';

void main() {
  runApp(
    const MyApp(),
  );
}
```

---

### Step 11: Create text_widgets.dart

Create text_widgets.dart in global_widgets folder. This will contain all the type of text that we will use in our app.

_text_widgets.dart_

```dart
import 'package:flutter/material.dart';

class LargeTitle extends StatelessWidget {
  const LargeTitle(
      {super.key, required this.text, this.textColor, this.textFontSize = 26});

  final String text;
  final Color? textColor;
  final double textFontSize;

  @override
  Widget build(BuildContext context) {
    textColor ?? Theme.of(context).colorScheme.onPrimaryContainer;
    return Text(
      text,
      style: TextStyle(
        color: textColor,
        fontSize: textFontSize,
      ),
    );
  }
}

class BigTitle extends StatelessWidget {
  const BigTitle(
      {super.key, required this.text, this.textColor, this.textFontSize = 24});

  final String text;
  final Color? textColor;
  final double textFontSize;

  @override
  Widget build(BuildContext context) {
    textColor ?? Theme.of(context).colorScheme.onPrimaryContainer;
    return Text(
      text,
      style: TextStyle(
        color: textColor,
        fontSize: textFontSize,
      ),
    );
  }
}

class MediumTitle extends StatelessWidget {
  const MediumTitle(
      {super.key, required this.text, this.textColor, this.textFontSize = 22});

  final String text;
  final Color? textColor;
  final double textFontSize;

  @override
  Widget build(BuildContext context) {
    textColor ?? Theme.of(context).colorScheme.onPrimaryContainer;
    return Text(
      text,
      style: TextStyle(
        color: textColor,
        fontSize: textFontSize,
      ),
    );
  }
}

class SmallTitle extends StatelessWidget {
  const SmallTitle(
      {super.key, required this.text, this.textColor, this.textFontSize = 20});

  final String text;
  final Color? textColor;
  final double textFontSize;

  @override
  Widget build(BuildContext context) {
    textColor ?? Theme.of(context).colorScheme.onPrimaryContainer;
    return Text(
      text,
      style: TextStyle(
        color: textColor,
        fontSize: textFontSize,
      ),
    );
  }
}

class Headline extends StatelessWidget {
  const Headline({
    super.key,
    required this.text,
    this.textColor,
    this.textFontSize = 18,
    this.fontWeight = FontWeight.bold,
  });

  final String text;
  final Color? textColor;
  final double textFontSize;
  final FontWeight fontWeight;

  @override
  Widget build(BuildContext context) {
    textColor ?? Theme.of(context).colorScheme.onPrimaryContainer;
    return Text(
      text,
      style: TextStyle(
        color: textColor,
        fontSize: textFontSize,
        fontWeight: fontWeight,
      ),
    );
  }
}

class BodyLarge extends StatelessWidget {
  const BodyLarge(
      {super.key, required this.text, this.textColor, this.textFontSize = 17});

  final String text;
  final Color? textColor;
  final double textFontSize;

  @override
  Widget build(BuildContext context) {
    textColor ?? Theme.of(context).colorScheme.onPrimaryContainer;
    return Text(
      text,
      style: TextStyle(
        color: textColor,
        fontSize: textFontSize,
      ),
    );
  }
}

class BodySmall extends StatelessWidget {
  const BodySmall(
      {super.key, required this.text, this.textColor, this.textFontSize = 16});

  final String text;
  final Color? textColor;
  final double textFontSize;

  @override
  Widget build(BuildContext context) {
    textColor ?? Theme.of(context).colorScheme.onPrimaryContainer;
    return Text(
      text,
      style: TextStyle(
        color: textColor,
        fontSize: textFontSize,
      ),
    );
  }
}

class SubHeading extends StatelessWidget {
  const SubHeading(
      {super.key, required this.text, this.textColor, this.textFontSize = 15});

  final String text;
  final Color? textColor;
  final double textFontSize;

  @override
  Widget build(BuildContext context) {
    textColor ?? Theme.of(context).colorScheme.onPrimaryContainer;
    return Text(
      text,
      style: TextStyle(
        color: textColor,
        fontSize: textFontSize,
      ),
    );
  }
}

class FootNote extends StatelessWidget {
  const FootNote(
      {super.key, required this.text, this.textColor, this.textFontSize = 13});

  final String text;
  final Color? textColor;
  final double textFontSize;

  @override
  Widget build(BuildContext context) {
    textColor ?? Theme.of(context).colorScheme.onPrimaryContainer;
    return Text(
      text,
      style: TextStyle(
        color: textColor,
        fontSize: textFontSize,
      ),
    );
  }
}

class CaptionBig extends StatelessWidget {
  const CaptionBig(
      {super.key, required this.text, this.textColor, this.textFontSize = 12});

  final String text;
  final Color? textColor;
  final double textFontSize;

  @override
  Widget build(BuildContext context) {
    textColor ?? Theme.of(context).colorScheme.onPrimaryContainer;
    return Text(
      text,
      style: TextStyle(
        color: textColor,
        fontSize: textFontSize,
      ),
    );
  }
}

class CaptionSmall extends StatelessWidget {
  const CaptionSmall(
      {super.key, required this.text, this.textColor, this.textFontSize = 11});

  final String text;
  final Color? textColor;
  final double textFontSize;

  @override
  Widget build(BuildContext context) {
    textColor ?? Theme.of(context).colorScheme.onPrimaryContainer;
    return Text(
      text,
      style: TextStyle(
        color: textColor,
        fontSize: textFontSize,
      ),
    );
  }
}
```

---

### Step 12: Add Svg icon

Add svg package

```commandline
flutter pub add flutter_svg
```

Create another folder - icons inside assets folder and add this svg icon inside it - https://remixicon.com/icon/vip-crown-2-fill

Add assets folder to pubspec.yaml

```yaml
flutter:
  # The following line ensures that the Material Icons font is
  # included with your application, so that you can use the icons in
  # the material Icons class.
  uses-material-design: true

  # To add assets to your application, add an assets section, like this:
  assets:
    - assets/icons/
  #   - images/a_dot_ham.jpeg
```

### Step 13: Create page_state.dart

Create page_state.dart in providers folder to store page state and change the function to change the appearance of selected page.

```dart
import 'package:flutter/cupertino.dart';

import '../../core/constants/app_screens.dart';

class PageState extends ChangeNotifier {
  // Page State

  String _selectedPage = AppScreens.tasks;

  String get selectedPage => _selectedPage;

  void updateSelectedPage(String page) {
    _selectedPage = page;
    notifyListeners();
  }
}
```

---

### Step 14: Add Drawer Widget

Add drawer_widget.dart to global_widgets and add functions to change the pages -

```dart
import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:go_router/go_router.dart';
import 'package:provider/provider.dart';
import 'package:simply_do/core/constants/app_screens.dart';
import 'package:simply_do/core/global_widgets/text_widgets.dart';
import 'package:simply_do/data/providers/app_state.dart';
import 'package:simply_do/ui/themes/colors.dart';

import '../../data/providers/page_state.dart';

class DrawerWidget extends StatelessWidget {
  const DrawerWidget({super.key});

  @override
  Widget build(BuildContext context) {
    var themeColor = Theme.of(context).colorScheme;
    var appState = Provider.of<AppState>(context, listen: false);
    var pageState = Provider.of<PageState>(context, listen: false);
    return Drawer(
      backgroundColor: themeColor.primaryContainer,
      child: ListView(
        padding: const EdgeInsets.all(0),
        children: [
          DrawerHeader(
            decoration: BoxDecoration(
              color: themeColor.secondaryContainer,
            ),
            child: Row(
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                CircleAvatar(
                  backgroundColor: themeColor.tertiaryContainer,
                  radius: 30,
                  child: appState.isPremiumSubscriber
                      ? SvgPicture.asset(
                          'assets/icons/crown.svg',
                          width: 30,
                          fit: BoxFit.fill,
                          colorFilter: ColorFilter.mode(
                            HighlightColors.highlightGolden,
                            BlendMode.srcIn,
                          ),
                        )
                      : const Icon(
                          Icons.person,
                          size: 30,
                        ),
                ),
                const SizedBox(
                  width: 16,
                ),
                Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    MediumTitle(text: appState.displayUserName),
                    SubHeading(
                      text: appState.isPremiumSubscriber
                          ? 'Premium User'
                          : 'Normal USer',
                    )
                  ],
                )
              ],
            ),
          ),
          ListTile(
            title: SmallTitle(
              text: 'Tasks',
              textColor: pageState.selectedPage == AppScreens.tasks
                  ? themeColor.secondary
                  : themeColor.onPrimaryContainer,
            ),
            onTap: () {
              Navigator.pop(context);
              context.goNamed(AppScreens.tasks);
              pageState.updateSelectedPage(AppScreens.tasks);
            },
          ),
          ListTile(
            title: SmallTitle(
              text: 'Timeline',
              textColor: pageState.selectedPage == AppScreens.timeline
                  ? themeColor.secondary
                  : themeColor.onPrimaryContainer,
            ),
            onTap: () {
              Navigator.pop(context);
              context.goNamed(AppScreens.timeline);
              pageState.updateSelectedPage(AppScreens.timeline);
            },
          ),
          ListTile(
            title: SmallTitle(
              text: 'Analytics',
              textColor: pageState.selectedPage == AppScreens.analytics
                  ? themeColor.secondary
                  : themeColor.onPrimaryContainer,
            ),
            onTap: () {
              Navigator.pop(context);
              context.goNamed(AppScreens.analytics);
              pageState.updateSelectedPage(AppScreens.analytics);
            },
          ),
          const Divider(),
          ListTile(
            title: SmallTitle(
              text: 'Settings',
              textColor: pageState.selectedPage == AppScreens.settings
                  ? themeColor.secondary
                  : themeColor.onPrimaryContainer,
            ),
            onTap: () {
              Navigator.pop(context);
              context.goNamed(AppScreens.settings);
              pageState.updateSelectedPage(AppScreens.settings);
            },
          ),
          ListTile(
            title: SmallTitle(
              text: 'Premium',
              textColor: pageState.selectedPage == AppScreens.premium
                  ? themeColor.secondary
                  : themeColor.onPrimaryContainer,
            ),
            onTap: () {
              Navigator.pop(context);
              context.goNamed(AppScreens.premium);
              pageState.updateSelectedPage(AppScreens.premium);
            },
          ),
          ListTile(
            title: SmallTitle(
              text: 'Tutorial',
              textColor: pageState.selectedPage == AppScreens.tutorial
                  ? themeColor.secondary
                  : themeColor.onPrimaryContainer,
            ),
            onTap: () {
              Navigator.pop(context);
              context.goNamed(AppScreens.tutorial);
              pageState.updateSelectedPage(AppScreens.tutorial);
            },
          ),
          ListTile(
            title: SmallTitle(
              text: 'Support',
              textColor: pageState.selectedPage == AppScreens.support
                  ? themeColor.secondary
                  : themeColor.onPrimaryContainer,
            ),
            onTap: () {
              Navigator.pop(context);
              context.goNamed(AppScreens.support);
              pageState.updateSelectedPage(AppScreens.support);
            },
          ),
        ],
      ),
    );
  }
}
```

---

### Step 15: Add Premium button

Add premium_button.dart to global widgets folder to and create premium button and add it to task page.

```dart
import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';
import 'package:go_router/go_router.dart';
import 'package:provider/provider.dart';
import 'package:simply_do/core/constants/app_screens.dart';

import '../../data/providers/app_state.dart';
import '../../ui/themes/colors.dart';

class PremiumButton extends StatelessWidget {
  const PremiumButton({super.key});

  @override
  Widget build(BuildContext context) {
    var appState = Provider.of<AppState>(context, listen: false);
    var themeColor = Theme.of(context).colorScheme;
    return InkWell(
      onTap: () {
        context.goNamed(AppScreens.premium);
      },
      child: CircleAvatar(
        backgroundColor: themeColor.tertiaryContainer,
        radius: 18,
        child: appState.isPremiumSubscriber
            ? SvgPicture.asset(
                'assets/icons/crown.svg',
                width: 20,
                fit: BoxFit.fill,
                colorFilter: ColorFilter.mode(
                  HighlightColors.highlightGolden,
                  BlendMode.srcIn,
                ),
              )
            : const Icon(
                Icons.person,
                size: 20,
              ),
      ),
    );
  }
}
```

### Step 16: Add basic structure to all the pages

Add basic scaffold and drawer and actions - premium_button to all the pages except add_task and edit_task

```dart
import 'package:flutter/material.dart';
import 'package:simply_do/core/global_widgets/drawer_widget.dart';
import 'package:simply_do/core/global_widgets/premium_button.dart';
import 'package:simply_do/core/global_widgets/text_widgets.dart';

import '../../data/providers/app_state.dart';
import '../themes/colors.dart';

class TasksScreen extends StatefulWidget {
  const TasksScreen({super.key});

  @override
  State<TasksScreen> createState() => _TasksScreenState();
}

class _TasksScreenState extends State<TasksScreen> {
  @override
  Widget build(BuildContext context) {
    var themeColor = Theme.of(context).colorScheme;
    return Scaffold(
      backgroundColor: themeColor.primaryContainer,
      appBar: AppBar(
        backgroundColor: themeColor.secondaryContainer,
        title: const BigTitle(
          text: 'Tasks', // Change this for every app
        ),
        centerTitle: true,
        toolbarHeight: 65,
        actions: const [
          PremiumButton(),
          SizedBox(
            width: 14,
          )
        ],
      ),
      drawer: const DrawerWidget(),
      body: SingleChildScrollView(),
    );
  }
}
```

---

### Step 17: Create 4 widgets for tasks body

Create 4 different widgets with simple text in widgets folder, these widgets will serve as body of tasks page.

- my_day.dart
- planned.dart
- all_tasks.dart
- completed.dart

_my_day_widget.dart_

```dart
import 'package:flutter/material.dart';

class MyDayWidget extends StatefulWidget {
  const MyDayWidget({super.key});

  @override
  State<MyDayWidget> createState() => _MyDayWidgetState();
}

class _MyDayWidgetState extends State<MyDayWidget> {
  @override
  Widget build(BuildContext context) {
    return Text('My Day');
  }
}
```

_planned_widget.dart_

```dart
import 'package:flutter/material.dart';

class PlannedWidget extends StatefulWidget {
const PlannedWidget({super.key});

@override
State<PlannedWidget> createState() => \_PlannedWidgetState();
}

class \_PlannedWidgetState extends State<PlannedWidget> {
@override
Widget build(BuildContext context) {
return Text('Planned');
}
}

```

_all_tasks_widget.dart_

```dart
import 'package:flutter/material.dart';

class AllTasksWidget extends StatefulWidget {
  const AllTasksWidget({super.key});

  @override
  State<AllTasksWidget> createState() => _AllTasksWidgetState();
}

class _AllTasksWidgetState extends State<AllTasksWidget> {
  @override
  Widget build(BuildContext context) {
    return Text('All Task');
  }
}
```

_completed_widget.dart_

```dart
import 'package:flutter/material.dart';

class CompletedWidget extends StatefulWidget {
  const CompletedWidget({super.key});

  @override
  State<CompletedWidget> createState() => _CompletedWidgetState();
}

class _CompletedWidgetState extends State<CompletedWidget> {
  @override
  Widget build(BuildContext context) {
    return Text('Completed');
  }
}
```

---

### Step 18: Create tasks_page_state.dart

Create tasks_page_state.dart in providers folder and add variables and functions to track state of tasks page body.

_tasks_page_state.dart_

```dart
import 'package:flutter/cupertino.dart';
import 'package:simply_do/ui/widgets/all_tasks_widget.dart';
import 'package:simply_do/ui/widgets/completed_widget.dart';
import 'package:simply_do/ui/widgets/my_day_widget.dart';
import 'package:simply_do/ui/widgets/planned_widget.dart';

class TasksPageState extends ChangeNotifier {
  int _taskPageSelected = 0;

  int get taskPageSelected => _taskPageSelected;

  Widget get taskPageWidget {
    if (_taskPageSelected == 0) {
      return const MyDayWidget();
    } else if (_taskPageSelected == 1) {
      return const PlannedWidget();
    } else if (_taskPageSelected == 3) {
      return const AllTasksWidget();
    } else if (_taskPageSelected == 4) {
      return const CompletedWidget();
    } else {
      return const MyDayWidget();
    }
  }

  void updateTaskPageSelected(int value) {
    _taskPageSelected = value;
    notifyListeners();
  }
}
```

---

### Step 19: Add Bottom App Bar

Add bottom_app_bar in widgets folder for tasks page.

_bottom_app_bar_widget.dart_

```dart
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:simply_do/data/providers/tasks_page_state.dart';

class BottomAppBarWidget extends StatelessWidget {
  const BottomAppBarWidget({super.key});

  @override
  Widget build(BuildContext context) {
    var taskPageState = Provider.of<TasksPageState>(context);
    return BottomAppBar(
      height: 60,
      padding: EdgeInsets.zero,
      shape: const CircularNotchedRectangle(),
      notchMargin: 10,
      clipBehavior: Clip.antiAlias,
      child: BottomNavigationBar(
        backgroundColor: Theme.of(context).colorScheme.secondaryContainer,
        onTap: (value) {
          // Value 2(3rd item is transparent, only for gap purpose
          if (value != 2) {
            taskPageState.updateTaskPageSelected(value);
          }
        },
        currentIndex: taskPageState.taskPageSelected,
        iconSize: 25,
        type: BottomNavigationBarType.fixed,
        showSelectedLabels: false,
        showUnselectedLabels: false,
        items: const [
          BottomNavigationBarItem(
            icon: Icon(Icons.sunny),
            label: 'My Day',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.calendar_month),
            label: 'Planned',
          ),
          // This is only for gap purpose
          BottomNavigationBarItem(
            icon: Icon(
              Icons.sunny,
              color: Colors.transparent,
            ),
            label: '',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.format_list_bulleted),
            label: 'All Tasks',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.assignment_turned_in),
            label: 'Completed',
          ),
        ],
      ),
    );
  }
}
```

---

### Step 20: Create another screen add_task.dart

Create another screen and add it to screens folder, also add it in screen constant and router variable.

Add a simple scaffold for now with app bar and title and close function.

_add_task.dart_

```dart
import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:simply_do/core/constants/app_screens.dart';
import 'package:simply_do/core/global_widgets/text_widgets.dart';

class AddTaskScreen extends StatefulWidget {
  const AddTaskScreen({super.key});

  @override
  State<AddTaskScreen> createState() => _AddTaskScreenState();
}

class _AddTaskScreenState extends State<AddTaskScreen> {
  @override
  Widget build(BuildContext context) {
    var themeColor = Theme.of(context).colorScheme;
    return Scaffold(
      backgroundColor: themeColor.primaryContainer,
      appBar: AppBar(
        backgroundColor: themeColor.secondaryContainer,
        title: const BigTitle(text: 'Add Task'),
        toolbarHeight: 65,
        centerTitle: true,
        leading: IconButton(
          onPressed: () {
            context.goNamed(AppScreens.tasks);
          },
          icon: const Icon(
            Icons.close,
            size: 30,
          ),
        ),
        actions: [
          IconButton(
            onPressed: () {
              // TODO: Add function to save new task
            },
            icon: const Icon(
              Icons.check,
              size: 30,
            ),
          ),
          const SizedBox(
            width: 6,
          ),
        ],
      ),
    );
  }
}
```

---

### Step 21: Create floating action button

Create floating action button which will direct add_task.dart

_floating_action_button_widget.dart_

```dart
import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:simply_do/core/constants/app_screens.dart';

class FloatingActionButtonWidget extends StatelessWidget {
  const FloatingActionButtonWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return FloatingActionButton(
      backgroundColor: Theme.of(context).colorScheme.primary,
      onPressed: () {
        context.goNamed(AppScreens.addTask);
      },
      shape: const CircleBorder(),
      child: const Icon(Icons.add),
    );
  }
}
```

---

### Step 22: Add everything to Tasks Screen

Add bottom navigation bar, floating action button and body widget to Tasks screen.

_tasks.dart_

```dart
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:simply_do/core/global_widgets/drawer_widget.dart';
import 'package:simply_do/core/global_widgets/premium_button.dart';
import 'package:simply_do/core/global_widgets/text_widgets.dart';
import 'package:simply_do/data/providers/tasks_page_state.dart';
import 'package:simply_do/ui/widgets/bottom_app_bar_widget.dart';
import 'package:simply_do/ui/widgets/floating_action_button_widget.dart';

class TasksScreen extends StatefulWidget {
  const TasksScreen({super.key});

  @override
  State<TasksScreen> createState() => _TasksScreenState();
}

class _TasksScreenState extends State<TasksScreen> {
  @override
  Widget build(BuildContext context) {
    var taskPageState = Provider.of<TasksPageState>(context);
    var themeColor = Theme.of(context).colorScheme;
    return Scaffold(
      backgroundColor: themeColor.primaryContainer,
      appBar: AppBar(
        backgroundColor: themeColor.secondaryContainer,
        title: const BigTitle(
          text: 'Tasks',
        ),
        centerTitle: true,
        toolbarHeight: 65,
        actions: const [
          PremiumButton(),
          SizedBox(
            width: 14,
          )
        ],
      ),
      drawer: const DrawerWidget(),
      body: taskPageState.taskPageWidget,
      bottomNavigationBar: const BottomAppBarWidget(),
      floatingActionButton: const FloatingActionButtonWidget(),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerDocked,
    );
  }
}
```

---

### Step 23: Create Text Widget for Name, Description

Create a text widget named text_widget.dart in widgets folder. It will be used for Task Name and Task Description.

_text_field_widget.dart_

```dart
import 'package:flutter/material.dart';
import 'package:simply_do/core/global_widgets/text_widgets.dart';

class TextFieldWidget extends StatelessWidget {
  const TextFieldWidget({
    super.key,
    required this.textEditingController,
    required this.hintText,
    required this.iconData,
    required this.labelText,
  });

  final TextEditingController textEditingController;
  final String labelText;
  final String hintText;
  final IconData iconData;

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.all(16),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Icon(
            iconData,
            size: 32,
          ),
          const SizedBox(
            width: 32,
          ),
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                MediumTitle(
                  text: labelText,
                  textColor: Theme.of(context).colorScheme.tertiary,
                ),
                const SizedBox(
                  height: 8,
                ),
                TextField(
                  controller: textEditingController,
                  style: TextStyle(
                    fontSize: 18,
                    color: Theme.of(context).colorScheme.onPrimaryContainer,
                  ),
                  decoration: InputDecoration(
                    isDense: true,
                    border: InputBorder.none,
                    hintText: hintText,
                    contentPadding: const EdgeInsets.all(0),
                  ),
                )
              ],
            ),
          )
        ],
      ),
    );
  }
}
```

---

### Step 24: Add Name and Description Widget to add_task screen

In \_AddTaskScreenState class, create two late variables -

```dart
late TextEditingController taskNameController;
late TextEditingController taskDescController;
```

Create an init function to initialize them -

```dart
@override
  void initState() {
    super.initState();
    taskNameController = TextEditingController();
    taskDescController = TextEditingController();
  }
```

Now in body of scaffold, add SingleChildScrollView and a Column within it, and then add TextField Widget for Task Name and Task Description.

```dart
TextFieldWidget(
                textEditingController: taskNameController,
                hintText: 'Add Task Name',
                iconData: Icons.edit,
                labelText: 'Task Name'),
            const Divider(),
            TextFieldWidget(
                textEditingController: taskDescController,
                hintText: 'Add Task Description',
                iconData: Icons.edit_note,
                labelText: 'Task Description'),
            const Divider(),
```

### Step 25: Create a Date Widget

Create a date widget named date_widget.dart in widget folder for adding due date.

Add 'intl' package for date formatting.

```
flutter pub add intl
```

_date_picker_widget.dart_

```dart
import 'package:flutter/material.dart';
import '../../../core/global_widgets/text_widgets.dart';

class DatePickerWidget extends StatelessWidget {
  const DatePickerWidget(
      {super.key,
      required this.labelText,
      required this.iconData,
      required this.dueDate,
      required this.displayText,
      required this.onDueDateChanged});

  final String labelText;
  final IconData iconData;
  final DateTime? dueDate;
  final String displayText;
  final Function onDueDateChanged;

  @override
  Widget build(BuildContext context) {
    var themeColor = Theme.of(context).colorScheme;
    return Container(
      padding: const EdgeInsets.symmetric(vertical: 16, horizontal: 16),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Icon(
            iconData,
            size: 32,
          ),
          const SizedBox(
            width: 32,
          ),
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                MediumTitle(
                  text: labelText,
                  textColor: Theme.of(context).colorScheme.tertiary,
                ),
                const SizedBox(
                  height: 8,
                ),
                InkWell(
                  onTap: () {
                    showDatePicker(
                      context: context,
                      firstDate: DateTime.now(),
                      lastDate: DateTime(3000),
                      builder: (BuildContext context, Widget? child) {
                        return Theme(
                          data: ThemeData(
                            colorScheme: ColorScheme.dark(
                              primary: themeColor.primary,
                              surface: themeColor.tertiaryContainer,
                              onSurface: themeColor.onPrimaryContainer,
                            ),
                          ),
                          child: child!,
                        );
                      },
                    ).then((selectedDate) {
                      if (selectedDate == null) {
                        return;
                      }
                      onDueDateChanged(selectedDate);
                    });
                  },
                  child: BodyLarge(
                    text: displayText,
                  ),
                )
              ],
            ),
          )
        ],
      ),
    );
  }
}
```

---

### Step 26: Add date widget to add_task screen

In \_AddTaskScreenState class, add another variable for storing date

```dart
DateTime? dueDate;
```

Now add date_picker_widget to children of the column, after Task description and divider

```dart
DatePickerWidget(
              labelText: 'Due Date',
              iconData: Icons.today,
              dueDate: dueDate,
              displayText: setDueDateDisplayText(dueDate),
              onDueDateChanged: (selectedDate) {
                setState(
                  () {
                    dueDate = selectedDate;
                  },
                );
              },
            ),
            const Divider(),
```

### Step 27: Create a ListTile Widget for Radio Button

Create a custom list-tile widget in widgets folder that we are going to use multiple times for radio-buttons.

_radio_tile.dart_

```dart
import 'package:flutter/material.dart';
import '../../../core/global_widgets/text_widgets.dart';

class RadioTile<T> extends StatelessWidget {
  final String title;
  final ValueChanged<T?> onChanged;
  final T value;
  final T groupValue;

  const RadioTile(
      {super.key,
      required this.title,
      required this.onChanged,
      required this.value,
      required this.groupValue});

  @override
  Widget build(BuildContext context) {
    var themeColor = Theme.of(context).colorScheme;
    return ListTile(
      title: BodyLarge(
        text: title,
      ),
      contentPadding: const EdgeInsets.symmetric(horizontal: 0),
      onTap: () {
        onChanged(value);
      },
      leading: Radio<T>(
        activeColor: themeColor.tertiary,
        value: value,
        groupValue: groupValue,
        onChanged: onChanged,
      ),
    );
  }
}
```

---

### Step 28: Create a Selection Widget for Priority

Create a Widget with Container that will let user select the Priority of Task.

_priority_selection_widget.dart_

```dart
class PrioritySelectionWidget extends StatelessWidget {
  const PrioritySelectionWidget(
      {super.key,
      required this.iconData,
      required this.labelText,
      required this.priorityDisplayText,
      required this.onPriorityChanged,
      required this.selectedPriority});

  final IconData iconData;
  final String labelText;
  final String priorityDisplayText;
  final PriorityOptions selectedPriority;
  final Function onPriorityChanged;

  @override
  Widget build(BuildContext context) {
    var themeColor = Theme.of(context).colorScheme;
    PriorityOptions pickedPriority = selectedPriority;
    return Container(
      padding: const EdgeInsets.symmetric(vertical: 16, horizontal: 16),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Icon(
            iconData,
            size: 32,
          ),
          const SizedBox(
            width: 32,
          ),
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                MediumTitle(
                  text: labelText,
                  textColor: Theme.of(context).colorScheme.tertiary,
                ),
                const SizedBox(
                  height: 8,
                ),
                InkWell(
                  onTap: () {
                    showDialogBox(context, pickedPriority, themeColor); // We will create this is new step
                  },
                  child: BodyLarge(
                    text: priorityDisplayText,
                  ),
                )
              ],
            ),
          )
        ],
      ),
    );
  }
}
```

---

### Step 29: Write function to show dialog for Priority

In the same PriorityWidgetSelection class, write a function to show dialog box

```dart
void showDialogBox(BuildContext context, PriorityOptions pickedPriority,
      ColorScheme themeColor) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return PopScope(
          onPopInvoked: (_) {
            pickedPriority = selectedPriority;
          },
          child: StatefulBuilder(
            builder: (BuildContext context, StateSetter setState) {
              return AlertDialog(
                backgroundColor: themeColor.tertiaryContainer,
                title: SmallTitle(
                  text: 'Select Priority Type',
                  textColor: themeColor.tertiary,
                ),
                content: SingleChildScrollView(
                  child: Column(
                    children: [
                      for (var option in PriorityOptions.values)
                        RadioTile<PriorityOptions>(
                            title: getPriorityText(option),
                            onChanged: (option) {
                              setState(() {
                                pickedPriority = option!;
                              });
                            },
                            value: option,
                            groupValue: pickedPriority)
                    ],
                  ),
                ),
                actions: [
                  Row(
                    mainAxisAlignment: MainAxisAlignment.end,
                    children: [
                      TextButton(
                        onPressed: () {
                          context.pop();
                        },
                        child: BodySmall(
                          text: 'Cancel',
                          textColor: themeColor.tertiary,
                        ),
                      ),
                      const SizedBox(
                        width: 16,
                      ),
                      TextButton(
                        onPressed: () {
                          onPriorityChanged(pickedPriority);
                          context.pop();
                        },
                        child: BodySmall(
                          text: 'Ok',
                          textColor: themeColor.tertiary,
                        ),
                      ),
                    ],
                  )
                ],
              );
            },
          ),
        );
      },
    );
  }
```

---

### Step 30: Add priority_selection_widget to add_task screen

Create a new variable in \_AddTaskScreenState class for priority options

```dart
PriorityOptions selectedPriority = PriorityOptions.none;
```

Add priority_selection_widget to column after due date and divider.

```dart
PrioritySelectionWidget(
              iconData: Icons.priority_high,
              labelText: 'Set Priority',
              priorityDisplayText: getPriorityText(selectedPriority),
              onPriorityChanged: (value) {
                setState(() {
                  selectedPriority = value;
                });
              },
              selectedPriority: selectedPriority,
            ),
            const Divider(),
```

---

### Step 31: Create Repeat Functions

Create a utility function to add RepeatType enum, DaysOfWeek enum and functions to get their string values.

_repeat_function.dart_

```dart
enum RepeatType { daily, weekly, monthly, yearly, custom }

enum DaysOfWeek {
  monday,
  tuesday,
  wednesday,
  thursday,
  friday,
  saturday,
  sunday
}

String getRepeatTypeString(RepeatType repeatType) {
  switch (repeatType) {
    case RepeatType.daily:
      return 'Repeat Daily';
    case RepeatType.weekly:
      return 'Repeat Weekly';
    case RepeatType.monthly:
      return 'Repeat Monthly';
    case RepeatType.yearly:
      return 'Repeat Yearly';
    case RepeatType.custom:
      return 'Custom Days';
  }
}

String getDaysOfWeekString(DaysOfWeek daysOfWeek) {
  switch (daysOfWeek) {
    case DaysOfWeek.monday:
      return 'M';
    case DaysOfWeek.tuesday:
      return 'T';
    case DaysOfWeek.wednesday:
      return 'W';
    case DaysOfWeek.thursday:
      return 'Th';
    case DaysOfWeek.friday:
      return 'F';
    case DaysOfWeek.saturday:
      return 'Sa';
    case DaysOfWeek.sunday:
      return 'S';
  }
}
```

### Step 32: Create Repeat Widget

Now let's create a Repeat Widget that will allow user to select Repeat Type.

_repeat_selection_widget.dart_

```dart
import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:simply_do/core/global_widgets/text_widgets.dart';
import 'package:simply_do/core/utils/repeat_function.dart';
import 'package:simply_do/ui/widgets/task_form_screen_widgets/radio_tile.dart';

class RepeatSelectionWidget extends StatelessWidget {
  const RepeatSelectionWidget(
      {super.key,
      required this.iconData,
      required this.labelText,
      required this.repeatDisplayText,
      required this.selectedRepeatType,
      required this.customDays,
      required this.setSelectedRepeatType,
      required this.setCustomDays});

  final IconData iconData;
  final String labelText;
  final String repeatDisplayText;
  final RepeatType selectedRepeatType;
  final List<DaysOfWeek> customDays;
  final Function setSelectedRepeatType;
  final Function setCustomDays;

  @override
  Widget build(BuildContext context) {
    var themeColor = Theme.of(context).colorScheme;
    RepeatType pickedRepeatType = selectedRepeatType;
    List<DaysOfWeek> pickedCustomDays = customDays;
    return Container(
      padding: const EdgeInsets.symmetric(vertical: 16, horizontal: 18),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Icon(
            iconData,
            size: 32,
          ),
          const SizedBox(
            width: 32,
          ),
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                MediumTitle(
                  text: labelText,
                  textColor: themeColor.tertiary,
                ),
                const SizedBox(
                  height: 8,
                ),
                InkWell(
                  onTap: () {
                    showDialogBox(context, pickedRepeatType, pickedCustomDays,
                        themeColor); // We will add this in next step
                  },
                  child: BodyLarge(
                    text: repeatDisplayText,
                  ),
                ),
              ],
            ),
          )
        ],
      ),
    );
  }
}
```

---

### Step 33: Create showDialogBox function for Repeat Widget

This will create a dialog box UI and functions that will be shown to the user when they tap to select Repeat Type.

Write this in the same class - RepeatSelectionWidget

```dart
void showDialogBox(BuildContext context, RepeatType pickedRepeatType,
      List<DaysOfWeek> pickedCustomDays, ColorScheme themeColor) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return PopScope(
          onPopInvoked: (_) {
            pickedRepeatType = selectedRepeatType;
            pickedCustomDays = customDays;
          },
          child: StatefulBuilder(
            builder: (BuildContext context, StateSetter setState) {
              return AlertDialog(
                backgroundColor: themeColor.tertiaryContainer,
                contentPadding: const EdgeInsets.all(16),
                actionsPadding:
                    const EdgeInsets.only(top: 0, right: 8, left: 8, bottom: 8),
                title: SmallTitle(
                  text: 'Select Repeat Type',
                  textColor: themeColor.tertiary,
                ),
                content: SingleChildScrollView(
                  child: Column(
                    children: [
                      for (var option in RepeatType.values)
                        RadioTile<RepeatType>(
                            title: getRepeatTypeString(option),
                            onChanged: (option) {
                              setState(() {
                                pickedRepeatType = option!;
                              });
                            },
                            value: option,
                            groupValue: pickedRepeatType),
                      const SizedBox(
                        height: 8,
                      ),
                      Row(
                        children: [
                          // TODO: Add Chip for all the days
                        ],
                      ),
                    ],
                  ),
                ),
                actions: [
                  Row(
                    mainAxisAlignment: MainAxisAlignment.end,
                    children: [
                      TextButton(
                        onPressed: () {
                          context.pop();
                        },
                        child: BodySmall(
                          text: 'Cancel',
                          textColor: themeColor.tertiary,
                        ),
                      ),
                      const SizedBox(
                        width: 16,
                      ),
                      TextButton(
                        onPressed: () {
                          setSelectedRepeatType(pickedRepeatType);
                          setCustomDays(pickedCustomDays);
                          context.pop();
                        },
                        child: BodySmall(
                          text: 'Ok',
                          textColor: themeColor.tertiary,
                        ),
                      ),
                    ],
                  )
                ],
              );
            },
          ),
        );
      },
    );
  }
```

---

### Step 34: Character Chip

Let's create a character chip which which will show each day of the week by using DaysOfWeek enum.

_character_chip.dart_

```dart
import 'package:flutter/material.dart';
import 'package:simply_do/core/global_widgets/text_widgets.dart';

class CharacterChip<T> extends StatelessWidget {
  const CharacterChip(
      {super.key,
      required this.displayText,
      required this.value,
      required this.pickedValues,
      required this.onListChanged});

  final String displayText;
  final T value;
  final List<T> pickedValues;
  final ValueChanged<List<T>> onListChanged;

  @override
  Widget build(BuildContext context) {
    final themeColor = Theme.of(context).colorScheme;
    final itemColor = pickedValues.contains(value)
        ? themeColor.tertiary
        : themeColor.onPrimaryContainer;
    return GestureDetector(
      onTap: () {
        if (pickedValues.contains(value)) {
          pickedValues.remove(value);
        } else {
          pickedValues.add(value);
        }
        onListChanged(pickedValues);
      },
      child: Container(
        margin: const EdgeInsets.all(2),
        child: Chip(
          padding: EdgeInsets.zero,
          label: ConstrainedBox(
            constraints: const BoxConstraints(
              minWidth: 16,
              maxWidth: 16,
              minHeight: 16,
            ),
            child: Center(
              child: CaptionBig(
                text: displayText,
                textColor: itemColor,
              ),
            ),
          ),
          shape: StadiumBorder(
            side: BorderSide(color: itemColor),
          ),
        ),
      ),
    );
  }
}
```

---

### Step 35: Add Repeat Widget to Add Task

Create 2 new variables in \_AddTaskScreenState class for repeat type and days of week list if custom days are selected in repeat type.

```dart
RepeatType selectedRepeatType = RepeatType.once;
  List<DaysOfWeek> selectedCustomDays = [];
```

Now add Repeat widget to the column

```dart
RepeatSelectionWidget(
              iconData: Icons.replay,
              labelText: 'Set Repeat',
              repeatDisplayText: getRepeatTypeString(selectedRepeatType),
              selectedRepeatType: selectedRepeatType,
              customDays: selectedCustomDays,
              setSelectedRepeatType: (value) {
                setState(() {
                  selectedRepeatType = value;
                });
              },
              setCustomDays: (value) {
                selectedCustomDays = value;
              },
            ),
            const Divider(),
```

### Step 36: Create Notification Enum and Functions

Create an enum for notification and function to show String values. The values will be -

- Off
- Notify
- Alarm

```dart
enum NotificationType { off, notify, alarm }

String getNotificationString(NotificationType notificationType) {
  switch (notificationType) {
    case NotificationType.off:
      return 'Off';
    case NotificationType.notify:
      return 'Notify on';
    case NotificationType.alarm:
      return 'Alarm on';
  }
}
```

### Step 37: Create Notification Widget

Create a widget that will allow users to select notification status and notification type.

```dart
import 'package:flutter/material.dart';
import 'package:simply_do/core/global_widgets/text_widgets.dart';
import 'package:simply_do/core/utils/notification_function.dart';

class NotificationSelectionWidget extends StatelessWidget {
const NotificationSelectionWidget(
{super.key,
required this.iconData,
required this.labelText,
required this.notificationDisplayText,
required this.selectedNotificationType,
required this.onNotificationTypeChanged});

final IconData iconData;
final String labelText;
final String notificationDisplayText;
final NotificationType selectedNotificationType;
final ValueChanged<NotificationType> onNotificationTypeChanged;

@override
Widget build(BuildContext context) {
var themeColor = Theme.of(context).colorScheme;

    return Container(
      padding: const EdgeInsets.symmetric(vertical: 16, horizontal: 16),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Icon(
            iconData,
            size: 32,
          ),
          const SizedBox(
            width: 32,
          ),
          Expanded(
              child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              MediumTitle(
                text: labelText,
                textColor: themeColor.tertiary,
              ),
              const SizedBox(height: 8,),
              InkWell(
                onTap: (){
                  // TODO: Add showDialog function
                },
                child:
                BodyLarge(text: notificationDisplayText,),
              )

            ],
          ))
        ],
      ),
    );

}
}
```

---

### Step 38: Add showDialog function

Create a void function in same class to show alert dialog box

```dart
void showDialogBox(BuildContext context,
      NotificationType pickedNotificationType, ColorScheme themeColor) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return PopScope(
          onPopInvoked: (_) {
            pickedNotificationType = selectedNotificationType;
          },
          child: StatefulBuilder(
            builder: (BuildContext context, StateSetter setState) {
              return AlertDialog(
                backgroundColor: themeColor.tertiaryContainer,
                contentPadding: const EdgeInsets.all(16),
                actionsPadding:
                    const EdgeInsets.only(top: 0, right: 8, left: 8, bottom: 8),
                title: SmallTitle(
                  text: 'Select Notification Type',
                  textColor: themeColor.tertiary,
                ),
                content: SingleChildScrollView(
                  child: Column(
                    children: [
                      for (var option in NotificationType.values)
                        RadioTile<NotificationType>(
                            title: getNotificationString(option),
                            onChanged: (notificationType) {
                              setState(() {
                                pickedNotificationType = notificationType!;
                              });
                            },
                            value: option,
                            groupValue: pickedNotificationType)
                    ],
                  ),
                ),
                actions: [
                  Row(
                    mainAxisAlignment: MainAxisAlignment.end,
                    children: [
                      TextButton(
                        onPressed: () {
                          context.pop();
                        },
                        child: BodySmall(
                          text: 'Cancel',
                          textColor: themeColor.tertiary,
                        ),
                      ),
                      const SizedBox(
                        width: 16,
                      ),
                      TextButton(
                        onPressed: () {
                          onNotificationTypeChanged(pickedNotificationType);
                          context.pop();
                        },
                        child: BodySmall(
                          text: 'Ok',
                          textColor: themeColor.tertiary,
                        ),
                      ),
                    ],
                  )
                ],
              );
            },
          ),
        );
      },
    );
  }
```

---

### Step 39: Add time selection dialog

In Notification selection widget, if notification type in not off then, user will have option to select time. default time will be 8:00 AM.

In the widget, instead of just on Inkwell for Notification Option Text, create a row and add conditional another InkWell,

```dart
Row(
                children: [
                  InkWell(
                    onTap: () {
                      showDialogBox(
                        context,
                        pickedNotificationType,
                        themeColor,
                      );
                    },
                    child: BodyLarge(
                      text: notificationDisplayText,
                    ),
                  ),
                  const SizedBox(
                    width: 24,
                  ),
                  if (selectedNotificationType != NotificationType.off)
                    InkWell(
                      onTap: () {
                        showTimePicker(
                            context: context,
                            initialTime: selectedTime,
                            builder: (BuildContext context, Widget? child) {
                              return Theme(
                                  data: ThemeData(
                                    colorScheme: ColorScheme.dark(
                                      primary: themeColor.tertiary,
                                      surface: themeColor.tertiaryContainer,
                                      onSurface: themeColor.onPrimaryContainer,
                                    ),
                                  ),
                                  child: child!);
                            }).then(
                          (value) {
                            onSelectedTimeChanged(
                              value ?? const TimeOfDay(hour: 8, minute: 00),
                            );
                          },
                        );
                      },
                      child: BodyLarge(
                        text: selectedTimeDisplayText,
                      ),
                    )
                ],
              )
```

### Step 40: Create subtask class

Crate a model class for subTask to store subTaskName and is completed status.

```dart
class Subtask {
  String subTaskName;
  bool isCompleted;

  Subtask({
    required this.subTaskName,
    this.isCompleted = false,
  });

  void updateSubtaskName(String newName) {
    subTaskName = newName;
  }

  void updateSubtaskStatus(bool value) {
    isCompleted = value;
  }
}
```

---

### Step 41: Create a widget to add subtask

Add a widget that will allow user to add subtasks.

_add_subtask_widget.dart_

```dart
import 'package:flutter/material.dart';
import 'package:simply_do/core/global_widgets/text_widgets.dart';

import '../../../data/models/subtask.dart';

class AddSubtaskWidget extends StatelessWidget {
  const AddSubtaskWidget({
    super.key,
    required this.iconDate,
    required this.subTaskList,
    required this.labelText,
    required this.subTaskHintText,
    required this.subtaskEditingController,
    required this.onSubtaskListChanged,
  });

  final IconData iconDate;
  final String labelText;
  final String subTaskHintText;
  final List<Subtask> subTaskList;
  final ValueChanged<List<Subtask>> onSubtaskListChanged;
  final TextEditingController subtaskEditingController;

  @override
  Widget build(BuildContext context) {
    var themeColor = Theme.of(context).colorScheme;
    return Container(
      padding: const EdgeInsets.all(16),
      child: Column(
        children: [
          Row(
            mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              Icon(
                iconDate,
                size: 32,
              ),
              const SizedBox(
                width: 32,
              ),
              Expanded(
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    MediumTitle(
                      text: labelText,
                      textColor: themeColor.tertiary,
                    ),
                    const SizedBox(
                      height: 8,
                    ),
                    Row(
                      children: [
                        Expanded(
                          child: TextField(
                            controller: subtaskEditingController,
                            style: TextStyle(
                              fontSize: 18,
                              color: Theme.of(context)
                                  .colorScheme
                                  .onPrimaryContainer,
                            ),
                            decoration: InputDecoration(
                              isDense: true,
                              hintText: subTaskHintText,
                              contentPadding: const EdgeInsets.all(0),
                            ),
                          ),
                        ),
                        IconButton(
                          onPressed: () {
                            if (subtaskEditingController.text.isNotEmpty) {
                              subTaskList.add(
                                Subtask(
                                    subTaskName: subtaskEditingController.text),
                              );
                              subtaskEditingController.text = '';
                              onSubtaskListChanged(subTaskList);
                            }
                          },
                          icon: const Icon(Icons.add),
                        )
                      ],
                    ),
                  ],
                ),
              )
            ],
          ),
          // TODO: Add Subtask Display
        ],
      ),
    );
  }
}
```

---

### Step 42: Create SubTask tile widget

Create a widget to show each subtask tile.

_subtask_tile.dart_

```dart
import 'package:flutter/material.dart';

import '../../../core/global_widgets/text_widgets.dart';
import '../../../data/models/subtask.dart';

class SubtaskTile extends StatefulWidget {
  const SubtaskTile(
      {super.key, required this.subtask, required this.onSubtaskDeleted});

  final Subtask subtask;
  final ValueChanged<Subtask> onSubtaskDeleted;

  @override
  State<SubtaskTile> createState() => _SubtaskTileState();
}

class _SubtaskTileState extends State<SubtaskTile> {
  bool isEditingModeOn = false;
  late TextEditingController subtaskEditController;

  @override
  void initState() {
    super.initState();
    subtaskEditController =
        TextEditingController(text: widget.subtask.subTaskName);
  }

  @override
  Widget build(BuildContext context) {
    return ListTile(
      title: isEditingModeOn
          ? TextField(
              controller: subtaskEditController,
            )
          : BodyLarge(text: widget.subtask.subTaskName),
      leading: IconButton(
        onPressed: () {
          setState(() {
            isEditingModeOn = !isEditingModeOn;
          });
        },
        icon: Icon(isEditingModeOn ? Icons.close : Icons.edit),
      ),
      trailing: isEditingModeOn
          ? IconButton(
              onPressed: () {
                setState(() {
                  widget.subtask.updateSubtaskName(subtaskEditController.text);
                  isEditingModeOn = false;
                });
              },
              icon: const Icon(Icons.check),
            )
          : IconButton(
              onPressed: () {
                widget.onSubtaskDeleted(widget.subtask);
              },
              icon: const Icon(Icons.delete),
            ),
    );
  }
}
```

---

### Step 43: Add Subtask tile in main column

```dart
for (var subtask in subTaskList)
            SubtaskTile(
              subtask: subtask,
              onSubtaskDeleted: (Subtask value) {
                subTaskList.remove(value);
                onSubtaskListChanged(subTaskList);
              },
            ),

```

---

### Step 44: Add subtask widget to add task screen

Create new TextEditingController for subtask, just like we added for task name and description

```dart
late TextEditingController subTaskEditingController;
```

Now, initialize it

```dart
@override
  void initState() {
    super.initState();
    taskNameController = TextEditingController();
    taskDescController = TextEditingController();
    subTaskEditingController = TextEditingController();
  }
```

Add one more variable for list of Subtask type.

```dart
List<Subtask> subTaskList = [];
```

Now, finally add subtask widget at the end.

```dart
AddSubtaskWidget(
              iconDate: Icons.format_list_bulleted,
              subTaskList: subTaskList,
              labelText: 'Sub Tasks',
              subTaskHintText: 'Add a Subtask',
              subtaskEditingController: subTaskEditingController,
              onSubtaskListChanged: (List<Subtask> value) {
                setState(() {
                  subTaskList = value;
                });
              },
            ),
```

---

### Step 45: Create basic model class for Task

Create a basic task model class with base constructor to add a new task -

```dart
import 'package:flutter/material.dart';
import 'package:simply_do/core/utils/notification_function.dart';
import 'package:simply_do/core/utils/priority_function.dart';
import 'package:simply_do/core/utils/repeat_function.dart';
import 'package:simply_do/data/models/subtask.dart';

class Task {
  final int? taskKey;
  final String taskName;
  final String? taskDescription;
  final DateTime? dueDate;
  final PriorityOptions priorityType;
  final RepeatType repeatType;
  final List<DaysOfWeek> customRepeatDays;
  final NotificationType notificationType;
  final TimeOfDay notificationTime;
  final List<Subtask> subtasks;
  final bool isCompleted;
  final DateTime? lastCompleted;
  final DateTime? nextDueDate;

  const Task(
      {this.taskKey,
      required this.taskName,
      this.taskDescription,
      this.dueDate,
      required this.priorityType,
      required this.repeatType,
      required this.customRepeatDays,
      required this.notificationType,
      required this.notificationTime,
      required this.subtasks,
      this.isCompleted = false,
      this.lastCompleted,
      this.nextDueDate,
      });


}

```

---

### Step 46: Create Function to manage dueDate if repeat type is not once

Here is a small thing that we want, if user selects repeat type anything other than once, in that case, we need to have a due date to start with, so if user has already selected it, then good, otherwise, we will automatically assign it today's date. For that, all we have to do is make some changes in add task form, where we are adding repeat selection widget -

```dart
RepeatSelectionWidget(
              iconData: Icons.replay,
              labelText: 'Set Repeat',
              repeatDisplayText: getRepeatTypeString(selectedRepeatType),
              selectedRepeatType: selectedRepeatType,
              customDays: selectedCustomDays,
              setSelectedRepeatType: (value) {
                setState(() {
                  selectedRepeatType = value;
                  if (selectedRepeatType != RepeatType.once) {
                    dueDate ??= DateTime.now();
                  }
                });
              },
              setCustomDays: (value) {
                selectedCustomDays = value;
              },
            ),
```

---

### Step 47: Create getNextDueDate Function

Create a function to get next due date based on due date and repeatType except for Custom days -

```dart
DateTime? getNextDueDate() {
    if (repeatType == RepeatType.once) {
      return null;
    } else if (repeatType == RepeatType.daily) {
      return dueDate!.add(const Duration(days: 1));
    } else if (repeatType == RepeatType.weekly) {
      return dueDate!.add(const Duration(days: 7));
    } else if (repeatType == RepeatType.monthly) {
      return getNextMonth(dueDate!);
    } else if (repeatType == RepeatType.yearly) {
      return DateTime(dueDate!.year + 1, dueDate!.month, dueDate!.day,
          dueDate!.hour, dueDate!.minute, dueDate!.second);
    } else if (repeatType == RepeatType.custom) {
      // TODO: Function to get next date based on custom days
    }
  }
```

---

### Step 48: Create getDueDate if repeat type is custom

A function to get next due date if the custom days are selected -

```dart
DateTime? getCustomNextDay(DateTime currentDate) {
    if (customRepeatDays.isEmpty) {
      return null;
    }

    List<int> customWeekdaysList = [];

    for (var day in customRepeatDays) {
      int dayIndex = daysToWeekday[day]!;
      customWeekdaysList.add(dayIndex);
    }

    DateTime nextDay = currentDate.add(const Duration(days: 1));

    while (!customWeekdaysList.contains(nextDay.weekday)) {
      nextDay = nextDay.add(const Duration(days: 1));
    }

    return nextDay;
  }

  Map<DaysOfWeek, int> daysToWeekday = {
    DaysOfWeek.monday: 1,
    DaysOfWeek.tuesday: 2,
    DaysOfWeek.wednesday: 3,
    DaysOfWeek.thursday: 4,
    DaysOfWeek.friday: 5,
    DaysOfWeek.saturday: 6,
    DaysOfWeek.sunday: 7
  };
```

---

### Step 49: Complete task class

We will add more functions later like onTaskCompleted, named constructor and others, but for now to create a new task, this is complete class.

```dart
import 'package:flutter/material.dart';
import 'package:simply_do/core/utils/notification_function.dart';
import 'package:simply_do/core/utils/priority_function.dart';
import 'package:simply_do/core/utils/repeat_function.dart';
import 'package:simply_do/data/models/subtask.dart';

class Task {
  final int? taskKey;
  final String taskName;
  final String? taskDescription;
  final DateTime? dueDate;
  final PriorityOptions priorityType;
  final RepeatType repeatType;
  final List<DaysOfWeek> customRepeatDays;
  final NotificationType notificationType;
  final TimeOfDay notificationTime;
  final List<Subtask> subtasks;
  final bool isCompleted;
  final DateTime? lastCompleted;
  DateTime? nextDueDate;

  Task({
    this.taskKey,
    required this.taskName,
    this.taskDescription,
    this.dueDate,
    required this.priorityType,
    required this.repeatType,
    required this.customRepeatDays,
    required this.notificationType,
    required this.notificationTime,
    required this.subtasks,
    this.isCompleted = false,
    this.lastCompleted,
  }) {
    nextDueDate = getNextDueDate();
  }

  DateTime? getNextDueDate() {
    if (repeatType == RepeatType.once) {
      return null;
    } else if (repeatType == RepeatType.daily) {
      return dueDate!.add(const Duration(days: 1));
    } else if (repeatType == RepeatType.weekly) {
      return dueDate!.add(const Duration(days: 7));
    } else if (repeatType == RepeatType.monthly) {
      return getNextMonth(dueDate!);
    } else if (repeatType == RepeatType.yearly) {
      return DateTime(dueDate!.year + 1, dueDate!.month, dueDate!.day,
          dueDate!.hour, dueDate!.minute, dueDate!.second);
    } else if (repeatType == RepeatType.custom) {
      return getCustomNextDay(dueDate!);
    }
    return null;
  }

  // Utils Functions

  DateTime? getCustomNextDay(DateTime currentDate) {
    if (customRepeatDays.isEmpty) {
      return null;
    }

    List<int> customWeekdaysList = [];

    for (var day in customRepeatDays) {
      int dayIndex = daysToWeekday[day]!;
      customWeekdaysList.add(dayIndex);
    }

    DateTime nextDay = currentDate.add(const Duration(days: 1));

    while (!customWeekdaysList.contains(nextDay.weekday)) {
      nextDay = nextDay.add(const Duration(days: 1));
    }

    return nextDay;
  }

  Map<DaysOfWeek, int> daysToWeekday = {
    DaysOfWeek.monday: 1,
    DaysOfWeek.tuesday: 2,
    DaysOfWeek.wednesday: 3,
    DaysOfWeek.thursday: 4,
    DaysOfWeek.friday: 5,
    DaysOfWeek.saturday: 6,
    DaysOfWeek.sunday: 7
  };

  DateTime getNextMonth(DateTime currentDate) {
    int year = currentDate.year;
    int month = currentDate.month + 1;
    int day = currentDate.day;

    if (month > 12) {
      month = 1;
      year++;
    }

    int nextMonthLastDay = DateTime(year, month, 1, 0).day;

    if (day > nextMonthLastDay) {
      day = nextMonthLastDay;
    }

    return DateTime(year, month, day, currentDate.hour, currentDate.minute,
        currentDate.second);
  }
}

```

---

### Step 50: Initialize database

import sqflite and add base database class

```
flutter pub add sqflite
```

In database folder create a new file named database.dart

```dart
class DbHelper {
  static Database? _db;
  static final DbHelper instance = DbHelper._constructor();
  DbHelper._constructor();

}
```

---  

### Step 51: Create String variables for all the parameters in task model

Within same DbHelper class, after constructor, create variables to store string value for table name and all teh columns name for database.

```dart
final String _taskTableName = 'task_table';

  final String _taskKey = 'task_key';
  final String _taskName = 'task_name';
  final String _taskDesc = 'task_desc';
  final String _taskDueDate = 'task_due_date';
  final String _taskPriority = 'task_priority';
  final String _taskRepeatType = 'task_repeat_type';
  final String _taskRepeatDays = 'task_repeat_days';
  final String _taskNotificationType = 'task_notification_type';
  final String _taskNotificationTime = 'task_notification_time';
  final String _subTasks = 'sub_tasks';
  final String _isCompleted = 'is_completed';
  final String _taskLastDate = 'task_last_date';
  final String _taskNextDate = 'task_next_date';
```

---  

### Step 52: Create

### Repeat to json String

Create function to add subtask to json string.

---

### Notifications to json String

Create function to add subtask to json string.

---

### Subtasks to json String

Create function to add subtask to json string.

---

### Create map function to convert task to map

Create mapping to convert task to map

---

### Create task named Constructor

Create a named constructor for class that will take map as input and add a new class

---

### Saving and leaving add task

Create function for saving task by checking task name and leaving the page

---

### Creating future builder

Create future builder to show all task in my day(for now).

---

### Create task tile

Create task_tile.dart in widgets folder to show each task with drag-able widget.

---

### Create functions for task-tile dragging

Create functions and UI for dragging task tile - edit and delete.

---

### Create edit_task.dart screen

Create a new screen for edit task and add it to constant and routes.

---

### Create edit_task UI

Create user-interface for edit task.

---

### Create function for competing subTask

Create function for completing and in-completing subtasks

---

### Create function for completing a task

Create function for completing a task and creating a new one based on repeat type.

---

### Show snack-bar option to confirm complete

Show a snack-bar to confirm that the task was completed and an undo option.

---

### Create function for un-completing a task

Create function if user marks a task not complete.

---

### Set functions for My Day Screen

Create functions to show tasks in the screen

---

### Set functions for Planned Screen

Create functions to show tasks in the screen - date, wise

---

### Set functions for All Tasks Screen

Create functions to show tasks in the screen

---

### Set functions for Completed Screen

Create functions to show tasks in the screen

---

### Create a timelineAction map

Create a timeline action map to store different task for timeline

- taskAdded
- taskDeleted
- taskCompleted
- taskNotCompleted
- allTaskCompleted(will be used later with time)

---

### Create timeline modal class

Create a modal class for timeline to store

- date
- time
- task

---

### Create a timeline database

Create another database for storing timelineAction -

---

### Create mapping function for timeline database

Create map to convert modal to database

---

### Create named constructor to add timelineAction

Create a default named constructor from map to add timelineAction

---

### Create timeline_state.dart

Create timeline state to manage state of timeline

---

### Create timeline tile to show the timeline

Create a timeline tile to show the timeline date-wise

---

### Task Completion modal class

Add a task completion modal class to store parameters for it.

### Create mapping function

Create a mapping function to convert task completion modal to map

### Create construction

Create a constructor to create task completion modal class from map

### Create completion state

Create function for adding and removing task from completion.

### Functions for 100% Completion Streaks

Create a function for finding out 100% current Streak

### Function for Average

### Functions for Productivity

### Create base settings screen

### Settings sub-page Account

### Settings sub-page Notifications

### Settings sub-page Subscriptions

### Settings sub page Database

### Settings sub page Support

###

###

###

###

###

###

###

###

###

###

###

###

###

###

###

###

###

###

###

###

###

###

###

###

###

###

###
