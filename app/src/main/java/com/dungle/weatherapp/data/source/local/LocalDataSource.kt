package com.dungle.weatherapp.data.source.local

import com.dungle.weatherapp.data.model.WeatherInfoModel
import com.dungle.weatherapp.data.source.DataSource

class LocalDataSource : DataSource {
    override suspend fun getWeatherInfoByCity(cityName: String): WeatherInfoModel {
        TODO("Get data from local")
    }
}