package com.example.travelappinterview.presentation.travel_main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.travelappinterview.domain.model.Attraction

/**
 * Created by AidenChang 2024/02/22
 */

@Composable
fun TravelMainItem(
    attraction: Attraction,
    onItemClick: (Attraction) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClick(attraction) }
    ) {
        Image(
            modifier = Modifier
                .size(100.dp),
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = attraction.imageUrl)
                    .apply(block = fun ImageRequest.Builder.() {
                        crossfade(true)
                    }).build()
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.Top)
                .padding(top = 4.dp)
        ) {
            Text(
                text = attraction.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = attraction.introduction,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
        Icon(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(24.dp),
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TravelMainItemPreview() {
    val fakeAttraction = Attraction(
        id = 1,
        name = "景點名稱",
        introduction = "這是一個假的景點描述，用於預覽。",
        imageUrl = "",
        page = 1
    )
    TravelMainItem(fakeAttraction, onItemClick = {})
}
