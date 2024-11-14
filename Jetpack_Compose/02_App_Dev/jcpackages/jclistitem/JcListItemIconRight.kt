package com.visionforgestudio.taskflow.jcpackages.jclistitem


import android.R.attr.contentDescription
import android.R.attr.onClick
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.visionforgestudio.jcpackages.jctext.JcTextTL
import com.visionforgestudio.jcpackages.jctext.JcTextTM

@Composable
fun JcListItemIconRight(
	titleText: String = "Default Title",
	iconImage: ImageVector = Icons.Filled.Circle,
	iconSize: Dp = 22.dp,
	contentDescription: String? = titleText,
	selected: Boolean = false,
	contentColor: Color = MaterialTheme.colorScheme.onSurface,
	iconColor: Color = contentColor,
	selectedContentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
	selectedContainerColor: Color = MaterialTheme.colorScheme.primaryContainer,
	containerColor: Color = MaterialTheme.colorScheme.surface,
	onClick: () -> Unit = {},
	headlineContent: @Composable (() -> Unit) = {
		JcTextTM(
			titleText, color = if (selected) {
				selectedContentColor
			} else {
				contentColor
			}
		)
	},
	modifier: Modifier = Modifier,
	overlineContent: @Composable (() -> Unit)? = null,
	supportingContent: @Composable (() -> Unit)? = null,
	leadingContent: @Composable (() -> Unit)? = null,
	trailingContent: @Composable (() -> Unit)? = {
		Icon(
			iconImage,
			contentDescription = contentDescription,
			modifier = Modifier.size(iconSize),
			tint =
				iconColor,
			
		)
	},
	colors: ListItemColors = ListItemDefaults.colors(
		containerColor = if (selected) {
			selectedContainerColor
		} else {
			containerColor
		},
		headlineColor = if (selected) {
			selectedContentColor
		} else {
			contentColor
		},
		leadingIconColor = if (selected) {
			selectedContentColor
		} else {
			contentColor
		},
		trailingIconColor = if (selected) {
			selectedContentColor
		} else {
			contentColor
		},
	),
	tonalElevation: Dp = ListItemDefaults.Elevation,
	shadowElevation: Dp = ListItemDefaults.Elevation
) {
	ListItem(
		modifier = modifier.clickable { onClick() },
		leadingContent = leadingContent,
		overlineContent = overlineContent,
		supportingContent = supportingContent,
		trailingContent = trailingContent,
		colors = colors,
		tonalElevation = tonalElevation,
		shadowElevation = shadowElevation,
		headlineContent = headlineContent,
	)
}