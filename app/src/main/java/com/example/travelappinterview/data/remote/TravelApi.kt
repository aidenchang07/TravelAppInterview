package com.example.travelappinterview.data.remote

import com.example.travelappinterview.data.remote.attractions_all_dto.AttractionDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by AidenChang 2024/02/22
 */
interface TravelApi {

    @Headers("Accept: application/json")
    @GET("{lang}/Attractions/All")
    suspend fun getAttractions(
        @Path("lang") lang: String,
        @Query("page") page: Int
    ): AttractionDto
}