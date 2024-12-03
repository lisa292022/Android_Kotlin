import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.example.myfirstapplication.SerieDestination

@Composable
fun ProfileScreen(classes: WindowSizeClass, innerPadding: PaddingValues, navController: NavHostController) {
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
                Profile("Lisa Defeuillet")
                ProfileStatus("BUT MMI", "Alternance")
                ContactInfo("lisa.defeuillet@gmail.com", "linkedin.com/in/lisa-defeuillet")
                StartButton(navController)
                NavigateToSeriesButton(navController)  // Ajout du bouton pour naviguer vers les séries
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
                    Profile("Lisa Defeuillet")
                }
                Column() {
                    ProfileStatus("BUT MMI", "Alternance")
                    Spacer(modifier = Modifier.height(15.dp))
                    ContactInfo("lisa.defeuillet@gmail.com", "linkedin.com/in/lisa-defeuillet")
                    StartButton(navController)
                    NavigateToSeriesButton(navController)  // Ajout du bouton pour naviguer vers les séries
                }
            }
        }
    }
}


@Composable
fun Profile(name: String) {
    Image(
        painterResource(R.drawable.fleur),
        contentDescription = "Image de profil",
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
    Text(
        text = name,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable

fun ProfileStatus(status1: String, status2: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(status1, fontSize = 16.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(4.dp))
        Text(status2, fontSize = 16.sp, color = Color.Black)
    }
}

@Composable

fun ContactInfo(email: String, linkedin: String) {
    Column(horizontalAlignment = Alignment.Start) {
        Row() {
            Image(
                painterResource(R.drawable.t_l_chargement__2_),
                modifier = Modifier.size(24.dp),
                contentDescription = "email Image",
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text("$email", fontSize = 16.sp, color = Color.Black
            )
        }
        Row() {
            Image(
                painterResource(R.drawable.linkedin),
                modifier = Modifier.size(24.dp),
                contentDescription = "Profile Image",
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text("$linkedin", fontSize = 16.sp, color = Color.Black)
        }
    }
}

@Composable

fun StartButton(navController : NavHostController) {
    Button(
        modifier = Modifier.padding(0.dp, 50.dp, 0.dp, 0.dp),
        onClick = { navController.navigate(FilmDestination::class.java.simpleName) }) {
        Text("Démarrer")
    }
}

@Composable
fun NavigateToSeriesButton(navController: NavHostController) {
    Button(
        modifier = Modifier.padding(0.dp, 50.dp, 0.dp, 0.dp),
        onClick = { navController.navigate(SerieDestination::class.java.simpleName) }) {
        Text("Séries")
    }
}