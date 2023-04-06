package br.com.luishenrique.moviesbrasil.details

import androidx.lifecycle.LiveData
import br.com.luishenrique.moviesbrasil.details.models.MovieDetail

interface DetailsFragmentViewModel {
    fun getDetails(movieId: Int)
    val movieDetail: LiveData<MovieDetail>
    val progressBar: LiveData<Boolean>
}