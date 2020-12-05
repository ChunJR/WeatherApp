package com.dungle.weatherapp.data.source.repo

import com.dungle.weatherapp.data.model.Area
import com.dungle.weatherapp.data.source.local.LocalDataSource
import com.dungle.weatherapp.data.source.remote.RemoteDataSource

class DataRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : DataRepository {
    override suspend fun getWeatherInfoByCity(cityName: String): Area {
        //TODO change condition to handle which source to get data
        return if (cityName.contains("some condition")) {
            localDataSource.getWeatherInfoByCity(cityName)
        } else {
            remoteDataSource.getWeatherInfoByCity(cityName)
        }
    }
}