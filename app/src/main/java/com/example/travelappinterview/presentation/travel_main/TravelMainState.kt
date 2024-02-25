package com.example.travelappinterview.presentation.travel_main

import com.example.travelappinterview.domain.model.Attraction

/**
 * Created by AidenChang 2024/02/22
 */
data class TravelMainState(
    val isLoading: Boolean = false,
    val attractions: List<Attraction> = emptyList(),
    val error: String = "",
    val isLastPage: Boolean = false
)
