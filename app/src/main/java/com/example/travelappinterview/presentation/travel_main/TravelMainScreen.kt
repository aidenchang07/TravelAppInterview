package com.example.travelappinterview.presentation.travel_main

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.travelappinterview.R
import com.example.travelappinterview.common.Language
import com.example.travelappinterview.presentation.Screen
import com.example.travelappinterview.presentation.components.StandardTopAppBar
import com.example.travelappinterview.presentation.travel_main.components.BottomSheetContent
import com.example.travelappinterview.presentation.travel_main.components.TravelMainItem
import kotlinx.coroutines.launch

/**
 * Created by AidenChang 2024/02/22
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelMainScreen(
    navController: NavController,
    viewModel: TravelMainViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val lazyListState = rememberLazyListState()

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    val languageMap = mapOf(
        Language.TW to "繁體中文",
        Language.CN to "zh-cn",
        Language.EN to "English",
        Language.JA to "ja",
        Language.KO to "ko",
        Language.ES to "es",
        Language.ID to "id",
        Language.TH to "th",
        Language.VI to "vi"
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StandardTopAppBar(
            modifier = Modifier.fillMaxWidth(),
            title = "Taipei Tour",
            navController = navController,
            actions = {
                IconButton(
                    onClick = {
                        showBottomSheet = true
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.translation),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        )
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
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
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {
                BottomSheetContent(
                    languageMap = languageMap,
                    currentLanguage = viewModel.loadLanguage(),
                    onLanguageSelected = { language ->
                        viewModel.updateLanguage(language)
                        showBottomSheet = false
                        scope.launch { sheetState.hide() }
                    }
                )
            }
        }
    }
}