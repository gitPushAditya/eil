# App Screens

Create All the screens with basic layout. 

## Scaffold

*Note: If you are using `jcpackages`, you can use premade Scaffold.*

Here is a base layout of it. 

```kotlin
@Composable
fun JcScaffold(
	modifier: Modifier = Modifier,
	topBar: @Composable (() -> Unit) = {},
	bottomBar: @Composable (() -> Unit) = {},
	snackbarHost: @Composable (() -> Unit) = {},
	floatingActionButton: @Composable (() -> Unit) = {},
	floatingActionButtonPosition: FabPosition = FabPosition.End,
	containerColor: Color = MaterialTheme.colorScheme.background,
	contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
	contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
	content: @Composable ((PaddingValues) -> Unit)
) {
	Scaffold(
		modifier = modifier,
		topBar = topBar,
		bottomBar = bottomBar,
		snackbarHost = snackbarHost,
		floatingActionButton = floatingActionButton,
		floatingActionButtonPosition = floatingActionButtonPosition,
		containerColor = containerColor,
		contentColor = contentColor,
		contentWindowInsets = contentWindowInsets,
	) {
			
			contentPadding ->
		Box(
			modifier = Modifier
				.padding(contentPadding)
				.fillMaxSize()
		) {
			content(contentPadding)
		}
	}
}
```
---

## An Empty Screen

```kotlin
package com.visionforgestudio.drinkup.ui.screens.homescreen

import androidx.compose.runtime.Composable

@Composable
fun HomeScreen() {
    // TODO: Implement HomeScreen composable
}
```

---


## Define Constants

To make all the screens easily accessible, we will create a sealed class in `constants`.

```kotlin
sealed class AppScreens(val route: String){
	object IntroScreen : AppScreens("intro_screen")
	object HomeScreen : AppScreens("home_screen")
	object TimelineScreen : AppScreens("timeline_screen")
	object StatisticsScreen : AppScreens("statistics_screen")
	object PreferencesScreen : AppScreens("preferences_screen")
	object SettingsScreen : AppScreens("settings_screen")
	object NotificationScreen : AppScreens("notification_screen")
	object ThemeScreen : AppScreens("theme_screen")
	object AccountScreen : AppScreens("account_screen")
	object AboutScreen : AppScreens("about_screen")
}
```

