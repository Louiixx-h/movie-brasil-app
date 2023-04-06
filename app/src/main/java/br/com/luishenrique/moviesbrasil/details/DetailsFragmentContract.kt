package br.com.luishenrique.moviesbrasil.details

import br.com.luishenrique.moviesbrasil.details.models.MovieDetail

interface DetailsFragmentContract {
    fun init()
    fun setComponents()
    fun setProgressBar()
    fun renderDetails(movieDetail: MovieDetail)
    fun changeVisibilityProgressBar(stateProgressBar: Boolean)
}