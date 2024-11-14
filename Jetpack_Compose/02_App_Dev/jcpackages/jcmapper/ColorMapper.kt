package com.visionforgestudio.taskflow.jcpackages.jcmapper

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

fun jcMapperColorToHexString(color: Color): String {
	return color.toHexString()
}

fun Color.toHexString(): String {
	return String.format("#%06X", 0xFFFFFF and this.toArgb())
}

fun jcMapperHexStringToColor(hexString: String): Color {
	return hexString.toColor()
}

fun String.toColor(): Color {
	return try {
		val hex = this.removePrefix("#")
		Color(android.graphics.Color.parseColor("#$hex"))
	} catch (e: IllegalArgumentException) {
		Color.Unspecified
	}
}