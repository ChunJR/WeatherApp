package com.dungle.weatherapp.data.source

import com.dungle.weatherapp.data.model.Area

interface DataRepository {
    suspend fun getWeatherInfoByCity(cityName: String): Area
}