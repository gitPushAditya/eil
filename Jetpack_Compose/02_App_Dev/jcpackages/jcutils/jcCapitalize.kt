package com.visionforgestudio.taskflow.jcpackages.jcutilsstring

fun String.jcCapitalize(): String =
	this.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }