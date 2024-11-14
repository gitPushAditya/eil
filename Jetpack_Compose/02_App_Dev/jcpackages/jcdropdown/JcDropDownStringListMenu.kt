package com.visionforgestudio.taskflow.jcpackages.jcdropdown

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.visionforgestudio.jcpackages.jctext.JcTextLL
import com.visionforgestudio.taskflow.jcpackages.jcutilsstring.jcCapitalize

@Composable
fun JcDropDownStringMenu(
	items: List<String>,
	selectedItem: String? = null,
	onSelectedItemChange: (String) -> Unit,
	expanded: Boolean,
	onDismissRequest: () -> Unit,
	modifier: Modifier = Modifier,
	offset: DpOffset = DpOffset(0.dp, 0.dp),
	scrollState: ScrollState = rememberScrollState(),
	shape: Shape = MenuDefaults.shape,
	containerColor: Color = MaterialTheme.colorScheme.background,
	tonalElevation: Dp = MenuDefaults.TonalElevation,
	shadowElevation: Dp = MenuDefaults.ShadowElevation,
	border: BorderStroke? = null,
) {
	DropdownMenu(
		expanded = expanded,
		onDismissRequest = onDismissRequest,
		modifier = modifier,
		offset = offset,
		scrollState = scrollState,
		shape = shape,
		containerColor = containerColor,
		tonalElevation = tonalElevation,
		shadowElevation = shadowElevation,
		border = border,
	) {
		items.forEach { item ->
			DropdownMenuItem(
				text = {
					JcTextLL(
						text = item.jcCapitalize(),
						color = if (item == selectedItem)
							MaterialTheme.colorScheme.onPrimary
						else
							MaterialTheme.colorScheme.onPrimaryContainer
					)
				},
				onClick = { onSelectedItemChange(item) },
			)
		}
	}
}