package com.weijia.weatherbuddy.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.weijia.weatherbuddy.ui.theme.WeatherBuddyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherBuddyTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "cityList") {
                    composable("cityList") { CityListScreen(navController) }
                    composable("forecast/{city}") { backStackEntry ->
                        val city = backStackEntry.arguments?.getString("city") ?: ""
                        ForecastScreen(city = city, navController = navController)
                    }
                }
            }
        }
    }
}