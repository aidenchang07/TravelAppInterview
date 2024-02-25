package com.example.travelappinterview.data.remote.attractions_all_dto

import com.example.travelappinterview.domain.model.Attraction
import com.example.travelappinterview.domain.model.AttractionDetail
import com.google.gson.annotations.SerializedName

data class AttractionDto(
    @SerializedName("total")
    val total: Int,
    @SerializedName("data")
    val `data`: List<Data>
)

fun AttractionDto.toAttraction(page: Int): List<Attraction> {
    return this.data.map { data ->
        Attraction(
            id = data.id,
            name = data.name,
            introduction = data.introduction,
            imageUrl = data.images.firstOrNull()?.src ?: "",
            page = page
        )
    }
}

fun AttractionDto.toAttractionDetail(): List<AttractionDetail> {
    return this.data.map { data ->
        AttractionDetail(
            id = data.id,
            name = data.name,
            introduction = data.introduction,
            address = data.address,
            modified = data.modified,
            url = data.url,
            imageUrl = data.images.firstOrNull()?.src ?: ""
        )
    }
}