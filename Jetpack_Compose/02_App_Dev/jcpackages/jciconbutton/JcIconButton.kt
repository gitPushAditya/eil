package com.visionforgestudio.taskflow.jcpackages.jciconbutton

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun JcIconButton(
	onClick: () -> Unit,
	imageIcon: ImageVector,
	description: String = "",
	iconSize: Dp = 24.dp,
	iconColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
	backgroundColor: Color = Color.Transparent,
	disabledIconColor: Color = iconColor.copy(alpha = 0.3f),
	disabledBackgroundColor: Color = Color.Transparent,
	modifier: Modifier = Modifier,
	enabled: Boolean = true,
	colors: IconButtonColors = IconButtonDefaults.iconButtonColors(
		containerColor = backgroundColor,
		contentColor = iconColor,
		disabledContainerColor = disabledBackgroundColor,
		disabledContentColor = disabledIconColor
	),
	interactionSource: MutableInteractionSource? = null,
	content: @Composable (() -> Unit) = {
		Icon(
			imageIcon,
			contentDescription = description,
			modifier = Modifier.size(iconSize)
		)
	}
) {
	IconButton(
		onClick = onClick,
		modifier = modifier,
		enabled = enabled,
		colors = colors,
		interactionSource = interactionSource,
	) {
		content()
	}
}