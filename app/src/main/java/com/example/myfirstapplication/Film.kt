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
fun FilmsScreen(viewModel: MainViewModel) {
    // Collecte de la liste des films
    val moviesList by viewModel.movies.collectAsState()

    // Récupération de la liste des films à afficher
    val films = moviesList.firstOrNull()?.results?.filterNotNull().orEmpty()

    // Appel de la méthode pour récupérer les films si la liste est vide
    LaunchedEffect(films) {
        if (films.isEmpty()) {
            viewModel.getFilmsInitiaux()
        }
    }

    // Affichage de la grille des films
    if (films.isNotEmpty()) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(films) { film ->
                FilmItem(film)
            }
        }
    } else {
        // Affichage d'un message de chargement si la liste est vide
        Text("Chargement des films...", Modifier.padding(16.dp))
    }
}

@Composable
fun FilmItem(film: FilmLight) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
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
            textAlign = TextAlign.Center // Ajout de l'alignement centré
        )
        Text(
            text = film.release_date ?: "Date inconnue",
            modifier = Modifier.padding(top = 4.dp),
            textAlign = TextAlign.Center // Ajout de l'alignement centré si nécessaire
        )
    }
}