# Providers

```
flutter pub add provider
```

## Providers Usage

In Flutter, the `provider` package offers several ways to access and use data from providers. Each method has its use cases and benefits depending on the context of your app. Here's a detailed overview of the different methods and when to use each:

### 1. **`Provider.of<T>(context)`**

**Description**: Directly access the value of a provider from the widget tree.

**Usage**:

```dart
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:simply_do/core/app_state.dart';

class SomeWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final appState = Provider.of<AppState>(context); // Access the provider
    return Text('Current Theme: ${appState.theme.brightness}');
  }
}
```

**When to Use**:

- When you need to access the provider value in a widget without rebuilding the widget when the provider value changes.
- Suitable for non-rebuilding or non-listening scenarios where you just need to access the data.

### 2. **`Consumer<T>`**

**Description**: A widget that listens to changes in the provider and rebuilds when the provider value changes.

**Usage**:

```dart
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:simply_do/core/app_state.dart';

class SomeWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Consumer<AppState>(
      builder: (context, appState, child) {
        return Text('Current Theme: ${appState.theme.brightness}');
      },
    );
  }
}
```

**When to Use**:

- When you need to rebuild part of the widget tree when the provider value changes.
- Useful for widgets that need to be responsive to state changes.

### 3. **`Selector<T, R>`**

**Description**: A widget that listens to changes in the provider and rebuilds only if the selected value changes, which helps in optimizing rebuilds.

**Usage**:

```dart
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:simply_do/core/app_state.dart';

class SomeWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Selector<AppState, Brightness>(
      selector: (context, appState) => appState.theme.brightness,
      builder: (context, brightness, child) {
        return Text('Current Brightness: $brightness');
      },
    );
  }
}
```

**When to Use**:

- When you only need a specific part of the provider’s state and want to optimize widget rebuilds by avoiding unnecessary updates.
- Useful for large provider values where you only care about a small subset of the state.

### 4. **`context.watch<T>()`**

**Description**: A shorthand for `Provider.of<T>(context, listen: true)`, automatically listening to changes in the provider.

**Usage**:

```dart
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:simply_do/core/app_state.dart';

class SomeWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final appState = context.watch<AppState>(); // Listen to changes
    return Text('Current Theme: ${appState.theme.brightness}');
  }
}
```

**When to Use**:

- When you need to access and listen to provider changes within the widget.
- Provides a more concise syntax compared to `Provider.of`.

### 5. **`context.read<T>()`**

**Description**: A shorthand for `Provider.of<T>(context, listen: false)`, used for accessing the provider without listening for changes.

**Usage**:

```dart
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:simply_do/core/app_state.dart';

class SomeWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final appState = context.read<AppState>(); // Access without listening
    return ElevatedButton(
      onPressed: () {
        appState.setTheme(AppThemes.lightPurpleTheme);
      },
      child: Text('Switch to Light Purple Theme'),
    );
  }
}
```

**When to Use**:

- When you need to access the provider but do not need to rebuild the widget when the provider value changes.
- Useful for accessing the provider within event handlers or functions where no widget rebuild is required.

### **Summary**

- **`Provider.of<T>(context)`**: Use when you need to access a provider’s value without listening for changes.
- **`Consumer<T>`**: Use when you need to rebuild parts of the widget tree in response to changes in the provider.
- **`Selector<T, R>`**: Use when you need to listen to a specific part of the provider’s state to optimize rebuilds.
- **`context.watch<T>()`**: Use for a concise way to access and listen to provider changes.
- **`context.read<T>()`**: Use for accessing provider methods or values without listening for changes.

Choose the method based on whether you need to listen to changes, optimize rebuilds, or simply access the provider’s value or methods.

---
