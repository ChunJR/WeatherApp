package com.dungle.weatherapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Area(
        val city: City = City(),
        val cod: String = "",
        val message: Double = 0.0,
        val cnt: Int = 0,
        val list: List<WeatherInfoModel> = listOf()
) : Parcelable