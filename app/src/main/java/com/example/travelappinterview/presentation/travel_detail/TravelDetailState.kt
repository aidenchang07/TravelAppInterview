package com.example.travelappinterview.presentation.travel_detail

import com.example.travelappinterview.domain.model.AttractionDetail

data class TravelDetailState(
    val isLoading: Boolean = false,
    val attractionDetail: AttractionDetail? = null,
    val error: String = ""
)
