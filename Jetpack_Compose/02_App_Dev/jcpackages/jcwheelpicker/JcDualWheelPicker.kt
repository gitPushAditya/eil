package com.visionforgestudio.taskflow.jcpackages.jcwheelpicker

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import com.visionforgestudio.jcpackages.jctext.JcTextBL

@Composable
fun JcDualWheelPicker(
	itemsLeft: List<String>,
	itemsRight: List<String>,
	modifier: Modifier = Modifier,
	initialIndexLeft: Int = 0,
	initialIndexRight: Int = 0,
	rowCount: Int = 5,
	onItemSelectedLeft: (Int) -> Unit = {},
	onItemSelectedRight: (Int) -> Unit = {},
	height: Dp = 250.dp,
	width: Dp = 128.dp,
	centerBackgroundColor: Color = MaterialTheme.colorScheme.surfaceBright,
	centerBorderColor: Color = MaterialTheme.colorScheme.primary,
	centerBorderWidth: Dp = 1.dp,
	centerCornerRadius: Dp = 16.dp,
	snappingEnabled: Boolean = true
) {
	val listStateLeft = rememberLazyListState(initialIndexLeft)
	val listStateRight = rememberLazyListState(initialIndexRight)
	val itemHeight = height / rowCount
	
	val centerIndexLeft by remember {
		derivedStateOf { listStateLeft.firstVisibleItemIndex + rowCount / 2 }
	}
	val centerIndexRight by remember {
		derivedStateOf { listStateRight.firstVisibleItemIndex + rowCount / 2 }
	}
	
	Box(
		modifier = modifier,
		contentAlignment = Alignment.Center
	) {
		Row(
			modifier = Modifier
				.height(itemHeight * rowCount)
				.width(width * 2)
				.padding(horizontal = 8.dp),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically
		) {
			// Left Picker with background surface
			WheelPickerColumn(
				items = itemsLeft,
				listState = listStateLeft,
				itemHeight = itemHeight,
				width = width,
				rowCount = rowCount,
				centerIndex = centerIndexLeft,
				centerBackgroundColor = centerBackgroundColor,
				centerBorderColor = centerBorderColor,
				centerBorderWidth = centerBorderWidth,
				centerCornerRadius = centerCornerRadius,
				onItemSelected = onItemSelectedLeft,
				snappingEnabled = snappingEnabled
			)
			
			// Right Picker with background surface
			WheelPickerColumn(
				items = itemsRight,
				listState = listStateRight,
				itemHeight = itemHeight,
				width = width,
				rowCount = rowCount,
				centerIndex = centerIndexRight,
				centerBackgroundColor = centerBackgroundColor,
				centerBorderColor = centerBorderColor,
				centerBorderWidth = centerBorderWidth,
				centerCornerRadius = centerCornerRadius,
				onItemSelected = onItemSelectedRight,
				snappingEnabled = snappingEnabled
			)
		}
	}
}

@Composable
fun WheelPickerColumn(
	items: List<String>,
	listState: LazyListState,
	itemHeight: Dp,
	width: Dp,
	rowCount: Int,
	centerIndex: Int,
	centerBackgroundColor: Color,
	centerBorderColor: Color,
	centerBorderWidth: Dp,
	centerCornerRadius: Dp,
	onItemSelected: (Int) -> Unit,
	snappingEnabled: Boolean
) {
	Box(
		modifier = Modifier.width(width),
		contentAlignment = Alignment.Center
	) {
		Surface(
			modifier = Modifier
				.size(width, itemHeight)
				.align(Alignment.Center),
			shape = RoundedCornerShape(centerCornerRadius),
			color = centerBackgroundColor,
			border = BorderStroke(centerBorderWidth, centerBorderColor)
		) {}
		
		LazyColumn(
			state = listState,
			contentPadding = PaddingValues(vertical = itemHeight * (rowCount / 2)),
			modifier = Modifier
				.height(itemHeight * rowCount)
				.width(width)
		) {
			itemsIndexed(items) { index, item ->
				
				
				Box(
					modifier = Modifier
						.height(itemHeight)
						.fillMaxWidth(),
					contentAlignment = Alignment.Center
				) {
					JcTextBL(text = item)
				}
			}
		}
	}
	
	LaunchedEffect(listState.isScrollInProgress) {
		if (!listState.isScrollInProgress) {
			if (snappingEnabled) {
				val targetIndex = listState.firstVisibleItemIndex + rowCount / 2
				listState.animateScrollToItem(targetIndex.coerceIn(0, items.lastIndex))
			}
			onItemSelected(centerIndex)
		}
	}
}