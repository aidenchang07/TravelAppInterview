package com.example.travelappinterview.domain.use_case

import com.example.travelappinterview.common.Resource
import com.example.travelappinterview.data.remote.attractions_all_dto.toAttraction
import com.example.travelappinterview.domain.model.Attraction
import com.example.travelappinterview.domain.repository.TravelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
import kotlin.math.ceil

/**
 * Created by AidenChang 2024/02/22
 */
class GetAttractionsUseCase @Inject constructor(
    private val repository: TravelRepository
) {
    private var currentPage = 0
    private val pageSize = 30
    private var isLastPage = false

    operator fun invoke(language: String, forceLoading: Boolean): Flow<Resource<PagedResult<Attraction>>> = flow {
        if (forceLoading) currentPage = 0
        currentPage++
        emit(Resource.Loading())
        try {
            val attractionDto = repository.getAttractions(language, currentPage)
            isLastPage = ceil(attractionDto.total.toDouble() / pageSize.toDouble()).toInt() < currentPage
            emit(Resource.Success(PagedResult(attractionDto.toAttraction(currentPage), isLastPage)))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}