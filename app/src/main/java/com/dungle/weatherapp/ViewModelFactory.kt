package com.dungle.weatherapp

import com.dungle.weatherapp.data.model.ResponseHandler
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dungle.weatherapp.data.source.DataRepositoryImpl
import com.dungle.weatherapp.main.viewmodel.WeatherInfoViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val dataRepositoryImpl: DataRepositoryImpl,
    private val responseHandler: ResponseHandler
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when (modelClass) {
            WeatherInfoViewModel::class.java -> {
                return WeatherInfoViewModel(dataRepositoryImpl, responseHandler) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}