package com.dungle.weatherapp.data.source.local

import com.dungle.weatherapp.data.model.Area
import com.dungle.weatherapp.data.source.DataSource
import com.dungle.weatherapp.data.source.database.AppDatabase

class LocalDataSource(private val appDatabase: AppDatabase) : DataSource {
    override suspend fun getWeatherInfoByCity(cityName: String): Area {
        return appDatabase.areaDao().findCityByName(cityName)
    }

    fun storeToLocal(area: Area) {
        appDatabase.areaDao().saveToLocal(area)
    }
}