package com.example.myfirstapplication

import Api
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class MainViewModel : ViewModel() {
    val movies = MutableStateFlow<List<FilmLight>>(listOf())
    val series = MutableStateFlow<List<SeriesLight>>(listOf())
    val personnes = MutableStateFlow<List<ActeurLight>>(listOf())

    val filmDetails = MutableStateFlow<FilmDetaille?>(null)
    val serieDetails = MutableStateFlow<SerieDetaille?>(null)

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

    // Fonction pour récupérer les détails d'un film avec le cast
    fun getFilmDetails(filmId: String) {
        viewModelScope.launch {
            try {
                val details = api.getFilmDetails(apiKey = api_key, filmId = filmId)
                filmDetails.value = details
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Fonction pour récupérer les détails d'une série avec le cast
    fun getSerieDetails(serieId: String) {
        viewModelScope.launch {
            try {
                val details = api.getSerieDetails(apiKey = api_key, serieId = serieId)
                serieDetails.value = details
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}






