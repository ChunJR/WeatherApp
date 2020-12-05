package com.dungle.weatherapp

import android.content.Context
import com.dungle.weatherapp.data.source.repo.DataRepositoryImpl
import com.dungle.weatherapp.data.source.local.LocalDataSource
import com.dungle.weatherapp.data.source.remote.RemoteDataSource
import com.dungle.weatherapp.data.netwoking.ServiceGenerator
import com.dungle.weatherapp.data.netwoking.WeatherInfoApiService
import com.dungle.weatherapp.data.source.database.AppDatabase

object Injection {

    private val lock = Any()
    var dataRepositoryImpl: DataRepositoryImpl? = null

    fun provideDataRepository(context : Context): DataRepositoryImpl {
        synchronized(lock) {
            return dataRepositoryImpl ?: dataRepositoryImpl ?: createDataRepository(context)
        }
    }

    private fun createDataRepository(context: Context): DataRepositoryImpl {
        val repo = DataRepositoryImpl(
            provideLocalDataSource(context),
            provideRemoteDataRepository()
        )
        dataRepositoryImpl = repo
        return repo
    }

    private fun provideLocalDataSource(context: Context): LocalDataSource {
        return LocalDataSource(AppDatabase.getDatabase(context))
    }

    private fun provideRemoteDataRepository(): RemoteDataSource {
        val service = ServiceGenerator.createService(WeatherInfoApiService::class.java)
        return RemoteDataSource(service)
    }
}