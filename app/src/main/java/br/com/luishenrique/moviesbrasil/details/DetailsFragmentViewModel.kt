package br.com.luishenrique.moviesbrasil.details

import androidx.lifecycle.LiveData
import br.com.luishenrique.moviesbrasil.details.models.MovieDetail
import br.com.luishenrique.moviesbrasil.details.models.responses.MovieDetailsResponseVO

interface DetailsFragmentViewModel {
    val command: LiveData<ResourceDetails<MovieDetail>>

    fun getDetails(movieId: Int)
    fun onSuccessGetMovie(response: ResourceDetails<MovieDetailsResponseVO>)
    fun onErrorGetMovie(exception: Exception)
    fun onLoading(value: Boolean)
    fun clickOnFavorite()
    fun addMovieToFavorites()
    fun removeMovieToFavorites()
    fun movieIsSaved()
}