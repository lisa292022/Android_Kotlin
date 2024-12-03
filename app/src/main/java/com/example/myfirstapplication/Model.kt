package com.example.myfirstapplication

data class TmdbFilms(
    val page: Int? = 0,
    val results: List<FilmLight> = listOf(),
    val total_pages: Int? = 0,
    val total_results: Int? = 0
)

data class FilmLight(
    val adult: Boolean? = false,
    val backdrop_path: String? = "",
    val genre_ids: List<Int?>? = listOf(),
    val id: Int? = 0,
    val media_type: String? = "",
    val original_language: String? = "",
    val original_title: String? = "",
    val overview: String? = "",
    val popularity: Double? = 0.0,
    val poster_path: String? = "",
    val release_date: String? = "",
    val title: String? = "",
    val video: Boolean? = false,
    val vote_average: Double? = 0.0,
    val vote_count: Int? = 0
)

data class TmdbActeur(
    val page: Int? = 0,
    val results: List<ActeurLight> = listOf(),
    val total_pages: Int? = 0,
    val total_results: Int? = 0
)

data class ActeurLight(
    val adult: Boolean? = false,
    val gender: Int? = 0,
    val id: Int? = 0,
    val known_for_department: String? = "",
    val media_type: String? = "",
    val name: String? = "",
    val original_name: String? = "",
    val popularity: Double? = 0.0,
    val profile_path: String? = ""
)

data class TmdbSeries(
    val page: Int? = 0,
    val results: List<SeriesLight> = listOf(),
    val total_pages: Int? = 0,
    val total_results: Int? = 0
)

data class SeriesLight(
    val adult: Boolean? = false,
    val backdrop_path: String? = "",
    val first_air_date: String? = "",
    val genre_ids: List<Int?>? = listOf(),
    val id: Int? = 0,
    val media_type: String? = "",
    val name: String? = "",
    val origin_country: List<String?>? = listOf(),
    val original_language: String? = "",
    val original_name: String? = "",
    val overview: String? = "",
    val popularity: Double? = 0.0,
    val poster_path: String? = "",
    val vote_average: Double? = 0.0,
    val vote_count: Int? = 0
)

data class FilmDetaille(
    val adult: Boolean? = false,
    val backdrop_path: String? = "",
    val budget: Int? = 0,
    val homepage: String? = "",
    val id: Int? = 0,
    val imdb_id: String? = "",
    val origin_country: List<String?>? = listOf(),
    val original_language: String? = "",
    val original_title: String? = "",
    val overview: String? = "",
    val popularity: Double? = 0.0,
    val poster_path: String? = "",
    val release_date: String? = "",
    val revenue: Int? = 0,
    val runtime: Int? = 0,
    val status: String? = "",
    val tagline: String? = "",
    val title: String? = "",
    val video: Boolean? = false,
    val vote_average: Double? = 0.0,
    val vote_count: Int? = 0
)


data class Cast(
    val adult: Boolean? = false,
    val cast_id: Int? = 0,
    val character: String? = "",
    val credit_id: String? = "",
    val gender: Int? = 0,
    val id: Int? = 0,
    val known_for_department: String? = "",
    val name: String? = "",
    val order: Int? = 0,
    val original_name: String? = "",
    val popularity: Double? = 0.0,
    val profile_path: String? = ""
)


data class SerieDetaille(
    val adult: Boolean? = false,
    val backdrop_path: String? = "",
    val episode_run_time: List<Int?>? = listOf(),
    val first_air_date: String? = "",
    val homepage: String? = "",
    val id: Int? = 0,
    val in_production: Boolean? = false,
    val languages: List<String?>? = listOf(),
    val last_air_date: String? = "",
    val name: String? = "",
    val next_episode_to_air: Any? = Any(),
    val number_of_episodes: Int? = 0,
    val number_of_seasons: Int? = 0,
    val origin_country: List<String?>? = listOf(),
    val original_language: String? = "",
    val original_name: String? = "",
    val overview: String? = "",
    val popularity: Double? = 0.0,
    val poster_path: String? = "",
    val status: String? = "",
    val tagline: String? = "",
    val type: String? = "",
    val vote_average: Double? = 0.0,
    val vote_count: Int? = 0
)


