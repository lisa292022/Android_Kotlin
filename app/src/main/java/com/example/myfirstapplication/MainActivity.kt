package com.example.myfirstapplication

import ActorScreen
import ProfileScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import androidx.navigation.NavDestination.Companion.hasRoute
import com.example.myfirstapplication.MainViewModel
import com.example.myfirstapplication.ui.theme.MyFirstApplicationTheme

@Serializable
class ProfilDestination

@Serializable
class FilmDestination

@Serializable
class SerieDestination

@Serializable
class ActorDestination

@Serializable
class FilmDetailDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(viewModel: MainViewModel) {
    var isSearching by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    TopAppBar(
        title = {
            if (isSearching) {
                TextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                    },
                    placeholder = { Text("Rechercher un film...") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                Text("Films")
            }
        },
        actions = {
            IconButton(onClick = {
                if (isSearching) {
                    // Rechercher les films par mot-clé
                    viewModel.searchMovies(searchQuery.text)
                    // Réinitialiser le champ de recherche
                    isSearching = false
                    searchQuery = TextFieldValue("")
                } else {
                    isSearching = true
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_search_24), // Utilisez une icône qui existe pour tester
                    contentDescription = "Recherche"
                )

            }
        }
    )
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass

            MyFirstApplicationTheme {
                // Utilisation du ViewModel lié au cycle de vie
                val viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        // Affiche la SearchTopBar uniquement si l'écran actuel n'est pas la page Profil
                        if (currentDestination?.route != ProfilDestination::class.java.simpleName) {
                            SearchTopBar(viewModel = viewModel)
                        }
                    },
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                icon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_movie_filter_24),
                                        contentDescription = "movies"
                                    )
                                },
                                label = { Text("Films") },
                                selected = currentDestination?.hasRoute<FilmDestination>() == true,
                                onClick = { navController.navigate(FilmDestination()) })
                            NavigationBarItem(
                                icon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_live_tv_24),
                                        contentDescription = "series"
                                    )
                                },
                                label = { Text("Series") },
                                selected = currentDestination?.hasRoute<SerieDestination>() == true,
                                onClick = { navController.navigate(SerieDestination()) })
                            NavigationBarItem(
                                icon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_account_circle_24),
                                        contentDescription = "actors"
                                    )
                                },
                                label = { Text("Acteurs") },
                                selected = currentDestination?.hasRoute<ActorDestination>() == true,
                                onClick = { navController.navigate(ActorDestination()) })
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController,
                        startDestination = ProfilDestination::class.java.simpleName,
                        Modifier.padding(innerPadding)
                    ) {
                        composable(ProfilDestination::class.java.simpleName) {
                            ProfileScreen(
                                classes = windowSizeClass,
                                innerPadding = innerPadding,
                                navController
                            )
                        }
                        composable(FilmDestination::class.java.simpleName) {
                            FilmsScreen(viewModel = viewModel, navController = navController)
                        }
                        composable(SerieDestination::class.java.simpleName) {
                            SerieScreen(viewModel
                            )
                        }

                        composable(ActorDestination::class.java.simpleName) {
                            ActorScreen(
                                classes = windowSizeClass,
                                innerPadding = innerPadding,
                                navController
                            )
                        }
                    }
                }
            }
        }
    }
}