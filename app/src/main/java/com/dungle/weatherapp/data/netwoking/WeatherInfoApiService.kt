package com.dungle.weatherapp.data.netwoking

import com.dungle.weatherapp.data.model.Area
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherInfoApiService {

    @GET("daily")
    suspend fun getWeatherInfoFromApi(
        @Query("q") cityName: String,
        @Query("cnt") foreCastDay: Int = 7,
        @Query("units") unit: String = "metric",
    ): Area
}