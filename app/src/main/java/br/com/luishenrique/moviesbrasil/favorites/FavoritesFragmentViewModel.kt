package br.com.luishenrique.moviesbrasil.favorites

import androidx.lifecycle.LiveData
import br.com.luishenrique.moviesbrasil.home.models.Movie

interface FavoritesFragmentViewModel {
    fun getMovies()
    val movies: LiveData<List<Movie>>
    val progressBar: LiveData<Boolean>
}