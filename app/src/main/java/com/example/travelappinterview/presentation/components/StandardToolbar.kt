package com.example.travelappinterview.presentation.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

/**
 * Created by AidenChang 2024/02/23
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardToolbar(
    navController: NavController,
    modifier: Modifier,
    title: @Composable () -> Unit = {}
) {
    TopAppBar(
        title = title,
        modifier = modifier
    )
}