package com.example.travelappinterview.data.repository

import com.example.travelappinterview.data.remote.TravelApi
import com.example.travelappinterview.data.remote.attractions_all_dto.AttractionDto
import com.example.travelappinterview.domain.repository.TravelRepository
import javax.inject.Inject

/**
 * Created by AidenChang 2024/02/22
 */
class TravelRepositoryImpl @Inject constructor(
    private val api: TravelApi
): TravelRepository {
    override suspend fun getAttractions(language: String, page: Int): AttractionDto {
        return api.getAttractions(language, page)
    }
}