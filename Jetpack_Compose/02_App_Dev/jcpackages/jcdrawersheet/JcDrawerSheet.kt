package com.visionforgestudio.taskflow.jcpackages.jcdrawersheet

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun JcDrawerSheet(
	maxWidth: Dp = 240.dp,
	modifier: Modifier = Modifier,
	drawerShape: Shape = DrawerDefaults.shape,
	drawerContainerColor: Color = MaterialTheme.colorScheme.surface,
	drawerContentColor: Color = MaterialTheme.colorScheme.onTertiaryContainer,
	drawerTonalElevation: Dp = DrawerDefaults.ModalDrawerElevation,
	windowInsets: WindowInsets = WindowInsets(0),
	content: @Composable (ColumnScope.() -> Unit)
) {
	ModalDrawerSheet(
		modifier = modifier.widthIn(max = maxWidth),
		drawerShape = drawerShape,
		drawerContainerColor = drawerContainerColor,
		drawerContentColor = drawerContentColor,
		drawerTonalElevation = drawerTonalElevation,
		windowInsets = windowInsets,
	) {
		LazyColumn {
			item {
				content()
			}
		}
	}
}