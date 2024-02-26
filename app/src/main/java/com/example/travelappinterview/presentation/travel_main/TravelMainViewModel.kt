package com.example.travelappinterview.presentation.travel_main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelappinterview.common.Resource
import com.example.travelappinterview.domain.use_case.GetAttractionsUseCase
import com.example.travelappinterview.domain.use_case.GetLanguageUseCase
import com.example.travelappinterview.domain.use_case.SetLanguageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by AidenChang 2024/02/22
 */

@HiltViewModel
class TravelMainViewModel @Inject constructor(
    private val getAttractionsUseCase: GetAttractionsUseCase,
    private val getLanguageUseCase: GetLanguageUseCase,
    private val setLanguageUseCase: SetLanguageUseCase
): ViewModel() {
    private val _state = mutableStateOf(TravelMainState())
    val state: State<TravelMainState> = _state
    private var isLastPage = false

    init {
        getAttractions()
    }

    private fun getAttractions() {
        if (_state.value.isLoading) return
        getAttractionsUseCase(getLanguageUseCase()).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        attractions = _state.value.attractions + (result.data?.data ?: emptyList())
                    )
                    isLastPage = result.data?.isLastPage ?: true
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(error = result.message ?:
                    "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun loadMoreAttractions() {
        if (!isLastPage) {
            getAttractions()
        }
    }

    fun loadLanguage(): String {
        return getLanguageUseCase()
    }

    fun updateLanguage(language: String) {
        setLanguageUseCase(language)
        _state.value = _state.value.copy(
            isLoading = false,
            attractions = emptyList(),
            error = "",
            isLastPage = false
        )
        getAttractions()
    }
}