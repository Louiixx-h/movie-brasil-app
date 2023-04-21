package br.com.luishenrique.moviesbrasil.favorites

import br.com.luishenrique.moviesbrasil.details.models.MovieDetail

interface FavoritesFragmentContract {
    fun removeMovie(movie: MovieDetail)
    fun goToDetails(movie: MovieDetail)
    fun onClick(movie: MovieDetail)
    fun setupObserver()
    fun renderMovies(movie: List<MovieDetail>?)
    fun setupLoading(stateProgressBar: Boolean)
}