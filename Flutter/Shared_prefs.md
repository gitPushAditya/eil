# Shared Preferences

Shared Preferences in Flutter is a key-value store for persisting simple data, such as user settings or preferences, across app launches. It's a lightweight solution ideal for storing small amounts of data like strings, integers, or booleans. Here’s how to use Shared Preferences in Flutter:

### 1. **Add Dependency**

First, add the `shared_preferences` package to your `pubspec.yaml` file:

```yaml
dependencies:
  flutter:
    sdk: flutter
  shared_preferences: ^2.0.15  # Check for the latest version on pub.dev
```

Then, run `flutter pub get` to install the package.

or 

```
flutter pub add shared_preferences
```

### 2. **Import the Package**

In your Dart file, import the `shared_preferences` package:

```dart
import 'package:shared_preferences/shared_preferences.dart';
```

### 3. **Store Data**

To store data, use the `SharedPreferences` class. Here’s an example of how to save various types of data:

```dart
Future<void> saveData() async {
  // Obtain shared preferences
  final prefs = await SharedPreferences.getInstance();

  // Save data
  await prefs.setString('username', 'JohnDoe');
  await prefs.setInt('userAge', 30);
  await prefs.setBool('isLoggedIn', true);
  await prefs.setDouble('userRating', 4.5);
}
```

### 4. **Retrieve Data**

To retrieve the data, use the `get` methods provided by `SharedPreferences`:

```dart
Future<void> loadData() async {
  // Obtain shared preferences
  final prefs = await SharedPreferences.getInstance();

  // Retrieve data
  final String? username = prefs.getString('username');
  final int? userAge = prefs.getInt('userAge');
  final bool? isLoggedIn = prefs.getBool('isLoggedIn');
  final double? userRating = prefs.getDouble('userRating');

  print('Username: $username');
  print('User Age: $userAge');
  print('Is Logged In: $isLoggedIn');
  print('User Rating: $userRating');
}
```

### 5. **Remove Data**

To remove data, use the `remove` method:

```dart
Future<void> removeData() async {
  // Obtain shared preferences
  final prefs = await SharedPreferences.getInstance();

  // Remove data
  await prefs.remove('username');
}
```

### 6. **Clear All Data**

To clear all data stored in Shared Preferences:

```dart
Future<void> clearAllData() async {
  // Obtain shared preferences
  final prefs = await SharedPreferences.getInstance();

  // Clear all data
  await prefs.clear();
}
```

### 7. **Check for Key Existence**

Before retrieving a value, you can check if the key exists:

```dart
Future<void> checkKey() async {
  final prefs = await SharedPreferences.getInstance();
  
  if (prefs.containsKey('username')) {
    final String? username = prefs.getString('username');
    print('Username exists: $username');
  } else {
    print('Username key does not exist');
  }
}
```

### **Example Usage**

Here’s how you might use Shared Preferences in a Flutter app to store and load user preferences:

```dart
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: HomeScreen(),
    );
  }
}

class HomeScreen extends StatefulWidget {
  @override
  _HomeScreenState createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  String _username = '';

  @override
  void initState() {
    super.initState();
    _loadUsername();
  }

  Future<void> _loadUsername() async {
    final prefs = await SharedPreferences.getInstance();
    setState(() {
      _username = prefs.getString('username') ?? 'Guest';
    });
  }

  Future<void> _saveUsername(String username) async {
    final prefs = await SharedPreferences.getInstance();
    await prefs.setString('username', username);
    _loadUsername(); // Refresh the displayed username
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Shared Preferences Example')),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text('Hello, $_username!'),
            ElevatedButton(
              onPressed: () => _saveUsername('JohnDoe'),
              child: Text('Set Username to JohnDoe'),
            ),
          ],
        ),
      ),
    );
  }
}
```

### Summary

- **Add Dependency**: Add the `shared_preferences` package to your `pubspec.yaml`.
- **Import and Use**: Use `SharedPreferences` to store, retrieve, remove, and clear data.
- **Manage Data**: Store and manage simple data types efficiently.

Shared Preferences is ideal for saving user settings and other simple data that needs to persist across app launches. For more complex data or larger storage needs, consider using a local database solution like SQLite or Hive.