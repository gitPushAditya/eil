# Typography

In `theme/type` add `Type.kt` to store the default values of all the fonts.

_Note: This is a custom designed fontsize, which you can further customise. Just make sure to do it in the starting of project, unless you want to mess everything up._

```kotlin

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

fun getLineSpacing(fontSize: TextUnit): TextUnit{
	return fontSize*1.5f
}


val Typography = Typography(
	displayLarge = Typography().displayLarge.copy(
		fontSize = 32.sp,
		lineHeight = getLineSpacing(32.sp)
	),
	displayMedium = Typography().displayMedium.copy(
		fontSize = 30.sp,
		lineHeight = getLineSpacing(30.sp)
	),
	displaySmall = Typography().displaySmall.copy(
		fontSize = 28.sp,
		lineHeight = getLineSpacing(28.sp)
	),
	headlineLarge = Typography().headlineLarge.copy(
		fontSize = 26.sp,
		lineHeight = getLineSpacing(26.sp)
	),
	headlineMedium = Typography().headlineMedium.copy(
		fontSize = 24.sp,
		lineHeight = getLineSpacing(24.sp)
	),
	headlineSmall = Typography().headlineSmall.copy(
		fontSize = 22.sp,
		lineHeight = getLineSpacing(22.sp)
	),
	titleLarge = Typography().titleLarge.copy(
		fontSize = 22.sp,
		lineHeight = getLineSpacing(22.sp)
	),
	titleMedium = Typography().titleMedium.copy(
		fontSize = 20.sp,
		lineHeight = getLineSpacing(20.sp)
	),
	titleSmall = Typography().titleSmall.copy(
		fontSize = 18.sp,
		lineHeight = getLineSpacing(18.sp)
	),
	bodyLarge = Typography().bodyLarge.copy(
		fontSize = 18.sp,
		lineHeight = getLineSpacing(18.sp)
	),
	bodyMedium = Typography().bodyMedium.copy(
		fontSize = 16.sp,
		lineHeight = getLineSpacing(16.sp)
	),
	bodySmall = Typography().bodySmall.copy(
		fontSize = 14.sp,
		lineHeight = getLineSpacing(14.sp)
	),
	labelLarge = Typography().labelLarge.copy(
		fontSize = 14.sp,
		lineHeight = getLineSpacing(14.sp)
	),
	labelMedium = Typography().labelMedium.copy(
		fontSize = 12.sp,
		lineHeight = getLineSpacing(12.sp)
	),
	labelSmall = Typography().labelSmall.copy(
		fontSize = 10.sp,
		lineHeight = getLineSpacing(10.sp)
	)
)
```
