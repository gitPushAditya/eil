## Date Time

### Set 

```dart
DateTime time = DateTime.now()
```

### To String

```dart
String timeInString = time.toIso8601String()
```

### Parsing

```dart
DateTime.parse(timeInString)
```

---

## Time

### Set

```dart
final time = TimeOfDay(hour: 8, minutes: 0);
```

### To String

```dart
String timeInString = '${time.hour}:${time.minute}';
```

### Parsing

```dart
final parts = timeInString.split(':');
final time = TimeOfDay(
hour: int.parse(parts[0]),
minute: int.parse(parts[1]),
);
```

---


