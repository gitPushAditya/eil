package com.visionforgestudio.taskflow.jcpackages.jcbottomsheet

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.visionforgestudio.jcpackages.jctext.JcTextBL
import com.visionforgestudio.jcpackages.jctext.JcTextTL
import com.visionforgestudio.taskflow.jcpackages.jcspacer.JcSpacerVertical16
import com.visionforgestudio.taskflow.jcpackages.jctextbutton.JcTextButtonSmall
import com.visionforgestudio.taskflow.jcpackages.jcwheelpicker.JcWheelPicker
import com.visionforgestudio.taskflow.jcpackages.jcwheelpicker.JcWheelTimePicker
import kotlinx.coroutines.launch
import java.time.LocalTime


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JcBottomSheetWheelTimePicker(
	onDismissRequest: () -> Unit,
	modifier: Modifier = Modifier,
	selectedTime: LocalTime? = null,
	title: String = "Select Time",
	leadingText: String = "Clear",
	trailingText: String = "Select",
	leadingButtonOnClick: () -> Unit = {},
	trailingButtonOnClick: (LocalTime?) -> Unit = {},
	leadingButton: @Composable (() -> Unit)? = {
		JcTextButtonSmall(
			onClick = leadingButtonOnClick,
			textString = leadingText
		)
	},
	trailingButton: @Composable ((LocalTime?) -> Unit)? = { time: LocalTime? ->
		JcTextButtonSmall(
			onClick = { trailingButtonOnClick(time) },
			textString = trailingText
		)
	},
	cornerRadius: Dp = 25.dp,
	sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
	sheetMaxWidth: Dp = BottomSheetDefaults.SheetMaxWidth,
	shape: Shape = RoundedCornerShape(topStart = cornerRadius, topEnd = cornerRadius),
	containerColor: Color = MaterialTheme.colorScheme.surface,
	contentColor: Color = contentColorFor(containerColor),
	tonalElevation: Dp = 0.dp,
	scrimColor: Color = BottomSheetDefaults.ScrimColor,
	dragHandle: @Composable (() -> Unit)? = { BottomSheetDefaults.DragHandle() },
	maxHeight: Dp = 250.dp
) {
	
	var selected = remember { mutableStateOf(selectedTime) }
	
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
		dragHandle = dragHandle
	) {
		// Header with title and buttons
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 8.dp),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceBetween
		) {
			leadingButton?.invoke()
			JcTextTL(title)
			trailingButton?.invoke(selected.value)
		}
		
		JcSpacerVertical16()
		
		// Time pickers for hours and minutes
		JcWheelTimePicker(
			initialTime = selected.value ?: LocalTime.now(),
			onTimeSelected = { time ->
				selected.value = time
			},
			modifier = modifier,
			height = maxHeight,
			
		)
	}
}