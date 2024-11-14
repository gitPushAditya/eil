package com.visionforgestudio.taskflow.jcpackages.jclist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.visionforgestudio.jcpackages.jctext.JcTextBL
import com.visionforgestudio.jcpackages.jctext.JcTextBM
import com.visionforgestudio.taskflow.jcpackages.jcspacer.JcSpacerHorizontal16
import com.visionforgestudio.taskflow.jcpackages.jcspacer.JcSpacerHorizontal8

@Composable
fun JcListUnorderedLarge(
	stringList: List<String>,
	modifier: Modifier = Modifier,
	icon: ImageVector = Icons.Filled.Circle,
	iconColor: Color = MaterialTheme.colorScheme.primary,
	textColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
	iconSize: Dp = 10.dp,
	iconTopPadding: Dp = 8.dp
){
	Column(modifier = Modifier.fillMaxWidth().then(modifier),
		) {
		for (string in stringList) {
			Row(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), verticalAlignment = Alignment.Top) {
				Icon(icon, contentDescription = string, tint = iconColor, modifier = Modifier.padding(top = iconTopPadding).size(iconSize))
				JcSpacerHorizontal16()
				JcTextBL(string, color = textColor)
				
			}
		}
	}
}