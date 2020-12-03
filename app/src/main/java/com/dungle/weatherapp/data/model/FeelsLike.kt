package com.dungle.weatherapp.data.model
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FeelsLike(
    val day: Double = 0.0,
    val night: Double = 0.0,
    val eve: Double = 0.0,
    val morn: Double = 0.0
) : Parcelable