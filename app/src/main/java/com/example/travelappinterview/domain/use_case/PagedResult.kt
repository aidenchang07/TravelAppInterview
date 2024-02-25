package com.example.travelappinterview.domain.use_case

/**
 * Created by AidenChang 2024/02/25
 */
data class PagedResult<T>(
    val data: List<T>,
    val isLastPage: Boolean
)
