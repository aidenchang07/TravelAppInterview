package com.example.travelappinterview.presentation.travel_main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Created by AidenChang 2024/02/26
 */

@Composable
fun BottomSheetContent(
    languageMap: Map<String, String>,
    currentLanguage: String,
    onLanguageSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        languageMap.forEach { (innerLanguage, name) ->
            val isSelected = currentLanguage == innerLanguage
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onLanguageSelected(innerLanguage) }
                    .padding(16.dp)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
                if (isSelected) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Selected",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}