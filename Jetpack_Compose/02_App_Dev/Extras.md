# Extras

## Handle Back Press

```kotlin
BackHandler {
        viewModel.setPreviousPage() // any function
    }
```

## Close the App 

```kotlin
val activity = LocalContext.current as? Activity

    BackHandler {
        activity?.finishAffinity()
    }
```

## Change Status Bar Color 

```kotlin
val systemUiController = rememberSystemUiController()

    systemUiController.setStatusBarColor(
        color = Color.Transparent,
        darkIcons = true
    )
```