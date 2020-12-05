package com.dungle.weatherapp.util

import java.text.DecimalFormat

class UnitUtils {
    companion object {
        private const val ROUNDED_PATTERN = "#.##"

        fun getRoundedNumber(number: Double): String {
            val numberFormat = DecimalFormat(ROUNDED_PATTERN)
            return numberFormat.format(number)
        }
    }
}
