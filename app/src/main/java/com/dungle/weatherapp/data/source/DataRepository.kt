package com.dungle.weatherapp.data.source

import com.dungle.weatherapp.data.model.WeatherInfoModel

interface DataRepository {
    fun getWeatherInfoByCity(cityName: String): WeatherInfoModel
}