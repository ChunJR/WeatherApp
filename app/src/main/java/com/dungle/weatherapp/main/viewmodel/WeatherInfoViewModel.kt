package com.dungle.weatherapp.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dungle.weatherapp.data.model.DataResult
import com.dungle.weatherapp.data.model.WeatherInfoModel
import com.dungle.weatherapp.data.source.DataRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherInfoViewModel(private val dataRepositoryImpl: DataRepositoryImpl) : ViewModel() {
    private var _weatherInfoData: MutableLiveData<DataResult<WeatherInfoModel>> = MutableLiveData()
    val weatherInfoData: LiveData<DataResult<WeatherInfoModel>>
        get() = _weatherInfoData

    fun getWeatherInfo(cityName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _weatherInfoData.postValue(DataResult.Loading)
            try {
                coroutineScope {
                    val data: WeatherInfoModel =
                        withContext(Dispatchers.IO) {
                            dataRepositoryImpl.getWeatherInfoByCity(
                                cityName
                            )
                        }

                    _weatherInfoData.postValue(DataResult.Success(data))
                }
            } catch (e: Exception) {
                _weatherInfoData.postValue(DataResult.Error(e))
            }
        }
    }
}