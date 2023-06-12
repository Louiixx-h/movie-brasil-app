package br.com.luishenrique.moviesbrasil.details.models

data class SimilarMovie(
    val id: Int,
    val title: String,
    val voteAverage: Double,
    val posterPath: String
)