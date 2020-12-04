package com.dungle.weatherapp.main.viewmodel

import com.dungle.weatherapp.data.model.ResponseHandler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dungle.weatherapp.data.model.Area
import com.dungle.weatherapp.data.model.DataResult
import com.dungle.weatherapp.data.source.DataRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherInfoViewModel(
    private val dataRepositoryImpl: DataRepositoryImpl,
    private val responseHandler: ResponseHandler,
) : ViewModel() {
    private var _areaData: MutableLiveData<DataResult<Area>> = MutableLiveData()
    val areaData: LiveData<DataResult<Area>>
        get() = _areaData

    fun getWeatherInfo(cityName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _areaData.postValue(DataResult.loading(null))
            try {
                coroutineScope {
                    val data: Area =
                        withContext(Dispatchers.IO) {
                            dataRepositoryImpl.getWeatherInfoByCity(
                                cityName
                            )
                        }

                    _areaData.postValue(responseHandler.handleSuccess(data))
                }
            } catch (e: Exception) {
                _areaData.postValue(responseHandler.handleException(e))
            }
        }
    }
}