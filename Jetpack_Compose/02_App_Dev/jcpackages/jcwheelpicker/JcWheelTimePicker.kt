package com.visionforgestudio.taskflow.jcpackages.jcwheelpicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.time.LocalTime

@Composable
fun JcWheelTimePicker(
	modifier: Modifier = Modifier,
	initialTime: LocalTime = LocalTime.now(),  // Default to current time
	onTimeSelected: (LocalTime) -> Unit,
	rowCount: Int = 5,
	height: Dp = 250.dp,
	width: Dp = 100.dp,
	centerBackgroundColor: Color = MaterialTheme.colorScheme.surface,
	centerBorderColor: Color = MaterialTheme.colorScheme.primary,
	centerBorderWidth: Dp = 1.dp,
	centerCornerRadius: Dp = 45.dp,
	snappingEnabled: Boolean = true,
	isSurfaceEnabled: Boolean = true,
) {
	val itemHeight = height / rowCount
	
	var selectedHour by remember { mutableIntStateOf(initialTime.hour) }
	var selectedMinute by remember { mutableIntStateOf(initialTime.minute) }
	
	Box(
		modifier = Modifier.padding(horizontal = 8.dp).then(modifier),
		contentAlignment = Alignment.Center
	) {
		if (isSurfaceEnabled) {
			Surface(
				modifier = Modifier
					.fillMaxWidth()
					.height(itemHeight)
					.align(Alignment.Center),
				shape = RoundedCornerShape(centerCornerRadius),
				color = centerBackgroundColor,
				border = BorderStroke(centerBorderWidth, centerBorderColor),
			) {}
		}
		Row(
			modifier = Modifier.fillMaxWidth().then(modifier),
			horizontalArrangement = Arrangement.Center,
		) {
			// Hour picker (0-23)
			JcWheelPicker(
				items = (0..23).map { it.toString().padStart(2, '0') },
				initialIndex = selectedHour,
				rowCount = rowCount,
				onItemSelected = { index ->
					selectedHour = index
					onTimeSelected(LocalTime.of(selectedHour, selectedMinute))
				},
				height = height,
				width = width,
				centerBackgroundColor = centerBackgroundColor,
				centerBorderColor = centerBorderColor,
				centerBorderWidth = centerBorderWidth,
				centerCornerRadius = centerCornerRadius,
				snappingEnabled = snappingEnabled,
				isSurfaceEnabled = false,
			)
			
			// Minute picker (0-59)
			JcWheelPicker(
				items = (0..59).map { it.toString().padStart(2, '0') },
				initialIndex = selectedMinute,
				rowCount = rowCount,
				onItemSelected = { index ->
					selectedMinute = index
					onTimeSelected(LocalTime.of(selectedHour, selectedMinute))
				},
				height = height,
				width = width,
				centerBackgroundColor = centerBackgroundColor,
				centerBorderColor = centerBorderColor,
				centerBorderWidth = centerBorderWidth,
				centerCornerRadius = centerCornerRadius,
				snappingEnabled = snappingEnabled,
				isSurfaceEnabled = false,
			)
		}
	}
}