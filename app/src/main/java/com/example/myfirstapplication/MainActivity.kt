package com.example.myfirstapplication

import FilmDetailScreen
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
import com.example.myfirstapplication.ui.theme.MyFirstApplicationTheme

@Serializable
class ProfileDest

@Serializable
class VideDest

@Serializable
class FilmsDest

@Serializable
class SeriesDest

@Serializable
class ActeursDest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(viewModel: MainViewModel, currentDestination: String) {
    var isSearching by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    val placeholderText = when (currentDestination) {
        "Films" -> "Rechercher un film..."
        "Series" -> "Rechercher une série..."
        "Acteurs" -> "Rechercher un acteur..."
        else -> "Rechercher..."
    }

    TopAppBar(
        title = {
            if (isSearching) {
                TextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                    },
                    placeholder = { Text(placeholderText) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                Text(currentDestination)
            }
        },
        actions = {
            IconButton(onClick = {
                if (isSearching) {
                    when (currentDestination) {
                        "Films" -> viewModel.searchMovies(searchQuery.text)
                        "Series" -> viewModel.searchSeries(searchQuery.text)
                        "Acteurs" -> viewModel.searchPersons(searchQuery.text)
                    }
                    isSearching = false
                    searchQuery = TextFieldValue("")
                } else {
                    isSearching = true
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_search_24),
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
                        val currentDestinationLabel = when {
                            currentDestination?.hasRoute<FilmsDest>() == true -> "Films"
                            currentDestination?.hasRoute<SeriesDest>() == true -> "Series"
                            currentDestination?.hasRoute<ActeursDest>() == true -> "Acteurs"
                            currentDestination?.hasRoute<VideDest>() == true -> "Vide"
                            else -> "Profil"
                        }

                        if (currentDestinationLabel != "Profil") {
                            SearchTopBar(
                                viewModel = viewModel,
                                currentDestination = currentDestinationLabel
                            )
                        }
                    },
                    bottomBar = {
                        if (currentDestination?.hasRoute<ProfileDest>() == false) {
                            NavigationBar {
                                NavigationBarItem(
                                    icon = {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_android_black_24dp),
                                            contentDescription = "vide"
                                        )
                                    },
                                    label = { Text("Vide") },
                                    selected = currentDestination?.hasRoute<VideDest>() == true,
                                    onClick = { navController.navigate(VideDest()) }
                                )
                                NavigationBarItem(
                                    icon = {
                                        Icon(
                                            painter = painterResource(id = R.drawable.baseline_live_tv_24),
                                            contentDescription = "movies"
                                        )
                                    },
                                    label = { Text("Films") },
                                    selected = currentDestination?.hasRoute<FilmsDest>() == true,
                                    onClick = { navController.navigate(FilmsDest()) }
                                )
                                NavigationBarItem(
                                    icon = {
                                        Icon(
                                            painter = painterResource(id = R.drawable.baseline_movie_filter_24),
                                            contentDescription = "series"
                                        )
                                    },
                                    label = { Text("Series") },
                                    selected = currentDestination?.hasRoute<SeriesDest>() == true,
                                    onClick = { navController.navigate(SeriesDest()) }
                                )
                                NavigationBarItem(
                                    icon = {
                                        Icon(
                                            painter = painterResource(id = R.drawable.baseline_account_circle_24),
                                            contentDescription = "actors"
                                        )
                                    },
                                    label = { Text("Acteurs") },
                                    selected = currentDestination?.hasRoute<ActeursDest>() == true,
                                    onClick = { navController.navigate(ActeursDest()) }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController,
                        startDestination = ProfileDest(),
                        Modifier.padding(innerPadding)
                    ) {
                        composable<ProfileDest> {
                            ProfileScreen(
                                classes = windowSizeClass,
                                innerPadding = innerPadding,
                                navController
                            )
                        }
                        composable<VideDest> {
                            VideScreen(viewModel = viewModel, navController)
                        }

                        composable<FilmsDest> {
                            FilmsScreen(viewModel = viewModel, navController)
                        }
                        composable<SeriesDest> {
                            SeriesScreen(viewModel = viewModel, navController)
                        }
                        composable<ActeursDest> {
                            ActeurScreen(viewModel = viewModel)
                        }
                        composable("FilmDetailScreen/{filmId}") { backStackEntry ->
                            val filmId = backStackEntry.arguments?.getString("filmId") ?: return@composable
                            FilmDetailScreen(viewModel, filmId)
                        }
                        composable("SeriesDetailScreen/{seriesId}") { backStackEntry ->
                            val seriesId = backStackEntry.arguments?.getString("seriesId") ?: return@composable
                            SeriesDetailScreen(viewModel, seriesId)
                        }
                    }
                }
            }
        }
    }
}
