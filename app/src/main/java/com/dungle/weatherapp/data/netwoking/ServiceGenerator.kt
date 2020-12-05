package com.dungle.weatherapp.data.netwoking

import com.dungle.weatherapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceGenerator {
    private var builder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    private var retrofit: Retrofit? = null

    private val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        .addInterceptor(getLoggingInterceptor())
        .addInterceptor(AuthInterceptor())
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