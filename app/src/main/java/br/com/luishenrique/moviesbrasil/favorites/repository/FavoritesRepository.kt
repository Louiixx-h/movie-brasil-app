package br.com.luishenrique.moviesbrasil.favorites.repository

import br.com.luishenrique.moviesbrasil.home.models.responses.MovieResponseVO

interface FavoritesRepository {
    fun getMovies(): List<MovieResponseVO>

}
