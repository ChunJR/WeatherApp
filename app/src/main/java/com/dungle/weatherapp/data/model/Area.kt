package com.dungle.weatherapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Area(
        val city: City = City(),
        val cod: String = "",
        val message: Double = 0.0,
        val cnt: Int = 0,
        @SerializedName("list") val weatherInfoList: List<WeatherInfoModel> = listOf()
) : Parcelable