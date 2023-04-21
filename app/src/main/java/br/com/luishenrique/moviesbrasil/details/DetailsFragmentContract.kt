package br.com.luishenrique.moviesbrasil.details

import br.com.luishenrique.moviesbrasil.details.models.MovieDetail

interface DetailsFragmentContract {
    fun setupObserver()
    fun setupCallbacks()
    fun renderDetails(movieDetail: MovieDetail)
    fun changeVisibilityProgressBar(stateProgressBar: Boolean)
    fun clickOnFavorite()
    fun addedMovieToFavorites()
    fun removedMovieToFavorites()
}