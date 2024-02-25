package com.example.travelappinterview.presentation.travel_main

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.travelappinterview.presentation.Screen
import com.example.travelappinterview.presentation.travel_main.components.TravelMainItem

/**
 * Created by AidenChang 2024/02/22
 */

@Composable
fun TravelMainScreen(
    navController: NavController,
    viewModel: TravelMainViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val lazyListState = rememberLazyListState()
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                state = lazyListState,
            ) {
                itemsIndexed(state.attractions) { index, attraction ->
                    TravelMainItem(
                        attraction = attraction,
                        onItemClick = {
                            navController.navigate(Screen.TravelDetailScreen.route + "/${attraction.id}/${attraction.page}")
                            Log.v("testNavigate", "attraction.id: ${attraction.id}, state.currentPage: ${attraction.page}")
                        }
                    )
                    Log.v("testNavigate", "state.attractions.size: ${state.attractions.size}, index: ${index}")
                    if (index == state.attractions.size - 1) {
                        viewModel.loadMoreAttractions()
                    }
                }
            }
            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}