package br.com.luishenrique.moviesbrasil.details.repository

import br.com.luishenrique.moviesbrasil.details.models.MovieDetail
import br.com.luishenrique.moviesbrasil.details.models.responses.MovieDetailsResponseVO
import br.com.luishenrique.moviesbrasil.service.Storage
import br.com.luishenrique.moviesbrasil.service.api.ApiService
import retrofit2.Response

class DetailsRepositoryImpl(
    private val service: ApiService,
    private val storage: Storage
): DetailsRepository {

    override suspend fun getDetails(movieId: Int): Response<MovieDetailsResponseVO> {
        return service.getMovieDetail(movieId)
    }

    override fun addMovieToFavorites(movie: MovieDetail): Boolean {
        return storage.addMovie(movie)
    }

    override fun removeMovieToFavorites(movie: MovieDetail): Boolean {
        return storage.removeMovie(movie)
    }

    override fun hasMovie(movie: MovieDetail): Boolean {
        return storage.hasMovie(movie)
    }
}
