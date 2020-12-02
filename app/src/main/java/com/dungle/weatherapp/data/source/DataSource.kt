package com.dungle.weatherapp.data.source

import com.dungle.weatherapp.data.model.WeatherInfoModel

interface DataSource {
    fun getWeatherInfoByCity(cityName : String) : WeatherInfoModel
}