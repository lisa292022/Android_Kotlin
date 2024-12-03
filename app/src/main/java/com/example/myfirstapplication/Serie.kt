package com.example.myfirstapplication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun SeriesScreen(viewModel: MainViewModel, navController: NavController) {
    val series by viewModel.series.collectAsState()

    if (series.isEmpty()) {
        viewModel.getSeriesInitiaux()
    }

    if (series.isNotEmpty()) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(series) { serie ->
                SeriesItem(serie, navController)
            }
        }
    } else {
        Text("Chargement des séries...", Modifier.padding(16.dp))
    }
}

@Composable
fun SeriesItem(serie: SeriesLight, navController: NavController) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { navController.navigate("SeriesDetailScreen/${serie.id}") },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val imageUrl = serie.poster_path?.let { "https://image.tmdb.org/t/p/w500$it" } ?: ""
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = serie.name ?: "Affiche de la série",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )
        Text(
            text = serie.name ?: "Titre inconnu",
            modifier = Modifier.padding(top = 8.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )
        Text(
            text = serie.first_air_date ?: "Date inconnue",
            modifier = Modifier.padding(top = 4.dp),
            textAlign = TextAlign.Center
        )
    }
}
