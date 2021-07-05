package br.com.luishenrique.moviesbrasil.data.models

class ResponseMoviePopular(
    val results: List<MoviePopular>
)

class MoviePopular(
    val title: String,
    val vote_average: String,
    val poster_path: String,
    val backdrop_path: String)
