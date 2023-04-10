package br.com.luishenrique.moviesbrasil.details.repository

import br.com.luishenrique.moviesbrasil.details.models.responses.MovieDetailsResponseVO
import br.com.luishenrique.moviesbrasil.service.api.ApiService
import retrofit2.Response

class DetailsRepositoryImpl(private val service: ApiService): DetailsRepository {

    override suspend fun getDetails(movieId: Int): Response<MovieDetailsResponseVO> {
        return service.getMovieDetail(movieId)
    }
}
