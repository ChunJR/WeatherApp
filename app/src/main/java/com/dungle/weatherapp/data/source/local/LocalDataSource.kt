package com.dungle.weatherapp.data.source.local

import com.dungle.weatherapp.data.model.Area
import com.dungle.weatherapp.data.source.DataSource

class LocalDataSource : DataSource {
    override suspend fun getWeatherInfoByCity(cityName: String): Area {
        TODO("Get data from local")
    }
}