package com.dungle.weatherapp.data.source.repo

import com.dungle.weatherapp.data.model.Area

interface DataRepository {
    suspend fun getWeatherInfoByCityFromApi(cityName: String): Area
    suspend fun getWeatherInfoByCityFromLocal(cityName: String): Area
}