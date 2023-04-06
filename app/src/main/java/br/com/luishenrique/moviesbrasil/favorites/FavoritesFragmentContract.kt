package br.com.luishenrique.moviesbrasil.favorites

import br.com.luishenrique.moviesbrasil.home.models.Movie

interface FavoritesFragmentContract {
    fun init()
    fun goToDetails(movie: Movie)
    fun changeVisibilityProgressBar(stateProgressBar: Boolean)
    fun renderMovies(movie: List<Movie>)
    fun setProgressBar()
    fun setComponents()
}