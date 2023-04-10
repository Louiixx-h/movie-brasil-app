package br.com.luishenrique.moviesbrasil.home

import androidx.lifecycle.LiveData
import br.com.luishenrique.moviesbrasil.home.models.ResultMovie

interface HomeFragmentViewModel {
    val progressBar: LiveData<Boolean>
    val movieFromSearch: LiveData<ResultMovie>
    val moviePopularList: LiveData<ResultMovie>

    fun getMovies()
    fun searchMovie(title: String)
}