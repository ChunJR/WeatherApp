package com.dungle.weatherapp.util

import java.text.DecimalFormat

class UnitUtils {
    companion object {
        private const val CONSTANT_DEGREE = 273.15
        private const val ROUNDED_PATTERN = "#.##"

        fun convertKelvinToCelsius(kelvin: Double): String {
            return getRoundedNumber(kelvin - CONSTANT_DEGREE)
        }

        private fun getRoundedNumber(number: Double): String {
            val numberFormat = DecimalFormat(ROUNDED_PATTERN)
            return numberFormat.format(number)
        }
    }
}
