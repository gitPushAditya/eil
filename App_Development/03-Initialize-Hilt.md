# Initialize Hilt

## Creating Application Class

```kt
import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HabitFlowApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
```

---

## Manifest

```kt
android:name=".HabitFlowApp"
```

---

## Entry Point

```kt
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

---