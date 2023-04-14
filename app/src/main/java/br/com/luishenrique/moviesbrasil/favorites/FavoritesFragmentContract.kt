package br.com.luishenrique.moviesbrasil.favorites

import br.com.luishenrique.moviesbrasil.details.models.MovieDetail

interface FavoritesFragmentContract {
    fun changeVisibilityProgressBar(stateProgressBar: Boolean)
    fun setProgressBar()
    fun setComponents()
    fun removeMovie(movie: MovieDetail)
    fun goToDetails(movie: MovieDetail)
    fun onClick(movie: MovieDetail)
    fun renderMovies(movie: List<MovieDetail>)
}