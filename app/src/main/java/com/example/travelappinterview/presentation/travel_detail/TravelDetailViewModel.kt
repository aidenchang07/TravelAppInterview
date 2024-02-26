package com.example.travelappinterview.presentation.travel_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelappinterview.common.Constants.PARAM_ATTRACTION_ID
import com.example.travelappinterview.common.Constants.PARAM_PAGE
import com.example.travelappinterview.common.Resource
import com.example.travelappinterview.domain.use_case.GetAttractionDetailUseCase
import com.example.travelappinterview.domain.use_case.GetLanguageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TravelDetailViewModel @Inject constructor(
    getAttractionDetailUseCase: GetAttractionDetailUseCase,
    getLanguageUseCase: GetLanguageUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(TravelDetailState())
    val state: State<TravelDetailState> = _state

    init {
        val id = savedStateHandle.get<String>(PARAM_ATTRACTION_ID)?.toIntOrNull()
        val page = savedStateHandle.get<String>(PARAM_PAGE)?.toIntOrNull()

        if (id == null || page == null) {
            _state.value = TravelDetailState(error = "Required page parameter is missing")
        } else {
            getAttractionDetailUseCase(getLanguageUseCase(), page, id).onEach { result ->
                when(result) {
                    is Resource.Success -> {
                        _state.value =  TravelDetailState(attractionDetail = result.data)
                    }
                    is Resource.Error -> {
                        _state.value = TravelDetailState(error = result.message ?: "An unexpected error occurred")
                    }
                    is Resource.Loading -> {
                        _state.value = TravelDetailState(isLoading = false)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}
