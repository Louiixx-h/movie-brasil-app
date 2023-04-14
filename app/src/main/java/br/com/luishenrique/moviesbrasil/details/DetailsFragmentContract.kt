package br.com.luishenrique.moviesbrasil.details

import br.com.luishenrique.moviesbrasil.details.models.MovieDetail

interface DetailsFragmentContract {
    fun init()
    fun renderDetails(movieDetail: MovieDetail)
    fun changeVisibilityProgressBar(stateProgressBar: Boolean)
    fun clickOnFavorite()
    fun setObservers()
    fun setCallbacks()
    fun changeIconFavorite(isFavorited: Boolean)
}