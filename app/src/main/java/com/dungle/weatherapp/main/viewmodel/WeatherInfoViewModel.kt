package com.dungle.weatherapp.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dungle.weatherapp.data.model.Area
import com.dungle.weatherapp.data.model.DataResult
import com.dungle.weatherapp.data.model.ResponseHandler
import com.dungle.weatherapp.data.source.repo.DataRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class WeatherInfoViewModel(
    private val dataRepositoryImpl: DataRepositoryImpl,
    private val responseHandler: ResponseHandler,
) : ViewModel() {
    private var _areaData: MutableLiveData<DataResult<Area>> = MutableLiveData()
    val areaData: LiveData<DataResult<Area>>
        get() = _areaData

    fun getWeatherInfo(cityName: String, isNetworkAvailable: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            _areaData.postValue(DataResult.loading(null))
            try {
                coroutineScope {
                    val data: Area = if (isNetworkAvailable) {
                        withContext(Dispatchers.IO) {
                            dataRepositoryImpl.getWeatherInfoByCityFromApi(
                                cityName
                            )
                        }
                    } else {
                        withContext(Dispatchers.IO) {
                            dataRepositoryImpl.getWeatherInfoByCityFromLocal(
                                cityName
                            )
                        }
                    }

                    if (isNetworkAvailable) {
                        saveToLocal(cityName, data)
                    }
                    _areaData.postValue(responseHandler.handleSuccess(data))
                }
            } catch (e: Exception) {
                _areaData.postValue(responseHandler.handleException(e))
            }
        }
    }

    private fun saveToLocal(key: String, data: Area) {
        data.cityName = key.toLowerCase(Locale.getDefault())
        dataRepositoryImpl.storeToLocal(data)
    }
}