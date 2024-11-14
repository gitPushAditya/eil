package com.visionforgestudio.taskflow.jcpackages.jcbottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.SheetState
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.visionforgestudio.jcpackages.jctext.JcTextTL
import com.visionforgestudio.taskflow.jcpackages.jcradiolist.JcRadioList
import com.visionforgestudio.taskflow.jcpackages.jcspacer.JcSpacerVertical16
import com.visionforgestudio.taskflow.jcpackages.jctextbutton.JcTextButtonSmall

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JcBottomSheetRoundedListSelector(
	onDismissRequest: () -> Unit,
	modifier: Modifier = Modifier,
	items: List<String>,
	title: String = "",
	selectedItem: String? = null,
	leadingText: String = "",
	trailingText: String = "",
	leadingButtonOnClick: () -> Unit = {},
	trailingButtonOnClick: (String?) -> Unit = {},
	itemOnClick: (String?) -> Unit = {},
	leadingButton: @Composable (() -> Unit)? = {
		JcTextButtonSmall(
			onClick = leadingButtonOnClick,
			textString = leadingText
		)
	},
	trailingButton: @Composable ((String?) -> Unit)? = { item: String? ->
		JcTextButtonSmall(
			onClick = { trailingButtonOnClick(item) },
			textString = trailingText
		)
	},
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
	maxHeight: Dp = 250.dp

) {
	ModalBottomSheet(
		onDismissRequest = onDismissRequest,
		modifier = modifier.padding(8.dp),
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
		var selected = rememberSaveable { mutableStateOf<String?>(selectedItem) }
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.windowInsetsPadding(contentWindowInsets())
				.padding(horizontal = 8.dp),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceBetween
		)
		{
			leadingButton?.invoke()
			JcTextTL(title)
			trailingButton?.invoke(selected.value)
		}
		JcSpacerVertical16()
		LazyColumn(modifier = Modifier
			.fillMaxWidth()
			.height(maxHeight)) {
			item{
				items.forEach { item ->
					JcRadioList(
						textString = item,
						onClick = {
							selected.value = item
							itemOnClick(item)
						},
						selected = item == selected.value,
					)
				}
			}
		}
		
	}
}