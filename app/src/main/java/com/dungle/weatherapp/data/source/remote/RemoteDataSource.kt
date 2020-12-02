package com.dungle.weatherapp.data.source.remote

import com.dungle.weatherapp.data.model.WeatherInfoModel
import com.dungle.weatherapp.data.source.DataSource

class RemoteDataSource : DataSource {
    override fun getWeatherInfoByCity(cityName: String): WeatherInfoModel {
        TODO("Get data from API")
    }
}