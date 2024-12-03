package com.example.myfirstapplication

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController

@Composable
fun FilmsScreen(viewModel: MainViewModel, navController: NavController) {
    val movies by viewModel.movies.collectAsState()

    if (movies.isEmpty()) {
        viewModel.getFilmsInitiaux()
    }

    if (movies.isNotEmpty()) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(movies) { film ->
                FilmItem(film, navController)
            }
        }
    } else {
        Text("Chargement des films...", Modifier.padding(16.dp))
    }
}

@Composable
fun FilmItem(film: FilmLight, navController: NavController) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { navController.navigate("FilmDetailScreen/${film.id}") }, // Navigation avec l'ID du film
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val imageUrl = film.poster_path?.let { "https://image.tmdb.org/t/p/w500$it" } ?: ""
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = film.title ?: "Affiche du film",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )
        Text(
            text = film.title ?: "Titre inconnu",
            modifier = Modifier.padding(top = 8.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )
        Text(
            text = film.release_date ?: "Date inconnue",
            modifier = Modifier.padding(top = 4.dp),
            textAlign = TextAlign.Center
        )
    }
}
