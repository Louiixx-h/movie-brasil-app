package br.com.luishenrique.moviesbrasil.home

import androidx.lifecycle.LiveData
import br.com.luishenrique.moviesbrasil.home.models.ResultMovie
import br.com.luishenrique.moviesbrasil.home.models.responses.ResultMovieResponseVO

interface HomeFragmentViewModel {
    val command: LiveData<ResourceHome<ResultMovie>>

    fun getMovies()
    fun searchMovie(title: String)
    fun onSuccessGetMovies(res: ResourceHome<ResultMovieResponseVO>)
    fun onErrorGetMovies(exception: Exception)
    fun onLoading()
}