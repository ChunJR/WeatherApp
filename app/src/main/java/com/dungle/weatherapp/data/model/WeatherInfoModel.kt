package com.dungle.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class WeatherInfoModel(
    @SerializedName("dt") val dateTime: Long = 0,
    val temp: Temp = Temp(),
    val pressure: Int = 0,
    val humidity: Int = 0,
    val weather: List<Weather> = listOf()
)