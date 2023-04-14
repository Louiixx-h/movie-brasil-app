package br.com.luishenrique.moviesbrasil.favorites.repository

import br.com.luishenrique.moviesbrasil.details.models.MovieDetail
import br.com.luishenrique.moviesbrasil.service.Storage

class FavoritesRepositoryImpl(
    private val storage: Storage
): FavoritesRepository {

    override fun getMovies(): List<MovieDetail> {
        return storage.moviesSaved
    }
}
