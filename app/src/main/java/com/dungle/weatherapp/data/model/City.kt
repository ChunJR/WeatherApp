package com.dungle.weatherapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City(
    val id: Int = 0,
    val name: String = "",
    @SerializedName("coord") val location: LocationInfo = LocationInfo(),
    val country: String = "",
    val population: Int = 0,
    val timezone: Int = 0
) : Parcelable