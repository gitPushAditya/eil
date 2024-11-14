package com.visionforgestudio.jcpackages.jcscaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun JcScaffoldEmpty(
	modifier: Modifier = Modifier,
	snackbarHost: @Composable (() -> Unit) = {},
	containerColor: Color = MaterialTheme.colorScheme.secondaryContainer,
	contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
	contentWindowInsets: WindowInsets = WindowInsets(0),
	content: @Composable ((PaddingValues) -> Unit)
) {
	Scaffold(
		modifier = modifier,
		snackbarHost = snackbarHost,
		containerColor = containerColor,
		contentColor = contentColor,
		contentWindowInsets = contentWindowInsets,
	) {
			
			contentPadding ->
		Box(
			modifier = Modifier
				.padding(contentPadding)
				.fillMaxSize()
		) {
			content(contentPadding)
		}
	}
}