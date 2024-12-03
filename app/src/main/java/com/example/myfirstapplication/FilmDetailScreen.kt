import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myfirstapplication.Cast

import com.example.myfirstapplication.MainViewModel
@Composable
fun FilmDetailScreen(viewModel: MainViewModel, filmId: String) {
    val filmDetails by viewModel.filmDetails.collectAsState()

    LaunchedEffect(filmId) {
        viewModel.getFilmDetails(filmId)
    }

    if (filmDetails != null) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            // Affichage de l'affiche du film
            filmDetails?.poster_path?.let { path ->
                val imageUrl = "https://image.tmdb.org/t/p/w500$path"
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Affiche du film",
                    modifier = Modifier.height(200.dp)
                        .align(alignment = Alignment.CenterHorizontally)
                )
            }
            // Affichage des détails du film
            Text("Titre : ${filmDetails?.title}")
            Text("Date de sortie : ${filmDetails?.release_date}")
            Text("Résumé : ${filmDetails?.overview}")
            Text("Budget : ${filmDetails?.budget}")
            Text("Statut : ${filmDetails?.status}")

            // Affichage des acteurs
            filmDetails?.credits?.cast?.let {
                if (it.isNotEmpty()) {
                    Text("Acteurs principaux :", style = MaterialTheme.typography.bodyLarge)
                    LazyRow {
                        it.forEach { actor ->
                            item {
                                ActorItem(actor)}
                        }
                    }
                }
            }


        }
    } else {
        Text("Chargement des détails du film...", Modifier.padding(16.dp))
    }
}


@Composable
fun ActorItem(actor: Cast) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(8.dp)) {
        val imageUrl = actor.profile_path?.let { "https://image.tmdb.org/t/p/w500$it" } ?: ""
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = actor.name ?: "Acteur",
            modifier = Modifier.size(100.dp)
        )
        Text(text = actor.name ?: "Nom inconnu", textAlign = TextAlign.Center)
        Text(text = actor.character ?: "Rôle inconnu", textAlign = TextAlign.Center)
    }
}
