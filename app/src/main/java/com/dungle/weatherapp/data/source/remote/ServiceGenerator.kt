package com.dungle.weatherapp.data.source.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceGenerator {
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/forecast/"
    private var builder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    private var retrofit: Retrofit? = null

    private val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        .addInterceptor(getLoggingInterceptor())
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)

    fun <S> createService(serviceClass: Class<S>): S {
        builder.client(httpClient.build())

        if (retrofit == null) {
            retrofit = builder.build()
        }

        return retrofit!!.create(serviceClass)
    }

    private fun getLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }
}