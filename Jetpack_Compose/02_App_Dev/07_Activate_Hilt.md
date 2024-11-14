# Hilt

We have already added hilt in packages. It's time to activate it. 

In root directory, right beside `MainActivity`, create another class with `{AppName}App`

```kotlin
import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DrinkUpApp : Application() {
	override fun onCreate() {
		super.onCreate()
		// Other Initialisation code for ads and notification channels
	}
}
```

Now we need to add this file to `Manifest` so that app can recognise it. 

