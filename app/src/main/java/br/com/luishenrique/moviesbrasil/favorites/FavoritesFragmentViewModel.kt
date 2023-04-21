package br.com.luishenrique.moviesbrasil.favorites

import androidx.lifecycle.LiveData
import br.com.luishenrique.moviesbrasil.details.models.MovieDetail

interface FavoritesFragmentViewModel {
    val command: LiveData<ResourceFavorites<List<MovieDetail>>>

    fun onSuccessGetMovies(response: ResourceFavorites<List<MovieDetail>>)
    fun onErrorGetMovie(exception: Exception)
    fun onLoading(value: Boolean)
    fun getMovies()
}