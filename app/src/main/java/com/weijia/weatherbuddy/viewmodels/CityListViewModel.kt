package com.weijia.weatherbuddy.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weijia.weatherbuddy.models.WeatherService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class CityWeather(val city: String, val temp: Float?, val isLoading: Boolean)

class CityListViewModel : ViewModel() {

    private val _citiesState = MutableStateFlow<List<CityWeather>>(emptyList())
    val citiesState: StateFlow<List<CityWeather>> = _citiesState

    private val cities = listOf("Singapore", "Tokyo", "London", "Sydney", "New York")

    init {
        fetchCitiesWeather()
    }

    private fun fetchCitiesWeather() {
        _citiesState.value = cities.map { CityWeather(it, null, true) }
        viewModelScope.launch {
            val updatedCities = cities.map { city ->
                try {
                    val response = WeatherService.api.getCurrentWeather(city)
                    CityWeather(city, response.main.temp, false)
                } catch (e: Exception) {
                    CityWeather(city, null, false)
                }
            }
            _citiesState.value = updatedCities
        }
    }
}