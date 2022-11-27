package br.com.luishenrique.moviesbrasil.details.repository

import br.com.luishenrique.moviesbrasil.details.models.responses.MovieDetailsResponseVO
import retrofit2.Response

interface DetailsRepository {
    suspend fun getDetails(movieId: Int): Response<MovieDetailsResponseVO>
}
