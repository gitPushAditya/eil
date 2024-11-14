package com.visionforgestudio.taskflow.jcpackages.jcdivider

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.visionforgestudio.jcpackages.jctext.JcTextLM
import com.visionforgestudio.taskflow.jcpackages.jcspacer.JcSpacerHorizontal8

@Composable
fun JcDividerLeftText(
	leftPadding: Int = 4,
	rightPadding: Int = 4,
	topPadding: Int = 8,
	bottomPadding: Int = 8,
	textString: String,
	textColor: Color = MaterialTheme.colorScheme.onSecondaryContainer,
	text: @Composable () -> Unit = {
		JcTextLM(
			text = textString,
			color = textColor
		)
	}
) {
	Row(
		modifier = Modifier
			.background(color = Color.Transparent)
			.fillMaxWidth()
			.padding(
				start = leftPadding.dp,
				end = rightPadding.dp,
				top = topPadding.dp,
				bottom = bottomPadding.dp
			),
		verticalAlignment = Alignment.CenterVertically
	) {
		text()
		JcSpacerHorizontal8()
		HorizontalDivider(
			modifier = Modifier
				.weight(1f)
		)
	}
}