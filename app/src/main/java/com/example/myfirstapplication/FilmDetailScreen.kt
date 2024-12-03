package com.example.myfirstapplication

import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun FilmDetailScreen(film: FilmLight, navController: NavHostController) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "Détails du Film")

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Titre: ${film.title}")
        Text(text = "Date de sortie: ${film.release_date}")
        Text(text = "Description: ${film.overview}")

       Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Retour")
        }
    }
}
