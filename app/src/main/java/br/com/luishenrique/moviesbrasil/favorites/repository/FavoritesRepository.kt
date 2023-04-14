package br.com.luishenrique.moviesbrasil.favorites.repository

import br.com.luishenrique.moviesbrasil.details.models.MovieDetail

interface FavoritesRepository {
    fun getMovies(): List<MovieDetail>

}
