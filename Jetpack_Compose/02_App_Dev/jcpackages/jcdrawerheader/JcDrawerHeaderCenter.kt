package com.visionforgestudio.taskflow.jcpackages.jcdrawerheader

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun JcDrawerHeaderCenter(
	backgroundColor: Color = MaterialTheme.colorScheme.secondaryContainer,
	topPadding: Dp = 48.dp,
	bottomPadding: Dp = 16.dp,
	horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
	content: @Composable () -> Unit
) {
	Column(
		modifier = Modifier
			.background(backgroundColor)
			.fillMaxWidth()
			.padding(top = topPadding, bottom = bottomPadding),
		horizontalAlignment = horizontalAlignment
	) {
		content()
	}
}