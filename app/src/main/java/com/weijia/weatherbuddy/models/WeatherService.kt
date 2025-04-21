package com.weijia.weatherbuddy.models

import com.weijia.weatherbuddy.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


object WeatherService {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: WeatherApi = retrofit.create(WeatherApi::class.java)
}

interface WeatherApi {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String = BuildConfig.OPENWEATHER_API_KEY,
        @Query("units") units: String = "metrics"
    ): WeatherResponse

    @GET("forecast")
    suspend fun getForecast(
        @Query("q") city: String,
        @Query("appid") apiKey: String = BuildConfig.OPENWEATHER_API_KEY,
        @Query("units") units: String = "metric"
    ): ForecastResponse
}