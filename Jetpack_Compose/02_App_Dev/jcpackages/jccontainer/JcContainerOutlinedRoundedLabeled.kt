package com.visionforgestudio.taskflow.jcpackages.jccontainer

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.PriorityHigh
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.visionforgestudio.jcpackages.jctext.JcTextBL
import com.visionforgestudio.taskflow.jcpackages.jciconbutton.JcIconButton
import com.visionforgestudio.taskflow.jcpackages.jcspacer.JcSpacerHorizontal2

@Composable
fun JcContainerOutlinedRoundedLabeled(
	labelText: String = "",
	contentText: String = "",
	leadingIcon: ImageVector = Icons.Filled.PriorityHigh,
	trailingIcon: ImageVector = Icons.Filled.ArrowDropDown,
	bodyOnClick: () -> Unit = {},
	leadingOnClick: () -> Unit = bodyOnClick,
	trailingOnClick: () -> Unit = bodyOnClick,
	contentOnClick: () -> Unit = bodyOnClick,
	leadingIconColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
	trailingIconColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
	contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
	leading: @Composable (() -> Unit)? = {
		JcIconButton(
			imageIcon = leadingIcon,
			onClick = leadingOnClick,
			iconColor = leadingIconColor
		)
	},
	content: @Composable (() -> Unit)? = {
		JcTextBL(
			text = contentText,
			color = contentColor,
			modifier = Modifier.clickable {
				contentOnClick()
			}
		)
	},
	trailing: @Composable (() -> Unit)? = {
		JcIconButton(
			imageIcon = trailingIcon,
			onClick = trailingOnClick,
			iconColor = trailingIconColor
		)
	},
	contentWeight: Float = 1f,
	labelColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
	label: @Composable (() -> Unit)? = { JcTextBL(labelText, color = labelColor) },
	topPadding: Dp = 8.dp,
	bottomPadding: Dp = 8.dp,
	startPadding: Dp = 8.dp,
	endPadding: Dp = 8.dp,
	minHeight: Dp = 60.dp,
	borderColor: Color = MaterialTheme.colorScheme.primary,
	borderCorner: Dp = 25.dp,
	labelBackgroundColor: Color = MaterialTheme.colorScheme.background,
	modifier: Modifier = Modifier
) {
	Box(
		modifier = Modifier
			.fillMaxWidth()
			.padding(top = topPadding, bottom = bottomPadding, start = startPadding, end = endPadding)
			.then(modifier)
			
	) {
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.padding(vertical = 10.dp)
				.border(
					1.dp,
					borderColor,
					RoundedCornerShape(borderCorner)
				)
				.heightIn(min = minHeight)
				.clickable {
					bodyOnClick()
				},
			contentAlignment = Alignment.CenterStart
		) {
			Row(
				modifier = Modifier.fillMaxWidth(),
				verticalAlignment = Alignment.CenterVertically
			) {
				leading?.invoke()
				JcSpacerHorizontal2()
				Box(modifier = Modifier.weight(contentWeight)) {
					content?.invoke()
				}
				trailing?.invoke()
			}
		}
		Box(
			modifier = Modifier
				.padding(start = 14.dp)
				.background(color = labelBackgroundColor)
				.padding(
					if (labelText.isNotEmpty()) {
						PaddingValues(horizontal = 6.dp)
					} else {
						PaddingValues(0.dp)
					}
				)
		) {
			label?.invoke()
		}
	}
}