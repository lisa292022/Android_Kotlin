package com.example.myfirstapplication

import Api
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class MainViewModel : ViewModel() {
    val movies = MutableStateFlow<List<FilmLight>>(listOf())
    val series = MutableStateFlow<List<SeriesLight>>(listOf())
    val personnes = MutableStateFlow<List<ActeurLight>>(listOf())

    val filmDetails = MutableStateFlow<FilmLight?>(null) // Stockage des détails d'un film
    val serieDetails = MutableStateFlow<SeriesLight?>(null) // Stockage des détails d'une série

    val api_key = "b229e69561a342c12d5048557ba6e35f"

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();

    val api = retrofit.create(Api::class.java)

    fun getFilmsInitiaux() {
        viewModelScope.launch {
            movies.value = api.lastmovies(api_key).results
        }
    }

    // Méthode pour charger les séries de la semaine
    fun getSeriesInitiaux() {
        viewModelScope.launch {
            series.value = api.latestSeries(api_key).results
        }
    }

    // Méthode pour charger les personnalités de la semaine
    fun getPersonnesInitiales() {
        viewModelScope.launch {
            personnes.value = api.latestPersons(api_key).results
        }
    }

    fun searchMovies(query: String) {
        viewModelScope.launch {
            movies.value = api.searchMovies(api_key, query).results
        }
    }

    fun searchSeries(query: String) {
        viewModelScope.launch {
            series.value = api.searchSeries(api_key, query).results
        }
    }

    fun searchPersons(query: String) {
        viewModelScope.launch {
            personnes.value = api.searchPersons(api_key, query).results
        }
    }

    // Nouvelle méthode : récupérer les détails d'un film
    fun getFilmDetails(filmId: String) {
        Log.v("message", "getFilmdetails")
        viewModelScope.launch {
            try {
                Log.v("message", "getFilmdetails")
                filmDetails.value = api.getFilmDetails(apiKey = api_key, movieId = filmId)
                Log.v("message", filmDetails.value?.title ?:"")
            } catch (e: Exception) {
                Log.v("message", "erreur")
                filmDetails.value = null
                e.printStackTrace() // Log d'erreur pour débogage
            }
        }
    }

    // Nouvelle méthode : récupérer les détails d'une série
    fun getSerieDetails(serieId: String) {
        viewModelScope.launch {
            try {
                serieDetails.value = api.getSerieDetails(apiKey= api_key, tvId = serieId)
            } catch (e: Exception) {
                serieDetails.value = null
                e.printStackTrace() // Log d'erreur pour débogage
            }
        }
    }



}


