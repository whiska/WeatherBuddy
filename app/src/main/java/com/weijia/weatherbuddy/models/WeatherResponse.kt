package com.weijia.weatherbuddy.models

data class WeatherResponse(
    val main: Main,
    val weather: List<Weather>,
    val name: String
)
data class Main(val temp: Float)
data class Weather(val description: String, val icon: String)