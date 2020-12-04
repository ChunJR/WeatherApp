package com.dungle.weatherapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherInfoModel(
    @SerializedName("dt") val dateTime: Long = 0,
    val sunrise: Long = 0,
    val sunset: Long = 0,
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