package com.example.travelappinterview.domain.repository

import com.example.travelappinterview.data.remote.attractions_all_dto.AttractionDto

/**
 * Created by AidenChang 2024/02/22
 */
interface TravelRepository {
    suspend fun getAttractions(
        language: String,
        page: Int
    ): AttractionDto
}