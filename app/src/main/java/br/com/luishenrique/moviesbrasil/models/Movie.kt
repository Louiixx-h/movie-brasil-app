package br.com.luishenrique.moviesbrasil.models

class ResponseMovie(
    val results: List<MoviePopular>
)

class Movie(
    val title: String,
    val vote_average: String,
    val poster_path: String,
    val backdrop_path: String)