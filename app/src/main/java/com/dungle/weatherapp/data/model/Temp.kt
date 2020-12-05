package com.dungle.weatherapp.data.model

data class Temp(
    val min: Double = 0.0,
    val max: Double = 0.0
) {
    fun getAveragePerDay(): Double {
        return (min + max) / 2
    }
}