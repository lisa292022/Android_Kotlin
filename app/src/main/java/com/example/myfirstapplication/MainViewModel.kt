package com.example.myfirstapplication
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class MainViewModel : ViewModel() {
    val movies = MutableStateFlow<List<TmdbFilms>>(listOf())
    val series = MutableStateFlow<List<TmdbSeries>>(listOf())
    val api_key = "b229e69561a342c12d5048557ba6e35f"

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();

    val api = retrofit.create(Api::class.java)

    fun getFilmsInitiaux() {
        viewModelScope.launch {
            movies.value = listOf(api.lastmovies(api_key))

        }
    }
    fun searchMovies(query: String) {
        viewModelScope.launch {
            val response = api.searchMovies(api_key, query)
            movies.value = listOf(response)
        }
    }

    fun getSeriesInitiaux() {
        viewModelScope.launch {
            movies.value = listOf(api.lastmovies(api_key))

        }
    }
    /*fun searchSeries(query: String) {
        viewModelScope.launch {
            val response = api.searchSeries(api_key, query)
            movies.value = listOf(response)
        }
    }*/

}