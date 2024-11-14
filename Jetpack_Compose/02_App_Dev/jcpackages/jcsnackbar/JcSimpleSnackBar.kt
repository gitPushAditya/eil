package com.visionforgestudio.taskflow.jcpackages.jcsnackbar

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.visionforgestudio.taskflow.ui.theme.themes.ExtraColors

@Composable
fun JcSimpleSnackBar(
	snackbarData: SnackbarData,
	modifier: Modifier = Modifier,
	cornerRadius: Dp = 10.dp,
	actionOnNewLine: Boolean = false,
	shape: Shape = RoundedCornerShape(cornerRadius),
	containerColor: Color = MaterialTheme.colorScheme.surfaceBright,
	contentColor: Color = MaterialTheme.colorScheme.onSurface,
	actionColor: Color = MaterialTheme.colorScheme.primary,
	actionContentColor: Color = MaterialTheme.colorScheme.primary,
	dismissActionContentColor: Color = ExtraColors.HighPriority.color
) {
	Snackbar(
	snackbarData = snackbarData,
	actionOnNewLine = actionOnNewLine,
	modifier = modifier,
	shape = shape,
	containerColor = containerColor,
	contentColor = contentColor,
	actionColor = actionColor,
	actionContentColor = actionContentColor,
	dismissActionContentColor = dismissActionContentColor
	)
}