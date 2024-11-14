package com.visionforgestudio.taskflow.jcpackages.jcradiolist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.visionforgestudio.jcpackages.jctext.JcTextTM
import com.visionforgestudio.jcpackages.jctext.JcTextTS

@Composable
fun JcRadioList(
	selected: Boolean,
	textString: String = "",
	text: @Composable () -> Unit = {JcTextTS(textString)},
	selectedColor: Color = MaterialTheme.colorScheme.primary,
	unselectedColor: Color = MaterialTheme.colorScheme.onSurface,
	onClick: (() -> Unit)?,
	modifierRadio: Modifier = Modifier,
	modifierRow: Modifier = Modifier,
	enabled: Boolean = true,
	colors: RadioButtonColors = RadioButtonDefaults.colors(
		selectedColor = selectedColor,
		unselectedColor = unselectedColor,
	),
	interactionSource: MutableInteractionSource? = null
){
	Row(
		modifier = modifierRow.padding(vertical = 2.dp, horizontal = 8.dp)
			.fillMaxWidth()
			.clickable{
				onClick?.invoke()
			},
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.Start
	){
		RadioButton(
			selected = selected,
			onClick = onClick,
			modifier = modifierRadio,
			enabled = enabled,
			colors = colors,
			interactionSource = interactionSource,
		)
		text()
	}
}