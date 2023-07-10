package br.com.luishenrique.moviesbrasil.search

import androidx.lifecycle.LiveData
import br.com.luishenrique.moviesbrasil.search.models.ResultMovie
import br.com.luishenrique.moviesbrasil.search.models.responses.ResultMovieResponseVO

interface SearchFragmentViewModel {
    val command: LiveData<ResourceSearch<ResultMovie>>

    fun getMovies(movieName: String)
    fun onSuccessGetMovies(res: ResourceSearch.Success<ResultMovieResponseVO>)
    fun onErrorGetMovies(exception: Exception)
    fun onLoading()
}