package com.visionforgestudio.taskflow.jcpackages.jcsnackbar

import android.R.id.message
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun jcSnackBarTrigger(
	scope: CoroutineScope,
	snackbarHostState: SnackbarHostState,
	message: String,
	actionLabel: String? = null,
	onActionClick: (() -> Unit)? = null,
	onDismiss: (() -> Unit)? = null,
	duration: SnackbarDuration = if (actionLabel == null) SnackbarDuration.Short else SnackbarDuration.Indefinite,
	withDismissAction: Boolean = true
){
	scope.launch{
		snackbarHostState.currentSnackbarData?.dismiss()
		val snackbarResult = snackbarHostState.showSnackbar(
			message = message,
			actionLabel = actionLabel,
			duration = duration,
			withDismissAction = withDismissAction
		)
		if (snackbarResult == SnackbarResult.ActionPerformed){
			onActionClick?.invoke()
		}
		if(snackbarResult == SnackbarResult.Dismissed){
			onDismiss?.invoke()
		}
	}
}