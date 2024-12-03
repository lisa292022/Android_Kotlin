import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.myfirstapplication.FilmDestination
import com.example.myfirstapplication.R

@Composable
fun ActorScreen(classes: WindowSizeClass, innerPadding: PaddingValues, navController : NavHostController) {
    val classeLargeur = classes.windowWidthSizeClass
    when (classeLargeur) {
        WindowWidthSizeClass.COMPACT -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(0.dp, 100.dp, 0.dp, 0.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Actor("Acteurs")
            }
        }

        else -> {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier.padding(end = 70.dp)
                ) {
                    Profile("Acteurs")
                }
            }
        }
    }
}

@Composable
fun Actor(name: String) {
    Image(
        painterResource(R.drawable.t_l_chargement),
        contentDescription = "Image des acteurs",
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
    Text(
        text = name,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
}

