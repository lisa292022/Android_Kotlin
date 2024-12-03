package com.example.myfirstapplication

import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController


@Composable
fun FilmsScreen(viewModel: MainViewModel, navController: NavHostController) {
    // Collecte de la liste des films
    val moviesList by viewModel.movies.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.getFilmsInitiaux()

    }

    // Récupération de la liste des films à afficher
    val films = moviesList.firstOrNull()?.results?.filterNotNull().orEmpty()

    // Affichage de la grille des films
    if (films.isNotEmpty()) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(films) { film ->
                FilmItem(film, navController) // Passe le navController pour gérer la navigation
            }
        }
    } else {
        // Affichage d'un message de chargement si la liste est vide
        Text("Chargement des films...", Modifier.padding(16.dp))
    }
}

@Composable
fun FilmItem(film: FilmLight, navController: NavHostController) {
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
                .clickable {
                    Log.v("zzzzz", "clic sur film")
                    navController.navigate("movieDetail/${film.id}") // Navigue vers l'écran de détails du film
                }
        )
        Text(
            text = film.title ?: "Titre inconnu",
            modifier = Modifier.padding(top = 8.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )
    }
}
