package com.visionforgestudio.taskflow.jcpackages.jctextbutton

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.visionforgestudio.jcpackages.jctext.JcTextBL
import com.visionforgestudio.jcpackages.jctext.JcTextLL

@Composable
fun JcTextButtonSmall(
	onClick: () -> Unit,
	textString: String,
	modifier: Modifier = Modifier,
	enabled: Boolean = true,
	shape: Shape = ButtonDefaults.textShape,
	colors: ButtonColors = ButtonDefaults.textButtonColors(
		contentColor = MaterialTheme.colorScheme.primary,
		containerColor = Color.Transparent,
	),
	elevation: ButtonElevation? = null,
	border: BorderStroke? = null,
	contentPadding: PaddingValues = ButtonDefaults.TextButtonContentPadding,
	interactionSource: MutableInteractionSource? = null,
	content: @Composable (RowScope.() -> Unit) = { JcTextLL(textString, color = MaterialTheme.colorScheme.primary) },
) {
	TextButton(
		onClick = onClick,
		modifier = modifier,
		enabled = enabled,
		shape = shape,
		colors = colors,
		elevation = elevation,
		border = border,
		contentPadding = contentPadding,
		interactionSource = interactionSource,
	) {
		content()
	}
}