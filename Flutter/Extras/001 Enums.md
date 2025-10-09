# Enums

Enum is a special type in dart that let's you define a fixed set of named constant values. 
### Declaration

```dart
enum Status { loading, success, error }
```

### Accessing Enum Values

```dart
Status currentStatus = Status.loading;
```

### Switch Statements

```dart
switch (currentStatus) {
  case Status.loading:
    print('Loading...');
    break;
  case Status.success:
    print('Success!');
    break;
  case Status.error:
    print('Error!');
    break;
}
```

### Get all values

```dart
for (final status in Status.values) {
  print(status); // Status.loading, Status.success, Status.error
}
```

### Get Index

```dart
print(Status.success.index); // 1
```

### Get name

```dart
print(Status.error.name); // "error"
```

### To String

Note: Name is preferred over string

```dart
print(Status.error.toString());
```

### Values

```dart
List<Status> all = Status.values;
```

### Saving and Retrieving Values

```dart
// Save
Status status = Status.error.name;

// Retrive
Status status = Status.values.firstWhere(
(stat) => stat.name == savedStatus,
orElse: () => Status.error,
);
```

---

## Enhanced Enums

Enhanced enums are a powerful Dart 3 feature that lets enums:

- Have fields (data attached to each value)
- Have constructors (including constant ones)
- Have methods/getters (custom logic per value)
- Implement interfaces

### Fields and Constructors

```dart
enum BadgeType {
  streak('Streak Badge', '🏆'),
  social('Social Badge', '👥');

  final String displayName;
  final String icon;

  const BadgeType(this.displayName, this.icon);
}
```

### Methods, Getter, and Logic

```dart
enum RoutineStatus {
  active(1),
  paused(2),
  completed(3);

  final int code;

  const RoutineStatus(this.code);

  bool get isActive => this == RoutineStatus.active;

  String get displayLabel {
    switch (this) {
      case RoutineStatus.active:
        return 'Active';
      case RoutineStatus.paused:
        return 'Paused';
      case RoutineStatus.completed:
        return 'Completed';
    }
  }
}
```

### With Interface

```dart
abstract class Routable {
  String routeName();
}

enum AppScreen implements Routable {
  home,
  settings;

  @override
  String routeName() {
    switch (this) {
      case AppScreen.home:
        return '/';
      case AppScreen.settings:
        return '/settings';
    }
  }
}
```

---
## JsonEnum

An annotation that marks an enum for custom JSON serialization and deserialization, allowing you to specify exactly how each enum value appears in JSON.

---
## **How It Works**

- Place `@JsonEnum()` above your enum declaration.
- Use `@JsonValue('...')` on enum values to set the exact string (or int) that will be used in the JSON.
- When you use code generation (`json_serializable`), the generated code will use these values when reading from or writing to JSON.

---

## **Example**

```dart
import 'package:json_annotation/json_annotation.dart';

@JsonEnum()
enum AreaOfLife {
  @JsonValue('health_fitness')
  healthFitness,
  @JsonValue('productivity_focus')
  productivityFocus,
  // ... more values
}
```

**How it works:**
- When serializing, `AreaOfLife.healthFitness` becomes `"health_fitness"` in JSON.
- When deserializing, `"productivity_focus"` in JSON becomes `AreaOfLife.productivityFocus` in Dart.

---


