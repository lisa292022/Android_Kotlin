package com.example.myfirstapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import coil.request.ImageRequest


@Composable
fun ActeurScreen(viewModel: MainViewModel) {
    val acteurs by viewModel.personnes.collectAsState()

    if (acteurs.isEmpty()) {
        viewModel.getPersonnesInitiales()
    }


    if (acteurs.isNotEmpty()) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(acteurs) { personne ->
                PersonneItem(personne)
            }
        }
    } else {
        Text("Chargement des personnalités...", Modifier.padding(16.dp))
    }
}

@Composable
fun PersonneItem(personne: ActeurLight) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val imageUrl = personne.profile_path?.let { "https://image.tmdb.org/t/p/w500$it" } ?: ""
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = personne.name ?: "Photo de la personne",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )
        Text(
            text = personne.name ?: "Nom pas connu",
            modifier = Modifier.padding(top = 8.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )
    }
}
