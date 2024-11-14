package com.visionforgestudio.taskflow.jcpackages.jcwheelpicker

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.visionforgestudio.jcpackages.jctext.JcTextBL
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Composable
fun JcWheelPicker(
	items: List<String>,
	modifier: Modifier = Modifier,
	initialIndex: Int = 0,
	rowCount: Int = 5,
	onItemSelected: (Int) -> Unit = {},
	height: Dp = 250.dp,
	width: Dp = 100.dp,
	centerBackgroundColor: Color = MaterialTheme.colorScheme.surfaceBright,
	centerBorderColor: Color = MaterialTheme.colorScheme.primary,
	centerBorderWidth: Dp = 1.dp,
	centerCornerRadius: Dp = 16.dp,
	snappingEnabled: Boolean = true,
	isSurfaceEnabled: Boolean = true,
) {
	val listState = rememberLazyListState(initialIndex)
	val itemHeight = height / rowCount
	
	val firstItem = listState.firstVisibleItemIndex
	
	val coroutineScope = rememberCoroutineScope()
	
	Box(
		modifier = modifier,
		contentAlignment = Alignment.Center
	) {
		// Centered Surface for the background of the central item
		if (isSurfaceEnabled) {
			Surface(
				modifier = Modifier
					.size(width, itemHeight)
					.align(Alignment.Center),
				shape = RoundedCornerShape(centerCornerRadius),
				color = centerBackgroundColor,
				border = BorderStroke(centerBorderWidth, centerBorderColor),
			) {}
		}
		
		
		LazyColumn(
			state = listState,
			contentPadding = PaddingValues(vertical = itemHeight * (rowCount / 2)),
			modifier = Modifier
				.height(itemHeight * rowCount)
				.width(width)
		) {
			itemsIndexed(items) { index, item ->
				val distanceToCenter = (index - firstItem).absoluteValue
				val alpha = calculateAlpha(distanceToCenter, rowCount)
				val rotationX = calculateRotation(distanceToCenter, rowCount)
				
				Box(
					modifier = Modifier
						.alpha(alpha)
						.graphicsLayer { this.rotationX = rotationX }
						.height(itemHeight)
						.fillMaxWidth()
						.pointerInput(Unit) {
							detectTapGestures {
								coroutineScope.launch {
									listState.animateScrollToItem(index)
								}
							}
						},
					contentAlignment = Alignment.Center
				) {
					if (index == firstItem) {
						JcTextBL(
							text = item,
							color = MaterialTheme.colorScheme.onPrimaryContainer,
							modifier = Modifier.scale(1.4f)
						)
					} else {
						JcTextBL(
							text = item,
							color = MaterialTheme.colorScheme.onPrimaryContainer,
							modifier = Modifier.scale(1f)
						
						)
					}
					
				}
			}
		}
	}
	
	LaunchedEffect(listState.isScrollInProgress) {
		if (!listState.isScrollInProgress) {
			if (snappingEnabled) {
				val centerVisibleItem = listState.firstVisibleItemIndex
				
				centerVisibleItem.let {
					val targetIndex = it
					listState.animateScrollToItem(targetIndex)
				}
			}
			onItemSelected(firstItem)
		}
	}
	
	
}

fun calculateAlpha(distanceToCenter: Int, rowCount: Int): Float {
	val maxDistance = rowCount / 2f
	val normalizedDistance = (distanceToCenter / maxDistance).coerceIn(0f, 1f)
	return 1f - normalizedDistance * 0.6f
}

fun calculateRotation(distanceToCenter: Int, rowCount: Int): Float {
	val maxRotation = 50f
	val normalizedDistance = (distanceToCenter / (rowCount / 2f)).coerceIn(0f, 1f)
	return maxRotation * normalizedDistance
}