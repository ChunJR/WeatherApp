package com.dungle.weatherapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherInfoModel(
    val dt: Int = 0,
    val sunrise: Int = 0,
    val sunset: Int = 0,
    val temp: Temp = Temp(),
    val feels_like: FeelsLike = FeelsLike(),
    val pressure: Int = 0,
    val humidity: Int = 0,
    val weather: List<Weather> = listOf(),
    val speed: Double = 0.0,
    val deg: Int = 0,
    val clouds: Int = 0,
    val pop: Double = 0.0,
    val rain: Double = 0.0
) : Parcelable