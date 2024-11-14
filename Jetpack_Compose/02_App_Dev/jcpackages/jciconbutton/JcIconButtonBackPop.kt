package com.visionforgestudio.taskflow.jcpackages.jciconbutton

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.visionforgestudio.taskflow.jcpackages.jcnavigation.JcNav

@Composable
fun JcIconButtonBackPop(
	navController: NavController,
	onClick: () -> Unit = { JcNav.pop(navController) },
	imageIcon: ImageVector = Icons.AutoMirrored.Outlined.ArrowBack,
	description: String = "",
	iconColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
	backgroundColor: Color = Color.Transparent,
	disabledIconColor: Color = MaterialTheme.colorScheme.onSurface,
	disabledBackgroundColor: Color = Color.Transparent,
	modifier: Modifier = Modifier,
	enabled: Boolean = true,
	colors: IconButtonColors = IconButtonDefaults.iconButtonColors(         containerColor = backgroundColor,         contentColor = iconColor,         disabledContainerColor = disabledBackgroundColor,         disabledContentColor = disabledIconColor     ),
	interactionSource: MutableInteractionSource? = null,
	content: @Composable (() -> Unit) = { Icon(imageIcon, contentDescription = description) }
){
	JcIconButton(
		onClick = onClick,
		imageIcon = imageIcon,
		description = description,
		iconColor = iconColor,
		backgroundColor = backgroundColor,
		disabledIconColor = disabledIconColor,
		disabledBackgroundColor = disabledBackgroundColor,
		modifier = modifier,
		enabled = enabled,
		colors = colors,
		interactionSource = interactionSource,
	){
		content()
	}
}