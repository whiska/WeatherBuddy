package com.weijia.weatherbuddy.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.weijia.weatherbuddy.viewmodels.ForecastViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForecastScreen(city: String, navController: NavController, viewModel: ForecastViewModel = viewModel()) {
    val forecastState by viewModel.forecastState.collectAsState()

    LaunchedEffect(city) {
        viewModel.fetchForecast(city)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("$city Weather Forecast") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(16.dp)) {
            forecastState?.let { forecast ->

                items(forecast) { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Text(
                            text = "${item.dateTime}: ${item.main.temp}°C, ${item.weather[0].description}",
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            } ?: item {
                Text("Loading forecast for $city...", modifier = Modifier.padding(16.dp))
            }
        }
    }
}