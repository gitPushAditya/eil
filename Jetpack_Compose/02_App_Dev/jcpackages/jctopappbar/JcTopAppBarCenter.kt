package com.visionforgestudio.taskflow.jcpackages.jctopappbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.visionforgestudio.jcpackages.jctext.JcTextHS


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JcTopAppBarCenter(
	titleText: String?,
	containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
	contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
	navigationIconColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
	actionIconColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
	title: @Composable (() -> Unit) = { JcTextHS(titleText ?: "Title") },
	modifier: Modifier = Modifier,
	navigationIcon: @Composable (() -> Unit) = {},
	actions: @Composable (RowScope.() -> Unit) = {},
	expandedHeight: Dp = TopAppBarDefaults.TopAppBarExpandedHeight,
	windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
	colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(
		containerColor = containerColor,
		titleContentColor = contentColor,
		navigationIconContentColor = navigationIconColor,
		actionIconContentColor = actionIconColor,
	),
	scrollBehavior: TopAppBarScrollBehavior? = null
) {
	CenterAlignedTopAppBar(
		title = title,
		modifier = modifier,
		navigationIcon = navigationIcon,
		actions = actions,
		scrollBehavior = scrollBehavior,
		colors = colors,
		windowInsets = windowInsets,
		expandedHeight = expandedHeight
	)
}