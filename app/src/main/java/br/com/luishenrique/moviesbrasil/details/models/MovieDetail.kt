package br.com.luishenrique.moviesbrasil.details.models

data class MovieDetail (
    var backdropPath: String? = null,
    var genres: List<Genre>? = emptyList(),
    var homepage: String? = null,
    var id: Long? = null,
    var originalTitle: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    var releaseDate: String? = null,
    var voteAverage: Double? = null,
    var voteCount: Long? = null
)