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
- **Task Page** appbar at the top with title in middle and premium option at right. In bottomAppBar, 4 options - My Day, Planned, All, Completed. Floating action button will allow to add new task.
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

### Step 1:  Create a new project - simply_do  
Open a designated folder for flutter project and in commnad prompt type - 

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

### Step 3: Create colors.dart in themes file
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


### Step 16: Add Bottom App Bar

Add bottom_app_bar in widgets folder to tasks page.(Without Functions)

---

### Create tasks_screen_state.dart

Create tasks_screen_state.dart in providers folder and add variables and functions to track state of tasks screen.

---

### Create 4 widgets for tasks body

Create 4 different widgets with simple text in widgets folder  
- my_day.dart
- planned.dart
- all_tasks.dart
- completed.dart

---

### Create functions to manage state body change

Create functions in bottom_app_bar and tasks page to change body, state, app title based on bottom app bar selection.

---

### Create floating action button

Create floating action button which will direct to another screen called add_task.dart

---

### Create another screen add_task.dart

Create another screen and add it to screens folder, also add it in screen constant and router variable

---

### Create a priority enum

Create a priority_type.dart enum in utils folder to store priority

---

### Create notification enum

Create notification_type.dart enum in utils to store notification type.

---

### Create weekDays enum

Create enum for weekDays to be added in customRepeatDays

---

### Create subtask class

Crate a model class for subTask to store subTaskName and is completed status

---

### Create task_model.dart 

Create a task model to add a new task -

- taskKey
- taskName
- taskDescription
- taskDueDate
- taskPriorityType
- taskNotification
- taskNotificationType
- taskNotificationTime
- taskRepeat
- taskRepeatType
- taskCustomRepeatDays
- subTasks
- isCompleted
- lastCompleted
- nextDueDate

---

### Work on add_task.dart 

Create UI for add task(no function)

---

### Create task_database

import sqflite and add database for task

---

### Subtask to json String

Create function to add subtask to json string.

---

### Create map function to convert task to map

Create mapping to convert task to map

---

### Create task named Constructor

Create a named constructor for class that will take map as input and add a new class

---

### Create task_state.dart

Create task state and functions to add, delete and update functions.

---

### Saving and leaving add task

Create function for saving task by checking task mame and leaving the page

---

### Creating future builder

Create future builder to show all task in my day(for now).

---

### Create task tile 

Create task_tile.dart in widgets folder to show each task with drag-able widget.

---

### Create edit_task.dart screen

Create a new screen for edit task and add it to constant and routes.

---

### Create edit_task UI

Create user-interface for edit task.

---

### Create functions for task-tile dragging

Create functions and UI for dragging task tile - edit and delete.

---

### Set functions for each task screen

Create functions to show tasks for My Day, Planned, All, Completed.

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

### Create a timelineAction map

Create a timeline action map to store different task for timeline

- taskAdded
- taskDeleted
- taskCompleted
- taskIncompleted
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

### Function for 100% current streak

Create a function for finding out 100% current Streak

### Function for 100% max streak

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

###

###

###

###

###

###

###


