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

### Create a new project - simply_do  
Open a disignated folder for flutter project and in commnad prompt type - 

```
flutter create simply_do
```
---

### Create basic folder structure - 
assets file in root folder  
Inside lib folder -   
config - env.dart, routes.dart  
core - constants, global_widgets, utils  
data - database, models, providers  
ui - screens, themes, widgets  
app.dart  
main.dart  
---

### Create colors.dart in themes file
Create an abstract class for colors to store all the colors. 

_colors.dart_
```dart
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
  static Color highlightOne = const Color(0xffC50000);
  static Color highlightTwo = const Color(0xffC79C00);
  static Color highlightThree = const Color(0xff3DC000);
}

abstract final class HighlightColors {
  static Color highlightRed = const Color(0xffC50000);
  static Color highlightOrange = const Color(0xffC79C00);
  static Color highlightGreen = const Color(0xff3DC000);
  static Color highlightGolden = const Color.fromARGB(255, 216, 170, 17);
}
```
---
### Create app_themes.dart for themes in themes folder

Create another abstact class for themes - 

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

### Create a app_screens.dart file in constants folder
This will store an abstract class for all the string variable for all the screens that will be used.
- tasks
- timeline
- analytics
- settings
- premium
- tutorial
- contact

---

### Create basic files for all pages

Create different files in screens folder for all the pages - 

- tasks - stateful
- timeline - stateful
- analytics - stateful
- settings - stateless
- premium - stateless
- tutorial - stateless
- contact - stateless

---

### Add go_router and routes.

Import router and create a routes.dart in constant to add routes variable. Add all the routes in router variable.

---

### Add app_state.dart in providers folder

Import provider and add app_state.dart in provider folder and initially take selected theme and premium subscriber and user_name as state.

---

### Edit app.dart

Add all the required code in app.dart file with Material app, router and provider.

---

### Edit main.dart

Add app class in main.dart file and run the app once.

---

### Create Drawer widget

Create drawer widget(Without any functions).

---

### Create page_state.dart

Create page_state.dart in providers folder to store page state and change the function to change the appearance of selected page.

---

### Add functions to Drawer

Add page changing and page_state changing functions to drawer.

---

### Add Bottom App Bar

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


