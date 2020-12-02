package com.dungle.weatherapp

import android.app.Application
import com.dungle.weatherapp.data.source.DataRepositoryImpl

class BaseApplication : Application() {

    val dataRepositoryImpl: DataRepositoryImpl
        get() = Injection.provideDataRepository()

    override fun onCreate() {
        super.onCreate()
    }
}