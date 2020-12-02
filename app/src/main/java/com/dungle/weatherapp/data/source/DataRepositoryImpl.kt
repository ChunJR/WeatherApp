package com.dungle.weatherapp.data.source

import com.dungle.weatherapp.data.model.WeatherInfoModel
import com.dungle.weatherapp.data.source.local.LocalDataSource
import com.dungle.weatherapp.data.source.remote.RemoteDataSource

class DataRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : DataRepository {
    override fun getWeatherInfoByCity(cityName: String): WeatherInfoModel {
        //TODO change condition to handle which source to get data
        return if (cityName.contains("asddasd")) {
            localDataSource.getWeatherInfoByCity(cityName)
        } else {
            remoteDataSource.getWeatherInfoByCity(cityName)
        }
    }
}