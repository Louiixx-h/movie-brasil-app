package br.com.luishenrique.moviesbrasil.service

import br.com.luishenrique.moviesbrasil.details.models.MovieDetail

interface Storage {
    var moviesSaved: MutableList<MovieDetail>

    fun removeMovie(movie: MovieDetail): Boolean
    fun addMovie(newMovie: MovieDetail): Boolean
    fun hasMovie(newMovie: MovieDetail): Boolean
}
