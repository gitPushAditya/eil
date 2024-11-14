package com.visionforgestudio.taskflow.jcpackages.jcmenuitem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.visionforgestudio.jcpackages.jctext.JcTextHS
import com.visionforgestudio.jcpackages.jctext.JcTextTM

@Composable
fun JcMenuItemMediumIconLeft(
	height: Dp = 72.dp,
	backgroundColor: Color = Color.Transparent,
	innerPaddingVertical: Dp = 8.dp,
	innerPaddingHorizontal: Dp = 16.dp,
	onClick: () -> Unit = {},
	containerModifier: Modifier = Modifier,
	horizontalAlignment: Alignment.Horizontal = Alignment.Start,
	verticalArrangement: Arrangement.Vertical = Arrangement.Center,
	rowModifier: Modifier = Modifier,
	iconImage: ImageVector = Icons.Filled.Circle,
	iconContentDescription: String = "",
	iconSize: Dp = 26.dp,
	iconColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
	spaceBetweenIconAndText: Dp = 28.dp,
	title: String = "Default Title",
	titleColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
	isBottomDivider: Boolean = true,
) {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.height(height)
			.background(color = backgroundColor)
			.clickable {
				onClick()
			}
			.then(containerModifier),
		horizontalAlignment = horizontalAlignment,
		verticalArrangement = verticalArrangement
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.weight(1f)
				.padding(vertical = innerPaddingVertical, horizontal = innerPaddingHorizontal)
				.then(rowModifier),
			verticalAlignment = Alignment.CenterVertically
		) {
			Icon(
				iconImage,
				contentDescription = iconContentDescription,
				tint = iconColor,
				modifier = Modifier.size(iconSize)
			)
			Spacer(modifier = Modifier.width(spaceBetweenIconAndText))
			JcTextTM(title, color = titleColor)
		}
		if (isBottomDivider) {
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.height(1.dp)
					.background(MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = 0.3f))
			)
		}
	}
}