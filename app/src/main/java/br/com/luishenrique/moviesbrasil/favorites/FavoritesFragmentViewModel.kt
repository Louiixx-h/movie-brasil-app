package br.com.luishenrique.moviesbrasil.favorites

import androidx.lifecycle.LiveData
import br.com.luishenrique.moviesbrasil.details.models.MovieDetail

interface FavoritesFragmentViewModel {
    fun getMovies()
    val movies: LiveData<List<MovieDetail>>
    val progressBar: LiveData<Boolean>
}