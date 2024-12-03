import com.example.myfirstapplication.ActeurLight
import com.example.myfirstapplication.FilmLight
import com.example.myfirstapplication.SeriesLight
import com.example.myfirstapplication.TmdbActeur
import com.example.myfirstapplication.TmdbFilms
import com.example.myfirstapplication.TmdbSeries
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("trending/movie/week")
    suspend fun lastmovies(@Query("api_key") apiKey: String): TmdbFilms

    // Nouvelle méthode pour rechercher des films par mot clé
    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): TmdbFilms

    @GET("trending/tv/week")
    suspend fun latestSeries(@Query("api_key") apiKey: String): TmdbSeries

    @GET("trending/person/week")
    suspend fun latestPersons(@Query("api_key") apiKey: String): TmdbActeur

    @GET("search/tv")
    suspend fun searchSeries(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): TmdbSeries

    @GET("search/person")
    suspend fun searchPersons(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): TmdbActeur

    @GET("movie/{movie_id}")
    suspend fun getFilmDetails(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String
    ): FilmLight


    @GET("tv/{tv_id}")
    suspend fun getSerieDetails(
        @Path("tv_id") tvId: String,
        @Query("api_key") apiKey: String
    ): SeriesLight
}


