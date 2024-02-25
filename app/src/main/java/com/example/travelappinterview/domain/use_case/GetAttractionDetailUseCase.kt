package com.example.travelappinterview.domain.use_case

import com.example.travelappinterview.common.Resource
import com.example.travelappinterview.data.remote.attractions_all_dto.toAttractionDetail
import com.example.travelappinterview.domain.model.AttractionDetail
import com.example.travelappinterview.domain.repository.TravelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

/**
 * Created by AidenChang 2024/02/22
 */
class GetAttractionDetailUseCase @Inject constructor(
    private val repository: TravelRepository
) {
    operator fun invoke(language: String, page: Int, id: Int): Flow<Resource<AttractionDetail>> = flow {
        try {
            emit(Resource.Loading())
            // TODO: page need search all
            val attractionsAllDetail = repository.getAttractions(language, page)
                .toAttractionDetail()
                .find { it.id == id }
            if (attractionsAllDetail != null) {
                emit(Resource.Success(attractionsAllDetail))
            } else {
                emit(Resource.Error("Item not found"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}