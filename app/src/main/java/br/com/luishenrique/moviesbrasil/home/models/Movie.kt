package br.com.luishenrique.moviesbrasil.home.models

data class Movie(
    val id: Int,
    val title: String,
    val voteAverage: Double,
    val posterPath: String,
    val backdropPath: String,
    var genres: List<Int>,
)