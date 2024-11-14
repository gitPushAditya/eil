package com.visionforgestudio.jcpackages.jctext

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit


@Composable
fun JcTextLL(
	text: String,
	modifier: Modifier = Modifier,
	color: Color = MaterialTheme.colorScheme.onPrimaryContainer,
	fontSize: TextUnit = TextUnit.Unspecified,
	fontStyle: FontStyle? = FontStyle.Normal,
	fontWeight: FontWeight? = null,
	fontFamily: FontFamily? = FontFamily.SansSerif,
	letterSpacing: TextUnit = TextUnit.Unspecified,
	textDecoration: TextDecoration? = null,
	textAlign: TextAlign? = TextAlign.Start,
	lineHeight: TextUnit = TextUnit.Unspecified,
	overflow: TextOverflow = TextOverflow.Clip,
	softWrap: Boolean = true,
	maxLines: Int = Int.MAX_VALUE,
	minLines: Int = 1,
	onTextLayout: ((TextLayoutResult) -> Unit)? = null,
	style: TextStyle = MaterialTheme.typography.labelLarge
) {
	Text(
		text = text,
		modifier = modifier,
		color = color,
		fontSize = fontSize,
		fontStyle = fontStyle,
		fontWeight = fontWeight,
		fontFamily = fontFamily,
		letterSpacing = letterSpacing,
		textDecoration = textDecoration,
		textAlign = textAlign,
		lineHeight = lineHeight,
		overflow = overflow,
		softWrap = softWrap,
		maxLines = maxLines,
		minLines = minLines,
		onTextLayout = onTextLayout,
		style = style
	)
}