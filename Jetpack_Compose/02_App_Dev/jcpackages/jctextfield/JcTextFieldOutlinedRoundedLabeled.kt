package com.visionforgestudio.taskflow.jcpackages.jctextfield

import android.R.attr.label
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.visionforgestudio.jcpackages.jctext.JcTextBL

@Composable
fun JcTextFieldOutlinedRoundedLabeled(
	value: String,
	onValueChange: (String) -> Unit,
	labelText: String = "",
	topPadding: Dp = 8.dp,
	bottomPadding: Dp = 8.dp,
	startPadding: Dp = 8.dp,
	endPadding: Dp = 8.dp,
	minHeight: Dp = 68.dp,
	borderColor: Color = MaterialTheme.colorScheme.primary,
	borderRadius: Dp = 25.dp,
	enabled: Boolean = true,
	readOnly: Boolean = false,
	modifier: Modifier = Modifier,
	textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
	labelColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
	textColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
	label: @Composable (() -> Unit)? = { JcTextBL(labelText, color = labelColor) },
	placeholder: @Composable (() -> Unit)? = null,
	leadingIcon: @Composable (() -> Unit)? = null,
	trailingIcon: @Composable (() -> Unit)? = null,
	prefix: @Composable (() -> Unit)? = null,
	suffix: @Composable (() -> Unit)? = null,
	supportingText: @Composable (() -> Unit)? = null,
	isError: Boolean = false,
	visualTransformation: VisualTransformation = VisualTransformation.None,
	keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
		imeAction = ImeAction.Done
	),
	keyboardActions: KeyboardActions = KeyboardActions.Default,
	singleLine: Boolean = false,
	maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
	minLines: Int = 1,
	interactionSource: MutableInteractionSource? = null,
	shape: Shape = RoundedCornerShape(borderRadius),
	colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
		focusedContainerColor = Color.Transparent,
		unfocusedContainerColor = Color.Transparent,
		disabledContainerColor = Color.Transparent,
		errorContainerColor = Color.Transparent,
		focusedBorderColor = borderColor,
		unfocusedBorderColor = borderColor,
		focusedTextColor = textColor,
		unfocusedTextColor = textColor,
	)
) {
	OutlinedTextField(
		value = value,
		onValueChange = onValueChange,
		enabled = enabled,
		readOnly = readOnly,
		textStyle = textStyle,
		label = label,
		placeholder = placeholder,
		leadingIcon = leadingIcon,
		trailingIcon = trailingIcon,
		prefix = prefix,
		suffix = suffix,
		supportingText = supportingText,
		isError = isError,
		visualTransformation = visualTransformation,
		keyboardOptions = keyboardOptions,
		keyboardActions = keyboardActions,
		singleLine = singleLine,
		maxLines = maxLines,
		minLines = minLines,
		interactionSource = interactionSource,
		shape = shape,
		colors = colors,
		modifier = modifier
			.fillMaxWidth()
			.padding(
				top = topPadding,
				bottom = bottomPadding,
				start = startPadding,
				end = endPadding
			)
			.heightIn(min = minHeight)
			.then(modifier)
	)
}