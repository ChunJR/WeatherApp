package com.dungle.weatherapp.data.source.database

import androidx.room.TypeConverter
import com.dungle.weatherapp.data.model.WeatherInfoModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun weatherInfoListToString(weatherInfoModels: List<WeatherInfoModel>): String {
        return gson.toJson(weatherInfoModels)
    }

    @TypeConverter
    fun stringToWeatherInfoList(data: String): List<WeatherInfoModel> {
        val listType: Type = object : TypeToken<List<WeatherInfoModel>>() {}.type
        return gson.fromJson(data, listType)
    }

}