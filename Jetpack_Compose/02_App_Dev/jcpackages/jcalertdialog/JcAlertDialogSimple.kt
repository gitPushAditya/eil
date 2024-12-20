package com.visionforgestudio.taskflow.jcpackages.jcalertdialog

import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JcAlertDialogSimple(
	onDismissRequest: () -> Unit,
	modifier: Modifier = Modifier,
	properties: DialogProperties = DialogProperties(),
	content: @Composable () -> Unit
) {
	BasicAlertDialog(
		onDismissRequest = onDismissRequest,
		modifier = modifier,
		properties = properties,
		
		) {
		content()
	}
}