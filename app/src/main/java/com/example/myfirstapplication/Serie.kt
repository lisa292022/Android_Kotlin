package com.example.myfirstapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun SerieScreen(viewModel: MainViewModel) {
    // Collecte de la liste des séries
    val seriesList by viewModel.series.collectAsState()

    // Récupération de la liste des séries à afficher
    val series = seriesList.firstOrNull()?.results?.filterNotNull().orEmpty()

    // Appel de la méthode pour récupérer les séries si la liste est vide
    LaunchedEffect(series) {
        if (series.isEmpty()) {
            viewModel.getSeriesInitiaux() // Remplacer par la méthode qui charge les séries
        }
    }

    // Affichage de la grille des séries
    if (series.isNotEmpty()) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(series) { serie ->
                SerieItem(serie)
            }
        }
    } else {
        // Affichage d'un message de chargement si la liste est vide
        Text("Chargement des séries...", Modifier.padding(16.dp))
    }
}

@Composable
fun SerieItem(serie: SerieLight) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Construction de l'URL de l'image de la série
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
        // Titre de la série
        Text(
            text = serie.name ?: "Titre inconnu",
            modifier = Modifier.padding(top = 8.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center // Alignement centré
        )
        // Date de sortie de la série
        Text(
            text = serie.first_air_date ?: "Date inconnue",
            modifier = Modifier.padding(top = 4.dp),
            textAlign = TextAlign.Center // Alignement centré si nécessaire
        )
    }
}
