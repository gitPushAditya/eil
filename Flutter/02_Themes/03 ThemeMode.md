
# Theme Mode

In `/constants/enums` create a file name `theme_mode.dart`

```dart
  
/// Enum representing the available theme modes  
@JsonEnum()  
enum ThemeMode {  
  @JsonValue('system')  
  system('System', Icons.phone_android),  
  @JsonValue('light')  
  light('Light', Icons.light_mode),  
  @JsonValue('dark')  
  dark('Dark', Icons.dark_mode);  
  
  final String name;  
  final IconData iconData;  
  
  const ThemeMode(this.name, this.iconData);  
}
```

---
