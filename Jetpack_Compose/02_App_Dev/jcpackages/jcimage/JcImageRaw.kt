package com.visionforgestudio.taskflow.jcpackages.jcimage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun JcImageRaw(
	id: Int,
	description: String = "",
	modifier: Modifier = Modifier,
	isRounded: Boolean = false,
	cornerSize: Dp = 16.dp,
	shape: Shape = if (isRounded) RoundedCornerShape(cornerSize) else RectangleShape,
	size: Dp? = null,
	height: Dp? = null,
	width: Dp? = null,
	alignment: Alignment = Alignment.Center,
	contentScale: ContentScale = ContentScale.Fit,
	alpha: Float = DefaultAlpha,
	colorFilter: ColorFilter? = null
) {
	Image(
		painter = painterResource(id),
		contentDescription = description,
		modifier = Modifier
			.then(if (size != null) Modifier.size(size) else Modifier)
			.then(if (height != null) Modifier.height(height) else Modifier)
			.then(if (width != null) Modifier.width(width) else Modifier)
			.clip(
				shape = shape
			)
			.then(modifier),
		alignment = alignment,
		contentScale = contentScale,
		alpha = alpha,
		colorFilter = colorFilter
	)
}