package com.dungle.weatherapp.data.source.remote

import com.dungle.weatherapp.data.model.Area
import com.dungle.weatherapp.data.source.DataSource

class RemoteDataSource (private val service: WeatherInfoApiService) : DataSource {
    override suspend fun getWeatherInfoByCity(cityName: String): Area {
        return service.getWeatherInfoFromApi(cityName)
    }
}