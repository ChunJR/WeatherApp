package com.dungle.weatherapp.data.source.remote

import com.dungle.weatherapp.data.model.Area
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherInfoApiService {

    @GET("daily")
    suspend fun getWeatherInfoFromApi(
        @Query("q") cityName: String,
        @Query("cnt") foreCastDay: Int = 7,
        @Query("appid") appId: String = "60c6fbeb4b93ac653c492ba806fc346d"
    ): Area
}