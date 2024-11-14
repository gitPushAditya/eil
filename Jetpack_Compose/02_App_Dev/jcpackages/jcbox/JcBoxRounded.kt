package com.visionforgestudio.taskflow.jcpackages.jcbox

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun JcBoxRounded(
	backgroundColor: Color = MaterialTheme.colorScheme.surfaceDim,
	padding: Dp = 16.dp,
	topPadding: Dp = padding,
	bottomPadding: Dp = padding,
	startPadding: Dp = padding,
	endPadding: Dp = padding,
	cornerRadius: Dp = 45.dp,
	minHeight: Dp = 60.dp,
	maxHeight: Dp = Dp.Unspecified,
	minWidth: Dp = Dp.Unspecified,
	maxWidth: Dp = Dp.Unspecified,
	fillMaxWidth: Boolean = false,
	fillMaxHeight: Boolean = false,
	contentAlignment: Alignment = Alignment.CenterStart,
	borderWidth: Dp = 1.dp,
	borderColor: Color = MaterialTheme.colorScheme.surfaceTint,
	modifier: Modifier = Modifier,
	content: @Composable () -> Unit
) {
	Box(
		modifier = Modifier
			.padding(
				top = topPadding, bottom = bottomPadding, start = startPadding, end = endPadding
			)
			.heightIn(
				min = minHeight,
				max = if (fillMaxHeight) Dp.Infinity else maxHeight
			)
			.widthIn(
				min = minWidth,
				max = if (fillMaxWidth) Dp.Infinity else maxWidth
			)
			.clip(RoundedCornerShape(cornerRadius))
			.background(backgroundColor)
			.border(
				width = borderWidth,
				color = borderColor,
				shape = RoundedCornerShape(cornerRadius)
			)
			.then(modifier),
		contentAlignment = contentAlignment
	) {
		content()
	}
}