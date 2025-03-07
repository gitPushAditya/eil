# ColorScheme

```kotlin

private val lightScheme = lightColorScheme(
    primary = Color(0xFF008080),
    secondary = Color(0xFF4DB3B3),
    tertiary = Color(0xFF99CCCC),

    onPrimary = Color(0xFF000000),
    onSecondary = Color(0xFF1A1A1A),
    onTertiary = Color(0xFF333333),

    primaryContainer = Color(0xFF7FBFBF),
    secondaryContainer = Color(0xFFB3D9D9),
    tertiaryContainer = Color(0xFFCCE6E6),

    onPrimaryContainer = Color(0xFF1A1A1A),
    onSecondaryContainer = Color(0xFF333333),
    onTertiaryContainer = Color(0xFF4D4D4D),

    error = Color(0xFFFF453A),
    errorContainer = Color(0xFFFF7F7F),

    onError = Color(0xFF1A1A1A),
    onErrorContainer = Color(0xFF1A1A1A),

    background = Color(0xFFF0F5F5),
    surface = Color(0xFFE6EBEB),

    onBackground = Color(0xFF000000),
    onSurface = Color(0xFF1A1A1A),

    outline = Color(0xFFCCCCCC),
    outlineVariant = Color(0xFFE6E6E6),

    surfaceVariant = Color(0xFFE6EBEB),
    onSurfaceVariant = Color(0xFF1A1A1A),

    inverseSurface = Color(0xFF1A1A1A),
    inverseOnSurface = Color(0xFFE6EBEB),
    inversePrimary = Color(0xFF7FBFBF),

    scrim = Color(0xFF1A1A1A),

    surfaceDim = Color(0xFFCCCCCC),
    surfaceBright = Color(0xFFFFFFFF),
    surfaceContainerLowest = Color(0xFFFFFFFF),
    surfaceContainerLow = Color(0xFFF0F5F5),
    surfaceContainerHigh = Color(0xFFE6EBEB),
    surfaceContainerHighest = Color(0xFFCCCCCC),
)

private val darkScheme = darkColorScheme(
    primary = Color(0xFF007373),
    secondary = Color(0xFF339999),
    tertiary = Color(0xFF66B3B3),

    onPrimary = Color(0xFFFFFFFF),
    onSecondary = Color(0xFFE6E6E6),
    onTertiary = Color(0xFFCCCCCC),

    primaryContainer = Color(0xFF4D9999),
    secondaryContainer = Color(0xFF66B3B3),
    tertiaryContainer = Color(0xFF7FBFBF),

    onPrimaryContainer = Color(0xFFE6E6E6),
    onSecondaryContainer = Color(0xFFCCCCCC),
    onTertiaryContainer = Color(0xFFB3B3B3),

    error = Color(0xFFFF453A),
    errorContainer = Color(0xFFCC6666),

    onError = Color(0xFFE6E6E6),
    onErrorContainer = Color(0xFFE6E6E6),

    background = Color(0xFF1A1A1A),
    surface = Color(0xFF262626),
    onBackground = Color(0xFFFFFFFF),
    onSurface = Color(0xFFE6E6E6),

    outline = Color(0xFF666666),
    outlineVariant = Color(0xFF4D4D4D),

    surfaceVariant = Color(0xFF333333),
    onSurfaceVariant = Color(0xFFE6E6E6),
    inverseSurface = Color(0xFFE6EBEB),
    inverseOnSurface = Color(0xFF1A1A1A),
    inversePrimary = Color(0xFF4D9999),
    scrim = Color(0xFF666666),
    surfaceDim = Color(0xFF1A1A1A),
    surfaceBright = Color(0xFF4D4D4D),
    surfaceContainerLowest = Color(0xFF1A1A1A),
    surfaceContainerLow = Color(0xFF262626),
    surfaceContainerHigh = Color(0xFF333333),
    surfaceContainerHighest = Color(0xFF4D4D4D),
)

@Composable
fun HabitFlowTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> darkScheme
        else -> lightScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
```