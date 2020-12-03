package com.dungle.weatherapp.data.model
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LocationInfo(
    val lon: Double = 0.0,
    val lat: Double = 0.0
) : Parcelable