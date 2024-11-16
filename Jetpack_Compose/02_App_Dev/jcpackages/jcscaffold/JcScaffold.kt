package com.visionforgestudio.taskflow.jcpackages.jcscaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun JcScaffold(
	modifier: Modifier = Modifier,
	topBar: @Composable (() -> Unit) = {},
	bottomBar: @Composable (() -> Unit) = {},
	snackbarHost: @Composable (() -> Unit) = {},
	floatingActionButton: @Composable (() -> Unit) = {},
	floatingActionButtonPosition: FabPosition = FabPosition.End,
	containerColor: Color = MaterialTheme.colorScheme.background,
	contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
	contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
	content: @Composable ((PaddingValues) -> Unit)
) {
	Scaffold(
		modifier = modifier,
		topBar = topBar,
		bottomBar = bottomBar,
		snackbarHost = snackbarHost,
		floatingActionButton = floatingActionButton,
		floatingActionButtonPosition = floatingActionButtonPosition,
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