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
import com.example.myfirstapplication.MainViewModel
@Composable
fun FilmDetailScreen(viewModel: MainViewModel, filmId: String) {

    // Charger les détails du film
    LaunchedEffect(filmId) {
        viewModel.getFilmDetails(filmId)
    }

    val filmDetails by viewModel.filmDetails.collectAsState()

    // Affichage des détails
    if (filmDetails != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            filmDetails?.poster_path?.let { path ->
                val imageUrl = "https://image.tmdb.org/t/p/w500$path"
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Affiche du film",
                    modifier = Modifier.height(300.dp)
                )
            }
            Text("Titre : ${filmDetails?.title}")
            Text("Date de sortie : ${filmDetails?.release_date}")
            Text("Résumé : ${filmDetails?.overview}")
        }
    } else {
        Text("Chargement des détails...", Modifier.padding(40.dp))
    }
}
