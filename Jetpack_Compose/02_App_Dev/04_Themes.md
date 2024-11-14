# Themes

## Color Scheme

*Note: These are custom designed color schemes, you can use your own colors in similar format.* 

Add a file `AppSchemes` in `theme/schemes` 

```kotlin
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

sealed class AppSchemes(val colors: ColorScheme) {
	object ClassicBlueLightTheme : AppSchemes(
		colors = lightColorScheme(
			primary = Color(0xFF050D19),
			secondary = Color(0xFF0A1A33),
			tertiary = Color(0xFF0F274C),
			
			primaryContainer = Color(0xFF2D77E5),
			secondaryContainer = Color(0xFF2D58E5),
			tertiaryContainer = Color(0xFF2D95E5),
			
			onPrimary = Color(0xFF0A1019),
			onSecondary = Color(0xFF142033),
			onTertiary = Color(0xFF1E304C),
			
			onPrimaryContainer = Color(0xFF000000),
			onSecondaryContainer = Color(0xFF161719),
			onTertiaryContainer = Color(0xFF282C33),
			
			surface = Color(0xFFB7C9E5),
			onSurface = Color(0xFF000000),
			
			surfaceDim = Color(0xFFA3B3CC),
			surfaceBright = Color(0xFFCCE0FF),
			
			background = Color(0xFFFFFFFF),
			onBackground = Color(0xFF1E2633),
			
			surfaceTint = Color(0xFFCCE0FF),
		)
	)
	
	object ClassicBlueDarkTheme : AppSchemes(
		colors = darkColorScheme(
			primary = Color(0xFF2D77E5),
			secondary = Color(0xFF286ACC),
			tertiary = Color(0xFF235CB2),
			
			primaryContainer = Color(0xFF0F274C),
			secondaryContainer = Color(0xFF0F1D4C),
			tertiaryContainer = Color(0xFF0F314C),
			
			onPrimary = Color(0xFF5B92E5),
			onSecondary = Color(0xFF5182CC),
			onTertiary = Color(0xFF4772B2),
			
			onPrimaryContainer = Color(0xFFFFFFFF),
			onSecondaryContainer = Color(0xFFCED7E5),
			onTertiaryContainer = Color(0xFFB7BFCC),
			
			surface = Color(0xFF141619),
			onSurface = Color(0xFFFFFFFF),
			
			surfaceDim = Color(0xFF0A0B0C),
			surfaceBright = Color(0xFF282C33),
			
			background = Color(0xFF0F1319),
			onBackground = Color(0xFF89AEE5),
			
			surfaceTint = Color(0xFF66707F),
		)
	)
	
	object CoralLightTheme : AppSchemes(
		colors = lightColorScheme(
			primary = Color(0xFF190605),
			secondary = Color(0xFF330D0A),
			tertiary = Color(0xFF4C140F),
			
			primaryContainer = Color(0xFFE53D2D),
			secondaryContainer = Color(0xFFE55B2D),
			tertiaryContainer = Color(0xFFE55B2D),
			
			onPrimary = Color(0xFF190B0A),
			onSecondary = Color(0xFF331614),
			onTertiary = Color(0xFF4C221E),
			
			onPrimaryContainer = Color(0xFF000000),
			onSecondaryContainer = Color(0xFF191716),
			onTertiaryContainer = Color(0xFF332928),
			
			surface = Color(0xFFE5BBB7),
			onSurface = Color(0xFF000000),
			
			surfaceDim = Color(0xFFCCA6A3),
			surfaceBright = Color(0xFFFFD0CC),
			
			background = Color(0xFFFFFFFF),
			onBackground = Color(0xFF33201E),
			
			surfaceTint = Color(0xFFFFD0CC),
		)
	)
	
	object CoralDarkTheme : AppSchemes(
		colors = darkColorScheme(
			primary = Color(0xFFE53D2D),
			secondary = Color(0xFFCC3628),
			tertiary = Color(0xFFB22F23),
			
			primaryContainer = Color(0xFF4C140F),
			secondaryContainer = Color(0xFF4C1E0F),
			tertiaryContainer = Color(0xFF4C1E0F),
			
			onPrimary = Color(0xFFE5675B),
			onSecondary = Color(0xFFCC5B51),
			onTertiary = Color(0xFFB25047),
			
			onPrimaryContainer = Color(0xFFFFFFFF),
			onSecondaryContainer = Color(0xFFE5D0CE),
			onTertiaryContainer = Color(0xFFCCB9B7),
			
			surface = Color(0xFF191414),
			onSurface = Color(0xFFFFFFFF),
			
			surfaceDim = Color(0xFF0C0A0A),
			surfaceBright = Color(0xFF332928),
			
			background = Color(0xFF19100F),
			onBackground = Color(0xFFE59189),
			
			surfaceTint = Color(0xFF7F6866),
		)
	)
	
	object MintGreenLightTheme : AppSchemes(
		colors = lightColorScheme(
			primary = Color(0xFF051912),
			secondary = Color(0xFF0A3324),
			tertiary = Color(0xFF0F4C36),
			
			primaryContainer = Color(0xFF2DE5A2),
			secondaryContainer = Color(0xFF2DE5C0),
			tertiaryContainer = Color(0xFF2DE583),
			
			onPrimary = Color(0xFF0A1913),
			onSecondary = Color(0xFF143327),
			onTertiary = Color(0xFF1E4C3B),
			
			onPrimaryContainer = Color(0xFF000000),
			onSecondaryContainer = Color(0xFF161918),
			onTertiaryContainer = Color(0xFF28332F),
			
			surface = Color(0xFFB7E5D4),
			onSurface = Color(0xFF000000),
			
			surfaceDim = Color(0xFFA3CCBD),
			surfaceBright = Color(0xFFCCFFEC),
			
			background = Color(0xFFFFFFFF),
			onBackground = Color(0xFF1E332B),
			
			surfaceTint = Color(0xFFCCFFEC),
		)
	)
	
	object MintGreenDarkTheme : AppSchemes(
		colors = darkColorScheme(
			primary = Color(0xFF2DE5A2),
			secondary = Color(0xFF28CC90),
			tertiary = Color(0xFF23B27E),
			
			primaryContainer = Color(0xFF0F4C36),
			secondaryContainer = Color(0xFF0F4C40),
			tertiaryContainer = Color(0xFF0F4C2B),
			
			onPrimary = Color(0xFF5BE5B3),
			onSecondary = Color(0xFF51CC9F),
			onTertiary = Color(0xFF47B28B),
			
			onPrimaryContainer = Color(0xFFFFFFFF),
			onSecondaryContainer = Color(0xFFCEE5DD),
			onTertiaryContainer = Color(0xFFB7CCC4),
			
			surface = Color(0xFF141917),
			onSurface = Color(0xFFFFFFFF),
			
			surfaceDim = Color(0xFF0A0C0B),
			surfaceBright = Color(0xFF28332F),
			
			background = Color(0xFF0F1915),
			onBackground = Color(0xFF89E5C3),
			
			surfaceTint = Color(0xFF667F76),
		)
	)
	
	object SoftLavenderLightTheme : AppSchemes(
		colors = lightColorScheme(
			primary = Color(0xFF0F0519),
			secondary = Color(0xFF1F0A33),
			tertiary = Color(0xFF2E0F4C),
			
			primaryContainer = Color(0xFF8C2DE5),
			secondaryContainer = Color(0xFFAB2DE5),
			tertiaryContainer = Color(0xFF6E2DE5),
			
			onPrimary = Color(0xFF120A19),
			onSecondary = Color(0xFF241433),
			onTertiary = Color(0xFF361E4C),
			
			onPrimaryContainer = Color(0xFF000000),
			onSecondaryContainer = Color(0xFF181619),
			onTertiaryContainer = Color(0xFF2E2833),
			
			surface = Color(0xFFCFB7E5),
			onSurface = Color(0xFF000000),
			
			surfaceDim = Color(0xFFB8A3CC),
			surfaceBright = Color(0xFFE6CCFF),
			
			background = Color(0xFFFFFFFF),
			onBackground = Color(0xFF291E33),
			
			surfaceTint = Color(0xFFE6CCFF),
		)
	)
	
	object SoftLavenderDarkTheme : AppSchemes(
		colors = darkColorScheme(
			primary = Color(0xFF8C2DE5),
			secondary = Color(0xFF7D28CC),
			tertiary = Color(0xFF6D23B2),
			
			primaryContainer = Color(0xFF2E0F4C),
			secondaryContainer = Color(0xFF390F4C),
			tertiaryContainer = Color(0xFF240F4C),
			
			onPrimary = Color(0xFFA25BE5),
			onSecondary = Color(0xFF9051CC),
			onTertiary = Color(0xFF7E47B2),
			
			onPrimaryContainer = Color(0xFFFFFFFF),
			onSecondaryContainer = Color(0xFFDACEE5),
			onTertiaryContainer = Color(0xFFC2B7CC),
			
			surface = Color(0xFF171419),
			onSurface = Color(0xFFFFFFFF),
			
			surfaceDim = Color(0xFF0B0A0C),
			surfaceBright = Color(0xFF2E2833),
			
			background = Color(0xFF140F19),
			onBackground = Color(0xFFB989E5),
			
			surfaceTint = Color(0xFF73667F),
		)
	)
	
	object DustyRoseLightTheme : AppSchemes(
		colors = lightColorScheme(
			primary = Color(0xFF190505),
			secondary = Color(0xFF330A0A),
			tertiary = Color(0xFF4C0F10),
			
			primaryContainer = Color(0xFFE52D30),
			secondaryContainer = Color(0xFFE52D4F),
			tertiaryContainer = Color(0xFFE52D4F),
			
			onPrimary = Color(0xFF190A0A),
			onSecondary = Color(0xFF331414),
			onTertiary = Color(0xFF4C1E1F),
			
			onPrimaryContainer = Color(0xFF000000),
			onSecondaryContainer = Color(0xFF191616),
			onTertiaryContainer = Color(0xFF332828),
			
			surface = Color(0xFFE5B7B8),
			onSurface = Color(0xFF000000),
			
			surfaceDim = Color(0xFFCCA3A3),
			surfaceBright = Color(0xFFFFCCCC),
			
			background = Color(0xFFFFFFFF),
			onBackground = Color(0xFF331E1E),
			
			surfaceTint = Color(0xFFFFCCCC),
		)
	)
	
	object DustyRoseDarkTheme : AppSchemes(
		colors = darkColorScheme(
			primary = Color(0xFFE52D30),
			secondary = Color(0xFFCC282B),
			tertiary = Color(0xFFB22326),
			
			primaryContainer = Color(0xFF4C0F10),
			secondaryContainer = Color(0xFF4C0F1A),
			tertiaryContainer = Color(0xFF4C0F1A),
			
			onPrimary = Color(0xFFE55B5E),
			onSecondary = Color(0xFFCC5153),
			onTertiary = Color(0xFFB24749),
			
			onPrimaryContainer = Color(0xFFFFFFFF),
			onSecondaryContainer = Color(0xFFE5CECE),
			onTertiaryContainer = Color(0xFFCCB7B7),
			
			surface = Color(0xFF191414),
			onSurface = Color(0xFFFFFFFF),
			
			surfaceDim = Color(0xFF0C0A0A),
			surfaceBright = Color(0xFF332828),
			
			background = Color(0xFF190F0F),
			onBackground = Color(0xFFE5898B),
			
			surfaceTint = Color(0xFF7F6666),
		)
	)
	
	object RichCharcoalLightTheme : AppSchemes(
		colors = lightColorScheme(
			primary = Color(0xFF190505),
			secondary = Color(0xFF330A0A),
			tertiary = Color(0xFF4C0F0F),
			
			primaryContainer = Color(0xFFE52D2D),
			secondaryContainer = Color(0xFFE54C2D),
			tertiaryContainer = Color(0xFFE54C2D),
			
			onPrimary = Color(0xFF190A0A),
			onSecondary = Color(0xFF331414),
			onTertiary = Color(0xFF4C1E1E),
			
			onPrimaryContainer = Color(0xFF000000),
			onSecondaryContainer = Color(0xFF191616),
			onTertiaryContainer = Color(0xFF332828),
			
			surface = Color(0xFFE5B7B7),
			onSurface = Color(0xFF000000),
			
			surfaceDim = Color(0xFFCCA3A3),
			surfaceBright = Color(0xFFFFCCCC),
			
			background = Color(0xFFFFFFFF),
			onBackground = Color(0xFF331E1E),
			
			surfaceTint = Color(0xFFFFCCCC),
		)
	)
	
	object RichCharcoalDarkTheme : AppSchemes(
		colors = darkColorScheme(
			primary = Color(0xFFE52D2D),
			secondary = Color(0xFFCC2828),
			tertiary = Color(0xFFB22323),
			
			primaryContainer = Color(0xFF4C0F0F),
			secondaryContainer = Color(0xFF4C190F),
			tertiaryContainer = Color(0xFF4C190F),
			
			onPrimary = Color(0xFFE55B5B),
			onSecondary = Color(0xFFCC5151),
			onTertiary = Color(0xFFB24747),
			
			onPrimaryContainer = Color(0xFFFFFFFF),
			onSecondaryContainer = Color(0xFFE5CECE),
			onTertiaryContainer = Color(0xFFCCB7B7),
			
			surface = Color(0xFF191414),
			onSurface = Color(0xFFFFFFFF),
			
			surfaceDim = Color(0xFF0C0A0A),
			surfaceBright = Color(0xFF332828),
			
			background = Color(0xFF190F0F),
			onBackground = Color(0xFFE58989),
			
			surfaceTint = Color(0xFF7F6666),
		)
	)
	
	object SunsetOrangeLightTheme : AppSchemes(
		colors = lightColorScheme(
			primary = Color(0xFF190B05),
			secondary = Color(0xFF33170A),
			tertiary = Color(0xFF4C220F),
			
			primaryContainer = Color(0xFFE5682D),
			secondaryContainer = Color(0xFFE5862D),
			tertiaryContainer = Color(0xFFE5492D),
			
			onPrimary = Color(0xFF190F0A),
			onSecondary = Color(0xFF331E14),
			onTertiary = Color(0xFF4C2D1E),
			
			onPrimaryContainer = Color(0xFF000000),
			onSecondaryContainer = Color(0xFF191716),
			onTertiaryContainer = Color(0xFF332C28),
			
			surface = Color(0xFFE5C6B7),
			onSurface = Color(0xFF000000),
			
			surfaceDim = Color(0xFFCCB0A3),
			surfaceBright = Color(0xFFFFDCCC),
			
			background = Color(0xFFFFFFFF),
			onBackground = Color(0xFF33251E),
			
			surfaceTint = Color(0xFFFFDCCC),
		)
	)
	
	object SunsetOrangeDarkTheme : AppSchemes(
		colors = darkColorScheme(
			primary = Color(0xFFE5682D),
			secondary = Color(0xFFCC5C28),
			tertiary = Color(0xFFB25023),
			
			primaryContainer = Color(0xFF4C220F),
			secondaryContainer = Color(0xFF4C2C0F),
			tertiaryContainer = Color(0xFF4C180F),
			
			onPrimary = Color(0xFFE5875B),
			onSecondary = Color(0xFFCC7851),
			onTertiary = Color(0xFFB26947),
			
			onPrimaryContainer = Color(0xFFFFFFFF),
			onSecondaryContainer = Color(0xFFE5D5CE),
			onTertiaryContainer = Color(0xFFCCBEB7),
			
			surface = Color(0xFF191614),
			onSurface = Color(0xFFFFFFFF),
			
			surfaceDim = Color(0xFF0C0B0A),
			surfaceBright = Color(0xFF332C28),
			
			background = Color(0xFF19120F),
			onBackground = Color(0xFFE5A689),
			
			surfaceTint = Color(0xFF7F6E66),
		)
	)
	
	object DeepTealLightTheme : AppSchemes(
		colors = lightColorScheme(
			primary = Color(0xFF051917),
			secondary = Color(0xFF0A332E),
			tertiary = Color(0xFF0F4C45),
			
			primaryContainer = Color(0xFF2DE5D0),
			secondaryContainer = Color(0xFF2DDCE5),
			tertiaryContainer = Color(0xFF2DE5B1),
			
			onPrimary = Color(0xFF0A1917),
			onSecondary = Color(0xFF14332F),
			onTertiary = Color(0xFF1E4C47),
			
			onPrimaryContainer = Color(0xFF000000),
			onSecondaryContainer = Color(0xFF161919),
			onTertiaryContainer = Color(0xFF283331),
			
			surface = Color(0xFFB7E5E0),
			onSurface = Color(0xFF000000),
			
			surfaceDim = Color(0xFFA3CCC7),
			surfaceBright = Color(0xFFCCFFF9),
			
			background = Color(0xFFFFFFFF),
			onBackground = Color(0xFF1E3330),
			
			surfaceTint = Color(0xFFCCFFF9),
		)
	)
	
	object DeepTealDarkTheme : AppSchemes(
		colors = darkColorScheme(
			primary = Color(0xFF2DE5D0),
			secondary = Color(0xFF28CCB8),
			tertiary = Color(0xFF23B2A1),
			
			primaryContainer = Color(0xFF0F4C45),
			secondaryContainer = Color(0xFF0F494C),
			tertiaryContainer = Color(0xFF0F4C3B),
			
			onPrimary = Color(0xFF5BE5D5),
			onSecondary = Color(0xFF51CCBD),
			onTertiary = Color(0xFF47B2A6),
			
			onPrimaryContainer = Color(0xFFFFFFFF),
			onSecondaryContainer = Color(0xFFCEE5E2),
			onTertiaryContainer = Color(0xFFB7CCC9),
			
			surface = Color(0xFF141918),
			onSurface = Color(0xFFFFFFFF),
			
			surfaceDim = Color(0xFF0A0C0C),
			surfaceBright = Color(0xFF283331),
			
			background = Color(0xFF0F1918),
			onBackground = Color(0xFF89E5DA),
			
			surfaceTint = Color(0xFF667F7C),
		)
	)
	
	object GoldenYellowLightTheme : AppSchemes(
		colors = lightColorScheme(
			primary = Color(0xFF191405),
			secondary = Color(0xFF33280A),
			tertiary = Color(0xFF4C3D0F),
			
			primaryContainer = Color(0xFFE5B72D),
			secondaryContainer = Color(0xFFE5D62D),
			tertiaryContainer = Color(0xFFE5992D),
			
			onPrimary = Color(0xFF19150A),
			onSecondary = Color(0xFF332B14),
			onTertiary = Color(0xFF4C411E),
			
			onPrimaryContainer = Color(0xFF000000),
			onSecondaryContainer = Color(0xFF191816),
			onTertiaryContainer = Color(0xFF333028),
			
			surface = Color(0xFFE5DAB7),
			onSurface = Color(0xFF000000),
			
			surfaceDim = Color(0xFFCCC1A3),
			surfaceBright = Color(0xFFFFF2CC),
			
			background = Color(0xFFFFFFFF),
			onBackground = Color(0xFF332D1E),
			
			surfaceTint = Color(0xFFFFF2CC),
		)
	)
	
	object GoldenYellowDarkTheme : AppSchemes(
		colors = darkColorScheme(
			primary = Color(0xFFE5B72D),
			secondary = Color(0xFFCCA328),
			tertiary = Color(0xFFB28E23),
			
			primaryContainer = Color(0xFF4C3D0F),
			secondaryContainer = Color(0xFF4C470F),
			tertiaryContainer = Color(0xFF4C330F),
			
			onPrimary = Color(0xFFE5C35B),
			onSecondary = Color(0xFFCCAD51),
			onTertiary = Color(0xFFB29747),
			
			onPrimaryContainer = Color(0xFFFFFFFF),
			onSecondaryContainer = Color(0xFFE5DFCE),
			onTertiaryContainer = Color(0xFFCCC6B7),
			
			surface = Color(0xFF191814),
			onSurface = Color(0xFFFFFFFF),
			
			surfaceDim = Color(0xFF0C0C0A),
			surfaceBright = Color(0xFF333028),
			
			background = Color(0xFF19160F),
			onBackground = Color(0xFFE5CE89),
			
			surfaceTint = Color(0xFF7F7966),
		)
	)
	
	object SlateBlueLightTheme : AppSchemes(
		colors = lightColorScheme(
			primary = Color(0xFF050F19),
			secondary = Color(0xFF0A1F33),
			tertiary = Color(0xFF0F2E4C),
			
			primaryContainer = Color(0xFF2D8CE5),
			secondaryContainer = Color(0xFF2D6EE5),
			tertiaryContainer = Color(0xFF2DABE5),
			
			onPrimary = Color(0xFF0A1219),
			onSecondary = Color(0xFF142433),
			onTertiary = Color(0xFF1E364C),
			
			onPrimaryContainer = Color(0xFF000000),
			onSecondaryContainer = Color(0xFF161819),
			onTertiaryContainer = Color(0xFF282E33),
			
			surface = Color(0xFFB7CFE5),
			onSurface = Color(0xFF000000),
			
			surfaceDim = Color(0xFFA3B8CC),
			surfaceBright = Color(0xFFCCE6FF),
			
			background = Color(0xFFFFFFFF),
			onBackground = Color(0xFF1E2933),
			
			surfaceTint = Color(0xFFCCE6FF),
		)
	)
	
	object SlateBlueDarkTheme : AppSchemes(
		colors = darkColorScheme(
			primary = Color(0xFF2D8CE5),
			secondary = Color(0xFF287DCC),
			tertiary = Color(0xFF236DB2),
			
			primaryContainer = Color(0xFF0F2E4C),
			secondaryContainer = Color(0xFF0F244C),
			tertiaryContainer = Color(0xFF0F394C),
			
			onPrimary = Color(0xFF5BA2E5),
			onSecondary = Color(0xFF5190CC),
			onTertiary = Color(0xFF477EB2),
			
			onPrimaryContainer = Color(0xFFFFFFFF),
			onSecondaryContainer = Color(0xFFCEDAE5),
			onTertiaryContainer = Color(0xFFB7C2CC),
			
			surface = Color(0xFF141719),
			onSurface = Color(0xFFFFFFFF),
			
			surfaceDim = Color(0xFF0A0B0C),
			surfaceBright = Color(0xFF282E33),
			
			background = Color(0xFF0F1419),
			onBackground = Color(0xFF89B9E5),
			
			surfaceTint = Color(0xFF66737F),
		)
	)
	
}
```

---

## App Theme

Now add this color scheme to `AppTheme`

*Note: In case of multiple color schemes where you want user to select the theme, you won't hardcode colorscheme name, instead you will get it from datastore.*

```kotlin

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.visionforgestudio.drinkup.ui.type.Typography


@Composable
fun DrinkUpTheme(
	darkTheme: Boolean = isSystemInDarkTheme(),
	dynamicColor: Boolean = false,
	content: @Composable () -> Unit
) {
	val colorScheme = when {
		dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
			val context = LocalContext.current
			if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
		}
		
		// TODO: Change this later based on datastore
		darkTheme -> AppSchemes.ClassicBlueDarkTheme.colors
		else -> AppSchemes.ClassicBlueLightTheme.colors
	}
	
	MaterialTheme(
		colorScheme = colorScheme,
		typography = Typography,
		content = content
	)
}
```