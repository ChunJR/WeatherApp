package com.dungle.weatherapp.data.source.remote

import com.dungle.weatherapp.data.model.DataResult
import com.dungle.weatherapp.data.model.WeatherInfoModel
import com.dungle.weatherapp.data.source.DataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteDataSource : DataSource {
    override suspend fun getWeatherInfoByCity(cityName: String): WeatherInfoModel {
        val service = ServiceGenerator.createService(WeatherInfoApi::class.java)
        return withContext(Dispatchers.IO) {
            service.getWeatherInfoFromApi(cityName)
        }
    }
}