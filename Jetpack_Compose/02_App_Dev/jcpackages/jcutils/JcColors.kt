package com.visionforgestudio.drinkup.jcpackages.jcutils

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun JcColors(){
	val primary = MaterialTheme.colorScheme.primary
	val secondary = MaterialTheme.colorScheme.secondary
	val tertiary = MaterialTheme.colorScheme.tertiary
	
	val primaryContainer = MaterialTheme.colorScheme.primaryContainer
	val secondaryContainer = MaterialTheme.colorScheme.secondaryContainer
	val tertiaryContainer = MaterialTheme.colorScheme.tertiaryContainer
	
	val onPrimary = MaterialTheme.colorScheme.onPrimary
	val onSecondary = MaterialTheme.colorScheme.onSecondary
	val onTertiary = MaterialTheme.colorScheme.onTertiary
	
	val onPrimaryContainer = MaterialTheme.colorScheme.onPrimaryContainer
	val onSecondaryContainer = MaterialTheme.colorScheme.onSecondaryContainer
	val onTertiaryContainer = MaterialTheme.colorScheme.onTertiaryContainer
	
	val surface = MaterialTheme.colorScheme.surface
	val onSurface = MaterialTheme.colorScheme.onSurface
	
	val surfaceDim = MaterialTheme.colorScheme.surfaceDim
	val surfaceBright = MaterialTheme.colorScheme.surfaceBright
	
	val background = MaterialTheme.colorScheme.background
	val onBackground = MaterialTheme.colorScheme.onBackground
	
	val surfaceTint = MaterialTheme.colorScheme.surfaceTint
	
	val BrightRed = Color(0xFFFF0000)
	val BrightGreen = Color(0xFF3DFF00)
	val BrightOrange = Color(0xFFFF6E00)
	val BrightGolden = Color(0xFFFFF200)
	
	val DarkRed = Color(0xFF970000)
	val DarkGreen = Color(0xFF1F8600)
	val DarkOrange = Color(0xFFAC4D00)
	val DarkGolden = Color(0xFFBAA800)
}