package com.visionforgestudio.taskflow.jcpackages.jcdropdown

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

@Composable
fun JcDropDownColorMenu(
	colors: List<Color>,
	selectedColor: Color? = null,
	onSelectedColorChange: (Color) -> Unit,
	expanded: Boolean,
	onDismissRequest: () -> Unit,
	modifier: Modifier = Modifier,
	offset: DpOffset = DpOffset(0.dp, 0.dp),
	scrollState: ScrollState = rememberScrollState(),
	shape: Shape = MenuDefaults.shape,
	containerColor: Color = MaterialTheme.colorScheme.background,
	tonalElevation: Dp = MenuDefaults.TonalElevation,
	shadowElevation: Dp = MenuDefaults.ShadowElevation,
	border: BorderStroke? = null,
	circleSize: Dp = 24.dp, // Size of the color circle
	selectedBorder: Dp = 2.dp // Border thickness for selected color
) {
	DropdownMenu(
		expanded = expanded,
		onDismissRequest = onDismissRequest,
		modifier = modifier,
		offset = offset,
		scrollState = scrollState,
		shape = shape,
		containerColor = containerColor,
		tonalElevation = tonalElevation,
		shadowElevation = shadowElevation,
		border = border,
	) {
		colors.forEach { color ->
			DropdownMenuItem(
				text = {
					if(selectedColor == color){
						Icon(
							modifier = Modifier.size(circleSize),
							imageVector = Icons.Filled.RadioButtonChecked,
							contentDescription = null,
							tint = color
						)
					}else{
						Icon(
							modifier = Modifier.size(circleSize),
							imageVector = Icons.Filled.Circle,
							contentDescription = null,
							tint = color
						)
					}
					
				},
				onClick = { onSelectedColorChange(color) }
			)
		}
	}
}