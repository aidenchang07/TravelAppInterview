package com.example.travelappinterview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.travelappinterview.common.Constants.PARAM_ATTRACTION_ID
import com.example.travelappinterview.common.Constants.PARAM_PAGE
import com.example.travelappinterview.presentation.Screen
import com.example.travelappinterview.presentation.travel_detail.TravelDetailScreen
import com.example.travelappinterview.presentation.travel_main.TravelMainScreen
import com.example.travelappinterview.presentation.ui.theme.TravelAppInterviewTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravelAppInterviewTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.TravelMainScreen.route
                    ) {
                        composable(
                            route = Screen.TravelMainScreen.route
                        ) {
                            TravelMainScreen(navController = navController)
                        }
                        composable(
                            route = Screen.TravelDetailScreen.route + "/{$PARAM_ATTRACTION_ID}/{$PARAM_PAGE}"
                        ) {
                            TravelDetailScreen(navController = navController)
                        }
                    }

                }
            }
        }
    }
}