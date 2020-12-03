package com.dungle.weatherapp.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dungle.weatherapp.data.model.WeatherInfoModel
import com.dungle.weatherapp.data.source.DataRepositoryImpl
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class WeatherInfoViewModel(private val dataRepositoryImpl: DataRepositoryImpl) : ViewModel() {
    private var _weatherInfoData: MutableLiveData<WeatherInfoModel> = MutableLiveData()
    val weatherInfoData: LiveData<WeatherInfoModel>
        get() = _weatherInfoData

    fun getWeatherInfo(cityName: String) {
        viewModelScope.launch {
            val data = async { dataRepositoryImpl.getWeatherInfoByCity(cityName) }
            _weatherInfoData.postValue(data.await())
        }
    }
}