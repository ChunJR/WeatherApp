package com.dungle.weatherapp.util

import java.text.SimpleDateFormat
import java.util.*

class TimeUtils {
    companion object {
        const val PATTERN = "EEE, d MMM yyyy"

        fun convertMillsToDateTimeAsString(pattern: String?, milliTime: Long): String? {
            val sdf = SimpleDateFormat(pattern, Locale.getDefault())
            val date = Date(milliTime)
            return sdf.format(date)
        }
    }
}