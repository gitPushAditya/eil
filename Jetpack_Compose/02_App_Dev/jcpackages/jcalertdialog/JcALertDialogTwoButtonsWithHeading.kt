package com.visionforgestudio.taskflow.jcpackages.jcalertdialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.visionforgestudio.jcpackages.jctext.JcTextTM
import com.visionforgestudio.taskflow.jcpackages.jcspacer.JcSpacerVertical16
import com.visionforgestudio.taskflow.jcpackages.jctextbutton.JcTextButtonSmall

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JcAlertDialogTwoButtonsWithHeading(
	headingText: String = "",
	headingColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
	heading: @Composable (() -> Unit)? = { JcTextTM(headingText, color = headingColor) },
	onDismissRequest: () -> Unit,
	modifier: Modifier = Modifier,
	properties: DialogProperties = DialogProperties(),
	contentPadding: Dp = 16.dp,
	leftButtonText: String? = "",
	leftButtonOnClick: () -> Unit = {},
	leftButton: @Composable (() -> Unit)? = {
		JcTextButtonSmall(
			textString = leftButtonText ?: "",
			onClick = { leftButtonOnClick() }
		)
	},
	rightButtonText: String? = "",
	rightButtonOnClick: () -> Unit = {},
	rightButton: @Composable (() -> Unit)? = {
		JcTextButtonSmall(
			textString = rightButtonText ?: "",
			onClick = { rightButtonOnClick() }
		)
	},
	buttonsArrangement: Arrangement.Horizontal = Arrangement.SpaceBetween,
	backgroundColor: Color = MaterialTheme.colorScheme.surface,
	cornerRadius: Dp = 25.dp,
	content: @Composable (() -> Unit)
) {
	BasicAlertDialog(
		onDismissRequest = onDismissRequest,
		modifier = modifier,
		properties = properties,
		
		) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.clip(shape = RoundedCornerShape(cornerRadius))
				.background(color = backgroundColor)
				.padding(contentPadding),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.padding(8.dp),
				contentAlignment = Alignment.Center
			) {
				Column(modifier = Modifier.fillMaxWidth(),
					horizontalAlignment = Alignment.CenterHorizontally) {
					heading?.invoke()
					JcSpacerVertical16()
					content()
				}
				
			}
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 16.dp),
				horizontalArrangement = buttonsArrangement,
				verticalAlignment = Alignment.CenterVertically
			) {
				leftButton?.invoke()
				rightButton?.invoke()
			}
		}
	}
}