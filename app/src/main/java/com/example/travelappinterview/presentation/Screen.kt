package com.example.travelappinterview.presentation

/**
 * Created by AidenChang 2024/02/22
 */
sealed class Screen(val route: String) {
    data object TravelMainScreen: Screen("travel_main_screen")
    data object TravelDetailScreen: Screen("travel_detail_screen")
}