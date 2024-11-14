package com.visionforgestudio.taskflow.jcpackages.jcmapper

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

fun LocalDateTime.jcToDateTimeString(): String {
	val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
	val dateTimeString = this.format(formatter)
	return dateTimeString
}

fun String.jcToLocalDateTime(): LocalDateTime? {
	val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
	return try {
		LocalDateTime.parse(this, formatter)
	} catch (e: DateTimeParseException) {
		null
	}
}

fun LocalDate.jcToDateString(): String {
	val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
	return this.format(formatter)
}

fun String.jcToLocalDate(): LocalDate? {
	val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
	return try {
		LocalDate.parse(this, formatter)
	} catch (e: DateTimeParseException) {
		null
	}
}

fun LocalTime.jcToTimeString(): String {
	val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
	return this.format(formatter)
}

fun String.jcToLocalTime(): LocalTime? {
	val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
	return try {
		LocalTime.parse(this, formatter)
	} catch (e: DateTimeParseException) {
		null
	}
}

fun LocalDate.jcDateStringView(): String{
	return if(this == LocalDate.now()){
		"Today"
	}else if(this == LocalDate.now().plusDays(1)){
		"Tomorrow"
	}else if(this == LocalDate.now().minusDays(1)){
		"Yesterday"
	}else{
		this.jcToDateString()
	}
}