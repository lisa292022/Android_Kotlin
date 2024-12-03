package com.example.myfirstapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun SeriesDetailScreen(viewModel: MainViewModel, seriesId: String) {
    // Charger les détails de la série
    LaunchedEffect(seriesId) {
        viewModel.getSerieDetails(seriesId)
    }

    val seriesDetails by viewModel.serieDetails.collectAsState()

    // Affichage des détails
    if (seriesDetails != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            seriesDetails?.poster_path?.let { path ->
                val imageUrl = "https://image.tmdb.org/t/p/w500$path"
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Affiche de la série",
                    modifier = Modifier.height(300.dp)
                )
            }
            Text("Titre : ${seriesDetails?.name}")
            Text("Première diffusion : ${seriesDetails?.first_air_date}")
            Text("Résumé : ${seriesDetails?.overview}")
        }

    } else {
        Text("Chargement des détails...", Modifier.padding(16.dp))
    }
}
