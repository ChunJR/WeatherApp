package com.dungle.weatherapp.data.source.repo

import com.dungle.weatherapp.data.model.Area
import com.dungle.weatherapp.data.source.local.LocalDataSource
import com.dungle.weatherapp.data.source.remote.RemoteDataSource
import kotlinx.coroutines.withContext

class DataRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : DataRepository {
    override suspend fun getWeatherInfoByCityFromApi(cityName: String): Area {
        return remoteDataSource.getWeatherInfoByCity(cityName)
    }

    override suspend fun getWeatherInfoByCityFromLocal(cityName: String): Area {
        return localDataSource.getWeatherInfoByCity(cityName)
    }

    fun storeToLocal(data: Area) {
        localDataSource.storeToLocal(data)
    }
}