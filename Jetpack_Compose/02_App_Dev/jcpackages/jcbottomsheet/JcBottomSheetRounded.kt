package com.visionforgestudio.taskflow.jcpackages.jcbottomsheet

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.DrawerDefaults.scrimColor
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.ModalBottomSheetDefaults.properties
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.ScaffoldDefaults.contentWindowInsets
import androidx.compose.material3.SheetState
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JcBottomSheetRounded(
	onDismissRequest: () -> Unit,
	modifier: Modifier = Modifier,
	cornerRadius: Dp = 25.dp,
	sheetState: SheetState = rememberModalBottomSheetState(
		skipPartiallyExpanded = true
	),
	sheetMaxWidth: Dp = BottomSheetDefaults.SheetMaxWidth,
	shape: Shape = RoundedCornerShape(topStart = cornerRadius, topEnd = cornerRadius),
	containerColor: Color = MaterialTheme.colorScheme.surface,
	contentColor: Color = contentColorFor(containerColor),
	tonalElevation: Dp = 0.dp,
	scrimColor: Color = BottomSheetDefaults.ScrimColor,
	dragHandle: @Composable (() -> Unit)? = { BottomSheetDefaults.DragHandle() },
	contentWindowInsets: @Composable (() -> WindowInsets) = { BottomSheetDefaults.windowInsets },
	properties: ModalBottomSheetProperties = ModalBottomSheetDefaults.properties,
	content: @Composable (ColumnScope.() -> Unit)
) {
	ModalBottomSheet(
		onDismissRequest = onDismissRequest,
		modifier = modifier,
		sheetState = sheetState,
		sheetMaxWidth = sheetMaxWidth,
		shape = shape,
		containerColor = containerColor,
		contentColor = contentColor,
		tonalElevation = tonalElevation,
		scrimColor = scrimColor,
		dragHandle = dragHandle,
		contentWindowInsets = contentWindowInsets,
		properties = properties,
		
		) {
		content()
	}
}