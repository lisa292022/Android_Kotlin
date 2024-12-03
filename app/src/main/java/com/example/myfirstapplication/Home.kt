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
import com.example.myfirstapplication.FilmsDest
import com.example.myfirstapplication.VideDest


import com.example.myfirstapplication.R


@Composable
fun ProfileScreen(
    classes: WindowSizeClass,
    innerPadding: PaddingValues,
    navController: NavHostController
) {
    val classeLargeur = classes.windowWidthSizeClass
    when (classeLargeur) {
        WindowWidthSizeClass.COMPACT -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(0.dp, 100.dp, 0.dp, 0.dp),
                verticalArrangement = Arrangement.spacedBy(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Profile("Lisa Defeuillet")
                ProfileStatus("3A BUT MMI", "en alternance")
                ContactInfo("lisa.defeuillet@gmail.com", "linkedin.com/in/lisa-defeuillet")
                StartButton(navController)
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
                    modifier = Modifier.padding(end = 100.dp)
                ) {
                    Profile("Lisa Defeuillet")
                }
                Column() {
                    ProfileStatus("3A BUT MMI", "en alternance")
                    Spacer(modifier = Modifier.height(15.dp))
                    ContactInfo("lisa.defeuillet@gmail.com", "linkedin.com/in/lisa-defeuillet")
                    StartButton(navController)
                }
            }
        }
    }
}

@Composable
fun Profile(name: String) {
    Image(
        painterResource(R.drawable.fleur),
        contentDescription = "image de profil",
        modifier = Modifier
            .size(150.dp) // Réduction de la taille pour un aspect plus équilibré
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
        Text(status1, fontSize = 16.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(4.dp))
        Text(status2, fontSize = 16.sp, color = Color.Gray)
    }
}

@Composable
fun ContactInfo(email: String, linkedin: String) {
    Column(horizontalAlignment = Alignment.Start) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(R.drawable.t_l_chargement__2_),
                contentDescription = "image de l'email",
                modifier = Modifier.size(24.dp) // Taille de l'icône de l'email ajustée
            )
            Spacer(modifier = Modifier.width(8.dp)) // Espacement entre l'icône et le texte
            Text("$email", fontSize = 16.sp, color = Color.Gray)
        }
        Spacer(modifier = Modifier.height(8.dp)) // Espacement entre les lignes
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(R.drawable.linkedin),
                modifier = Modifier.size(24.dp), // Taille de l'icône LinkedIn
                contentDescription = "Image de profil LinkedIn"
            )
            Spacer(modifier = Modifier.width(8.dp)) // Espacement entre l'icône et le texte
            Text("$linkedin", fontSize = 16.sp, color = Color.Gray)
        }
    }
}

@Composable
fun StartButton(navController: NavHostController) {
    Button(
        modifier = Modifier.padding(0.dp, 50.dp, 0.dp, 0.dp),
        onClick = { navController.navigate(VideDest()) }
    ) {
        Text("Démarrer")
    }
}

