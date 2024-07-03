# Development Base (Step 1 - 10).

## Step 1:- Ideas and Concepts.

**App Name** - "Simply Do"

**Idea** - A basic to-do app that will allow user to add task with multiple properties and store detailed analytics and timeline for enhanced productivity.

**Concept**-

- **Add Task** - Simple interface to add new task.
- **Display Tasks** - A clean and organized view of all tasks based on today's tasks, planned tasks, all tasks and completed tasks.
- **Checkbox** - Task can be marked complete or incomplete.
- **Delete Task** - Ability to delete a task.
- **Edit** - Ability to edit a task.
- **Due Date** - Add due date option and view tasks based on that. Tasks with no due date will be only be shown in all tasks and completed(once they are completed).
- **Priority** - Allow users to add priority to a task. Default will be None
- **Notification** - This option will let user to set a time for notification or alarm. By default it will be off.
- **Recurring Tasks** - Tasks can be repeated on daily basis, weekly, monthly, yearly or on custom days. By default it will be Once.
- **Light/Dark Themes** - Option to switch between multiple themes.
- **Widgets** - Option to add widget on home screen.
- **Detailed Timeline** - Detailed timeline panel to show users activity in the app.
- **Analytics** - Detailed analytics for user to understand more about their productivity and timings.

---

## Step 2:- Planning.

### Base Pages -

- **Tasks** - To display tasks.
- **Timeline** - To display detailed timeline.
- **Analytics** - To display detailed analytics.
- **Settings** - For all user settings and options.

### Other Pages -

- **Add Task** - To add a new task.
- **Edit Task** - To edit task.

### Sub Pages/Screens -

- **Tasks:-**
  - My Day (default) - To display all the tasks that are due today.
  - Planned - To display all planned task(where due date is not null).
  - All Tasks - To display all the tasks, planned or unplanned.
  - Completed - To display all the completed tasks.
- **Settings:-**
  - Account - To display settings for Full Name, Username, Google Link.
  - Notification - Daily Notification, Start of Day, End of Day, Email Notification
  - Subscription - Status, Get Premium, Update, Renew
  - Database - Manage All Data, Task Data, Clear Completed Task, Clear Timeline, Clear Analytics, Download Data
  - Support - Contact, Tutorial, Report a bug.

## ![alt text](<App Wireframe.png>)

## Step 3:- New Flutter Project.

_Note:- From this step and onwards, we are going to work in flutter. I am using Android Studio on Windows Platform for development but you can use other IDEs as well._

_Note:- This tutorial considers that you already have flutter and Android Studio Installed(You will have to install Android Studio whether you use it for coding or not). Here is a detailed guide for installing flutter and other related applications - https://docs.flutter.dev/get-started/install_

Open a directory where you want to store all your apps, then open command prompt from that directory(right click and choose- Open in Terminal).

Write the following code in terminal -

```
flutter create simply_do
```

If you followed all the steps from the link given above, this command should create a new directory with named - simply_do.

If this doesn't work, you can check flutter by writing `flutter doctor` in command prompt.

If flutter is not found then go back to link above and check every step and don't forget to add flutter bin folder to path variables.

There are bunch of tutorials and documents for this process, you might wanna use one of them.

---

## Step 4:- Basic Folder Structure.

Once your project is created, you should open the directory simply_do in Visual Studio Code or Android Studio. Once open, you will see multiple files and folders, but don't panic, you don't have to work in all of them. Mostly you will be working inside 'lib' folder and we will very rarely touch other folders and files for very specific uses.

Before we start with coding, let's create a basic folder structure for our files, you don't want all of them messed up in one place, do you?

First of all, in our root directory(simply_do), create a folder named - 'assets'. This directory will store all the assets like images, icons, sounds etc. I will also recommend to add separate sub-directory for them when you add them(like icons sub-directory for storing icons). 'assets' is one and only directory that you are going to add in root directory, other than that, every other directory will be added inside lib folder.

Here is the basic structure of lib folder -

lib

- config
  - env.dart
  - routes.dart
- core
  - constants
  - global_widgets
  - utils
- data
  - database
  - models
  - providers
- ui
  - screens
  - themes
  - widgets
- app.dart
- main.dart

_Note:- .dart files are dart files, but all the others are folders and sub-folder._

Now, you have created the folder structure, it's time to know about their use -

'config' folder contains two files related to app configurations-  
'env.dart' file to store environment variables which are basically secret api keys or password that you don't want to share or store in app code.  
'routes.dart' will store all the routes for navigating to one screen to another.

'core' folder contains three sub-folders - 'constants' for storing all the constant variables of app, variables that will stay same and will be used throughout the app.  
'global_widget' for storing all of the widgets that are used globally, a simple rule that I follow is - if a widget is used in more than one screen or page, then it's global.  
'utils' for adding utilities functions, these are helper functions that we will use in app.

'data' folder is mainly associated with storing data. It contains 3 folders - 'database' for storing database related files, we will use sqflite in this app, so we will store files related to that.  
'models' folder will contain all the model classes.  
'providers' will contain all the files related to provider. In case you don't know, provider is basically a state manager throughout the app. For say, you have a variable you changed it's value in one screen, but it is used by other screens too, so for all of the screen to know about these changes, you need an app-wide state manager.

'ui' folder is mostly self-explanatory. It contains three folders, all related to UI.  
'screens' folder will contain all the screens(pages of the app).  
'themes' folder will contain all the files related to themes and colors.  
'widgets' folder will contain all the non-global widgets that are only used in one screen or as a part of other widget.

---

## Step 5:- Decide Colors For The App

This step is not directly related to app development, but it's crucial if you are planning to create a production ready app. Before starting your development process, decide the main colors and theme that you are going to use in the app and store them. Obviously we can adjust or change it later, and we will also create our app in a way that it supports multiple themes if we want to give that option in future.

Primarily, you need to decide four things:

- Your default theme brightness - light or dark
- Your accent color - will be used in specific areas but will provide theme to entire app.
- Your foreground color - a shade of white if brightness is dark and vice versa.
- Your background color - a shade of black if brightness is dark and vice versa.

_Note:- Two really good website to help you with deciding colors ad UI are: https://dribbble.com/ and https://www.behance.net/_

For this app, we have:

- Brightness - dark
- Accent color - deep purple hex code - #6735AC
- Foreground color - shade of white - rgb value - 236, 245, 255
- Background color - shade of black - hex code - #1D1C2C

After you have decided these colors, there is one more thing you need to do. You need to create two more shades of each color, which will make each color in three different shades. Why this? well, because you are not going to use one same color everywhere, there must some difference.

For accent color, create two more shades that are close to accent color. Do the same for background and foreground color.

Now we have 9 colors in total:

- accentPrimary = #6735AC
- accentSecondary = #7E46D9
- accentTertiary = #9180F9
- backgroundPrimary = #1D1C2C
- backgroundSecondary = #34344C
- backgroundTertiary = rgb 35, 37, 53
- foregroundPrimary = rgb 236, 245, 255
- foregroundSecondary = #D9D9D9
- foregroundTertiary = rgb 168, 168, 168

---

# Add Task (Step 11 - 20).

---

# Display Task (Step 21 - 30).

---

#
