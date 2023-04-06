package br.com.luishenrique.moviesbrasil.details.models

data class MovieDetail (
    var backdropPath: String,
    var genres: List<Genre>,
    var homepage: String,
    var id: Long,
    var originalTitle: String,
    var overview: String,
    var popularity: Double,
    var releaseDate: String,
    var voteAverage: Double,
    var voteCount: Long
)