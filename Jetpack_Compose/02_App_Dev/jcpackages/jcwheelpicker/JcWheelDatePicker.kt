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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.visionforgestudio.jcpackages.jctext.JcTextBL
import java.time.LocalDate
import java.time.Month
import java.time.YearMonth

@Composable
fun JcWheelDatePicker(
	modifier: Modifier = Modifier,
	initialDate: LocalDate = LocalDate.now(),  // Default to today's date
	onDateSelected: (LocalDate) -> Unit,
	rowCount: Int = 5,
	height: Dp = 250.dp,
	width: Dp = 100.dp,
	centerBackgroundColor: Color = MaterialTheme.colorScheme.surface,
	centerBorderColor: Color = MaterialTheme.colorScheme.primary,
	centerBorderWidth: Dp = 1.dp,
	centerCornerRadius: Dp = 45.dp,
	snappingEnabled: Boolean = true,
	rangeGap: Int = 100,
	isSurfaceEnabled: Boolean = true,
) {
	val daysInMonth = remember(initialDate.year, initialDate.month) {
		YearMonth.of(initialDate.year, initialDate.month).lengthOfMonth()
	}
	val itemHeight = height / rowCount
	
	
	var selectedDay by remember { mutableIntStateOf(initialDate.dayOfMonth) }
	var selectedMonth by remember { mutableIntStateOf(initialDate.monthValue) }
	var selectedYear by remember { mutableIntStateOf(initialDate.year) }
	
	fun updateDaysInMonth() {
		val updatedDaysInMonth = YearMonth.of(selectedYear, selectedMonth).lengthOfMonth()
		if (selectedDay > updatedDaysInMonth) {
			selectedDay = updatedDaysInMonth
		}
	}
	Box(
		modifier = Modifier.padding(horizontal = 8.dp).then(modifier),
		contentAlignment = Alignment.Center
	){
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
			// Day picker
			JcWheelPicker(
				items = (1..daysInMonth).map { it.toString() },
				initialIndex = selectedDay - 1,
				rowCount = rowCount,
				onItemSelected = { index ->
					selectedDay = index + 1
					updateDaysInMonth()
					onDateSelected(LocalDate.of(selectedYear, selectedMonth, selectedDay))
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
			
			// Month picker
			JcWheelPicker(
				items = (1..12).map { Month.of(it).name.substring(0,3) },
				initialIndex = selectedMonth - 1,
				rowCount = rowCount,
				onItemSelected = { index ->
					selectedMonth = (index + 1).coerceIn(1,12)
					updateDaysInMonth()
					onDateSelected(LocalDate.of(selectedYear, selectedMonth, selectedDay))
				},
				height = height,
				width = width,
				centerBackgroundColor = centerBackgroundColor,
				centerBorderColor = centerBorderColor,
				centerBorderWidth = centerBorderWidth,
				centerCornerRadius = centerCornerRadius,
				snappingEnabled = snappingEnabled,
				isSurfaceEnabled = false
			)
			
			// Year picker
			val startRange = LocalDate.now().year - rangeGap
			val endRange = LocalDate.now().year + rangeGap
			JcWheelPicker(
				items = (startRange..endRange).map { it.toString() },
				initialIndex = selectedYear - (LocalDate.now().year - rangeGap),
				rowCount = rowCount,
				onItemSelected = { index ->
					selectedYear = index + startRange
					updateDaysInMonth()
					onDateSelected(LocalDate.of(selectedYear, selectedMonth, selectedDay))
				},
				height = height,
				width = width,
				centerBackgroundColor = centerBackgroundColor,
				centerBorderColor = centerBorderColor,
				centerBorderWidth = centerBorderWidth,
				centerCornerRadius = centerCornerRadius,
				snappingEnabled = snappingEnabled,
				isSurfaceEnabled = false
			)
		}
	}
	
}