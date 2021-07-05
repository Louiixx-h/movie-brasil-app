package br.com.luishenrique.moviesbrasil.data.models

class ResponseMovieNowPlaying(
    val results: List<MovieNowPlaying>
)

class MovieNowPlaying(
    val title: String,
    val vote_average: String,
    val poster_path: String,
    val backdrop_path: String)