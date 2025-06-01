# Accompanist System UI Controller

This library can be used for multiple system ui related tasks like - 

1. **Change Status Bar Color**
2. **Change Navigation Bar Color**
3. **Change Icon Color (Light/Dark) in Bars**
4. **Control System Bars Visibility**
5. **Control Other Window Insets**

```kotlin
accompanistSystemuicontroller = "0.29.2-rc"
```

```kotlin
accompanist-systemuicontroller = { group = "com.google.accompanist", name = "accompanist-systemuicontroller", version.ref = "accompanistSystemuicontroller" }
```

---

## 1. Change Status Bar Color

You can dynamically change the status bar color based on your app's theme, screen, or user interaction.

**Example:**

```kotlin
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MyScreen() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colorScheme.isLight

    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(
            color = Color.Red,
            darkIcons = useDarkIcons
        )
    }

    // Screen content
}
```

---

## 2. Change Navigation Bar Color

Set the color of the navigation bar (the bar at the bottom of the screen).

**Example:**

```kotlin
LaunchedEffect(Unit) {
    systemUiController.setNavigationBarColor(
        color = Color.Black,
        darkIcons = false
    )
}
```

---

## 3. Control Icon Color (Light/Dark)

Switch between light and dark icons to maintain contrast.

**Example:**

```kotlin
LaunchedEffect(Unit) {
    systemUiController.setStatusBarColor(
        color = Color.White,
        darkIcons = true // Use dark icons for better contrast on light background
    )
}
```

---

## 4. Hide/Show System Bars

You can hide or show the system bars for immersive experiences.

**Example:**

```kotlin
LaunchedEffect(Unit) {
    // Hide both status and navigation bars
    systemUiController.systemBarsBehavior = SystemBarsBehavior.Immersive
    systemUiController.isSystemBarsVisible = false
}

// To restore:
LaunchedEffect(Unit) {
    systemUiController.isSystemBarsVisible = true
}
```

---

## 5. Use Cases in Real Apps

- **Splash Screens:** Set specific system bar colors for launch.
- **Themed Apps:** Change bar colors with dark/light themes.
- **Media/Full-Screen Mode:** Hide system bars for videos or games.
- **Dynamic Effects:** Animate bar colors based on scroll or actions.

---

## Usage Pattern

- Add the dependency:
  ```gradle
  implementation "com.google.accompanist:accompanist-systemuicontroller:<version>"
  ```
- Use `rememberSystemUiController()` in your composables.
- Apply color/icon/visibility changes using its functions.

---

## Notes

- Should be used inside a `Composable`.
- Remember to revert changes if needed (for example, when navigating away from a screen).
- Works best with Compose Navigation for handling per-screen changes.

---

