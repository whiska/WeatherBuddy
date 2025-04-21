package com.weijia.weatherbuddy.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.weijia.weatherbuddy.viewmodels.CityListViewModel

@Composable
fun CityListScreen(navController: NavController, viewModel: CityListViewModel = viewModel()) {

    val citiesState by viewModel.citiesState.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        items(citiesState) { cityWeather ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { navController.navigate("forecast/${cityWeather.city}") },
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Text(
                    text = when {
                        cityWeather.isLoading -> "${cityWeather.city}: Loading..."
                        cityWeather.temp != null -> "${cityWeather.city}: ${cityWeather.temp}°C"
                        else -> "${cityWeather.city}: Error"
                    },
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}


