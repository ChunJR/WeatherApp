package com.dungle.weatherapp.util

import java.text.SimpleDateFormat
import java.util.*

class TimeUtils {
    companion object {
        const val PATTERN = "EEE, d MMM yyyy"

        fun convertMillsToDateTimeAsString(pattern: String?, milliTime: Long): String? {
            val sdf = SimpleDateFormat(pattern, Locale.getDefault())
            val date = Date(milliTime * 1000)
            return sdf.format(date)
        }
    }
}