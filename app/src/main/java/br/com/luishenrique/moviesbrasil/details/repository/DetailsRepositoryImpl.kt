package br.com.luishenrique.moviesbrasil.details.repository

import br.com.luishenrique.moviesbrasil.details.models.responses.MovieDetailsResponseVO
import br.com.luishenrique.moviesbrasil.home.repository.api.MovieService
import retrofit2.Response

class DetailsRepositoryImpl : DetailsRepository {

    override suspend fun getDetails(movieId: Int): Response<MovieDetailsResponseVO> {
        return MovieService.getInstance().getMovieDetail(movieId)
    }
}
