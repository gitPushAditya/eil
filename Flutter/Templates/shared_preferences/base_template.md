```dart
import 'package:shared_preferences/shared_preferences.dart';

class SharedPrefs {
  // Singleton

  static final SharedPrefs _instance = SharedPrefs._internal();

  factory SharedPrefs() {
    return _instance;
  }

  SharedPrefs._internal();

  Future<SharedPreferences> get prefs => SharedPreferences.getInstance();

  // Save Values

  Future<bool> saveString(String key, String value) async {
    final SharedPreferences prefs = await this.prefs;
    return prefs.setString(key, value);
  }

  Future<bool> saveInt(String key, int value) async {
    final SharedPreferences prefs = await this.prefs;
    return prefs.setInt(key, value);
  }

  Future<bool> saveDouble(String key, double value) async {
    final SharedPreferences prefs = await this.prefs;
    return prefs.setDouble(key, value);
  }

  Future<bool> saveBool(String key, bool value) async {
    final SharedPreferences prefs = await this.prefs;
    return prefs.setBool(key, value);
  }

  Future<bool> saveList(String key, List<String> list) async {
    final SharedPreferences prefs = await this.prefs;
    return prefs.setStringList(key, list);
  }

  // Get Values

  Future<String?> getString(String key) async {
    final SharedPreferences prefs = await this.prefs;
    return prefs.getString(key);
  }

  Future<int?> getInt(String key) async {
    final SharedPreferences prefs = await this.prefs;
    return prefs.getInt(key);
  }

  Future<double?> getDouble(String key) async {
    final SharedPreferences prefs = await this.prefs;
    return prefs.getDouble(key);
  }

  Future<bool?> getBool(String key) async {
    final SharedPreferences prefs = await this.prefs;
    return prefs.getBool(key);
  }

  Future<List<String>?> getList(String key) async {
    final SharedPreferences prefs = await this.prefs;
    return prefs.getStringList(key);
  }

  // Remove a Values

  Future<bool> remove(String key) async {
    final SharedPreferences prefs = await this.prefs;
    return prefs.remove(key);
  }

  // Clear all Values

  Future<bool> clear() async {
    final SharedPreferences prefs = await this.prefs;
    return prefs.clear();
  }

  // Check if contains a Value

  Future<bool> containsKey(String key) async {
    final SharedPreferences prefs = await this.prefs;
    return prefs.containsKey(key);
  }
}
```
