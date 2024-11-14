package com.visionforgestudio.taskflow.jcpackages.jctextfield

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun JcTextFieldBorderedRoundedCorner(
	value: String,
	onValueChange: (String) -> Unit,
	topPadding: Dp = 8.dp,
	bottomPadding: Dp = 8.dp,
	startPadding: Dp = 8.dp,
	endPadding: Dp = 8.dp,
	minHeight: Dp = 68.dp,
	borderColor: Color = MaterialTheme.colorScheme.primary,
	borderRadius: Dp = 25.dp,
	modifier: Modifier = Modifier,
	enabled: Boolean = true,
	readOnly: Boolean = false,
	textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
	label: @Composable (() -> Unit)? = null,
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
				top = topPadding, bottom = bottomPadding, start = startPadding, end = endPadding
			)
			.then(modifier)
	)
}