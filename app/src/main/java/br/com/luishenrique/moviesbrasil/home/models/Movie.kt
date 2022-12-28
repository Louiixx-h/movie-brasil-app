package br.com.luishenrique.moviesbrasil.home.models

data class Movie(
    val id: Int?,
    val title: String?,
    val voteAverage: String?,
    val posterPath: String?,
    val backdropPath: String?,
    var genres: List<Genre>? = emptyList(),
)