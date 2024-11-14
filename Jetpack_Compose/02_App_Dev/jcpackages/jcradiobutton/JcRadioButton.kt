package com.visionforgestudio.taskflow.jcpackages.jcradiobutton


import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun JcRadioButton(
	selected: Boolean,
	selectedColor: Color = MaterialTheme.colorScheme.primary,
	unselectedColor: Color = MaterialTheme.colorScheme.onSurface,
	onClick: (() -> Unit)?,
	modifier: Modifier = Modifier,
	enabled: Boolean = true,
	colors: RadioButtonColors = RadioButtonDefaults.colors(
		selectedColor = selectedColor,
		unselectedColor = unselectedColor,
	),
	interactionSource: MutableInteractionSource? = null
) {
	RadioButton(
		selected = selected,
		onClick = onClick,
		modifier = modifier,
		enabled = enabled,
		colors = colors,
		interactionSource = interactionSource,
	)
}