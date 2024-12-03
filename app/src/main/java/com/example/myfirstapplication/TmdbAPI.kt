package com.example.myfirstapplication

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("trending/movie/week")
    suspend fun lastmovies(@Query("api_key") api_key: String): TmdbFilms

    // Nouvelle méthode pour rechercher des films par mot-clé
    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") api_key: String,
        @Query("query") query: String
    ): TmdbFilms
}

val retrofit = Retrofit.Builder()
    .baseUrl("https://api.themoviedb.org/3/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build();

val api = retrofit.create(Api::class.java)
// à partir de là, on peut appeler api.lastMovies(...)




