package com.dungle.weatherapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "area_table")
data class Area(
        @PrimaryKey
        @ColumnInfo(name = "name") var cityName: String,
        @ColumnInfo(name = "weatherInfoList")
        @SerializedName("list") val weatherInfoList: List<WeatherInfoModel> = listOf()
)