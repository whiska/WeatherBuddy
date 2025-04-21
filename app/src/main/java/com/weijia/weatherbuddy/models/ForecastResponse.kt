package com.weijia.weatherbuddy.models

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    val list: List<ForecastItem>
)
data class  ForecastItem(
    @SerializedName(value = "dt_txt")
    val dateTime: String,
    val main: Main,
    val weather: List<Weather>
)