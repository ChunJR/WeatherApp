package com.dungle.weatherapp

import android.app.Application
import com.dungle.weatherapp.data.source.repo.DataRepositoryImpl

class BaseApplication : Application() {

    val dataRepositoryImpl: DataRepositoryImpl
        get() = Injection.provideDataRepository(baseContext)
}