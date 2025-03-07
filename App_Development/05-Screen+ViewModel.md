# Screen and ViewModel Template

## Screen with Navigation

```kotlin
@Composable
fun IntroScreen(navController: NavController) {
// Content here
}

```

## ViewModel

```kotlin
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(

) : ViewModel() {
 // ViewModel logic here
}
```