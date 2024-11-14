package com.visionforgestudio.taskflow.jcpackages.jccheckbox

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.icons.outlined.RadioButtonUnchecked
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.visionforgestudio.taskflow.ui.theme.themes.ExtraColors

@Composable
fun JcCheckboxRounded(
	checked: Boolean,
	onCheckedChange: ((Boolean) -> Unit)?,
	modifier: Modifier = Modifier,
	checkedColor: Color = MaterialTheme.colorScheme.primary,
	uncheckedColor: Color = MaterialTheme.colorScheme.primary,
	size: Dp = 40.dp,
	enabled: Boolean = true,
) {
	
	IconButton(
		onClick = { onCheckedChange?.invoke(!checked) },
	) {
		if(checked){
			Icon(Icons.Filled.CheckCircle, contentDescription = null, tint = checkedColor)
		}else{
			Icon(Icons.Outlined.RadioButtonUnchecked, contentDescription = null, tint = uncheckedColor)
		}
	}
	
}