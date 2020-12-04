package com.dungle.weatherapp

import com.dungle.weatherapp.data.source.DataRepositoryImpl
import com.dungle.weatherapp.data.source.local.LocalDataSource
import com.dungle.weatherapp.data.source.remote.RemoteDataSource
import com.dungle.weatherapp.data.source.remote.ServiceGenerator
import com.dungle.weatherapp.data.source.remote.WeatherInfoApiService

object Injection {

    private val lock = Any()
    var dataRepositoryImpl: DataRepositoryImpl? = null

    fun provideDataRepository(): DataRepositoryImpl {
        synchronized(lock) {
            return dataRepositoryImpl ?: dataRepositoryImpl ?: createDataRepository()
        }
    }

    private fun createDataRepository(): DataRepositoryImpl {
        val repo = DataRepositoryImpl(
            provideLocalDataSource(),
            provideRemoteDataRepository()
        )
        dataRepositoryImpl = repo
        return repo
    }

    private fun provideLocalDataSource(): LocalDataSource {
        return LocalDataSource()
    }

    private fun provideRemoteDataRepository(): RemoteDataSource {
        val service = ServiceGenerator.createService(WeatherInfoApiService::class.java)
        return RemoteDataSource(service)
    }
}