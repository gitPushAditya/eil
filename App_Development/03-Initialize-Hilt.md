# Initialize Hilt

## Creating Application Class

```kotlin
import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HabitFlowApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
```

## Manifest

```xml
android:name=".HabitFlowApp"
```

## Entry Point

```kotlin
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Content here
        }
    }
}
```
