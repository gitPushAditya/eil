# Screen and ViewModel Template

## Screen with Navigation

```kt
@Composable
fun IntroScreen(navController: NavController) {
// Content here
}
```

---

## ViewModel

```kt
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(

) : ViewModel() {
 // ViewModel logic here
}
```