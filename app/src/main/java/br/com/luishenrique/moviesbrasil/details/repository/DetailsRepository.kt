package br.com.luishenrique.moviesbrasil.details.repository

import br.com.luishenrique.moviesbrasil.details.models.MovieDetail
import br.com.luishenrique.moviesbrasil.details.models.responses.MovieDetailsResponseVO
import retrofit2.Response

interface DetailsRepository {
    suspend fun getDetails(movieId: Int): Response<MovieDetailsResponseVO>
    fun addMovieToFavorites(movie: MovieDetail): Boolean
    fun removeMovieToFavorites(movie: MovieDetail): Boolean
    fun hasMovie(movie: MovieDetail): Boolean
}
