```dart
// models/task_model.dart
class Task {
  final int? id;
  final String title;
  final bool isCompleted;

  Task({this.id, required this.title, this.isCompleted = false});

  // Convert a Task into a Map. The Map is used as the data source for Sqflite.
  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'title': title,
      'isCompleted': isCompleted ? 1 : 0, // SQLite expects integer for boolean
    };
  }

  // Convert a Map into a Task.
  factory Task.fromMap(Map<String, dynamic> map) {
    return Task(
      id: map['id'],
      title: map['title'],
      isCompleted: map['isCompleted'] == 1,
    );
  }
}

```