package com.example.travelappinterview.data.remote.attractions_all_dto

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("introduction")
    val introduction: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("images")
    val images: List<Image>
)