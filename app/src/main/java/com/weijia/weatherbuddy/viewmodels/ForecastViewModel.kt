package com.weijia.weatherbuddy.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weijia.weatherbuddy.models.ForecastItem
import com.weijia.weatherbuddy.models.WeatherService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ForecastViewModel : ViewModel() {
    private val _forecastState = MutableStateFlow<List<ForecastItem>?>(null)
    val forecastState: StateFlow<List<ForecastItem>?> = _forecastState

    fun fetchForecast(city: String) {
        _forecastState.value = null // Reset for loading
        viewModelScope.launch {
            try {
                val response = WeatherService.api.getForecast(city)
                _forecastState.value = response.list.take(5)
            } catch (e: Exception) {
                _forecastState.value = emptyList() // Error state
            }
        }
    }
}