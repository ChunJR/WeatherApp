package com.dungle.weatherapp.data.source

import com.dungle.weatherapp.data.model.WeatherInfoModel

interface DataSource {
    suspend fun getWeatherInfoByCity(cityName : String) : WeatherInfoModel
}