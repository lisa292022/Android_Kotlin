package com.example.myfirstapplication

import ActorItem
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun SeriesDetailScreen(viewModel: MainViewModel, serieId: String) {
    val serieDetails by viewModel.serieDetails.collectAsState()

    LaunchedEffect(serieId) {
        viewModel.getSerieDetails(serieId)
    }

    if (serieDetails != null) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            // Affichage de l'affiche de la série
            serieDetails?.poster_path?.let { path ->
                val imageUrl = "https://image.tmdb.org/t/p/w500$path"
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Affiche de la série",
                    modifier = Modifier.height(200.dp)
                )
            }
            // Affichage des détails de la série
            Text("Nom : ${serieDetails?.name}")
            Text("Première diffusion : ${serieDetails?.first_air_date}")
            Text("Résumé : ${serieDetails?.overview}")
            Text("Nombre d'épisodes : ${serieDetails?.number_of_episodes}")
            Text("Nombre de saisons : ${serieDetails?.number_of_seasons}")

            // Affichage des acteurs
            serieDetails?.credits?.cast?.let {
                if (it.isNotEmpty()) {
                    Text("Acteurs principaux :", style = MaterialTheme.typography.bodyLarge)
                    LazyRow {
                        it.forEach { actor ->
                            item {
                                ActorItem(actor)
                            }
                        }
                    }
                }
            }


        }
    } else {
        Text("Chargement des détails de la série...", Modifier.padding(16.dp))
    }
}
