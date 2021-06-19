package br.com.luishenrique.moviesbrasil.models

class ResponseMovieTopRated(
    val results: List<MovieTopRated>
)

class MovieTopRated(
    val title: String,
    val vote_average: String,
    val poster_path: String)
