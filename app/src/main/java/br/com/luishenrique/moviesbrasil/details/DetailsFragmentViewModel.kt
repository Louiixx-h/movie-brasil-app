package br.com.luishenrique.moviesbrasil.details

import androidx.lifecycle.LiveData
import br.com.luishenrique.moviesbrasil.details.models.MovieDetail

interface DetailsFragmentViewModel {
    val progressBar: LiveData<Boolean>
    val movieDetail: LiveData<MovieDetail>

    fun getDetails(movieId: Int)
    fun addMovieToFavorites()
    val isFavorite: LiveData<Boolean>
    fun clickOnFavorite()
    fun removeMovieToFavorites()
}