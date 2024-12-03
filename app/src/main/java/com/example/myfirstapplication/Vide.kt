package com.example.myfirstapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController

@Composable
fun VideScreen(viewModel: MainViewModel, navController: NavController) {
    val playlist by viewModel.playlist().collectAsState()
    AsyncImage(model = "file:///android_asset/drawable/images/2.jpg",
    contentDescription = "",
     )

                if (playlist.isEmpty()) {
                    viewModel.getPlaylistInitiales()
                }

                if (playlist.isNotEmpty()) {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = 160.dp), // Plus de place pour chaque élément
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp), // Ajout de padding pour l'ensemble de la grille
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(playlist) { playlist ->
                            PlaylistItem(playlist, navController)
                        }
                    }
                } else {
                    Text("Chargement des playlist...", Modifier.padding(16.dp), color = Color.Gray)
                }
            }

    @Composable
    fun PlaylistItem(playlist: PlaylistLight, navController: NavController) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clickable { navController.navigate("PlaylistDetailScreen/${playlist.id}") }
                .shadow(10.dp, shape = MaterialTheme.shapes.medium) // Ajouter une ombre douce pour l'effet de survol
                .clip(MaterialTheme.shapes.medium) // Bordures arrondies
                .background(MaterialTheme.colorScheme.surface) // Fond de carte
                .padding(8.dp), // Ajouter un peu de padding autour du contenu
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val imageUrl = playlist.poster_path?.let { "https://image.tmdb.org/t/p/w500$it" } ?: ""
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = playlist.title ?: "Affiche de la playlist",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp) // Une taille plus grande pour les images
                    .clip(MaterialTheme.shapes.small) // Bordures arrondies pour l'image
            )

            Spacer(modifier = Modifier.height(8.dp)) // Espace entre l'image et le texte

            Text(
                text = playlist.title ?: "Titre inconnu",
                modifier = Modifier.padding(top = 8.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )

            Text(
                text = playlist.description ?: "Description inconnue",
                modifier = Modifier.padding(top = 4.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
            )
        }
    }




}

