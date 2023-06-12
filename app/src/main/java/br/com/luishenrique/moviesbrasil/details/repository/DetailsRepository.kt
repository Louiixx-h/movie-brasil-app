package br.com.luishenrique.moviesbrasil.details.repository

import br.com.luishenrique.moviesbrasil.details.models.MovieDetail
import br.com.luishenrique.moviesbrasil.details.models.responses.MovieDetailsResponseVO
import br.com.luishenrique.moviesbrasil.details.models.responses.SimilarResultMovieResponseVO
import br.com.luishenrique.moviesbrasil.details.models.responses.VideosResultMovieResponseVO
import retrofit2.Response

interface DetailsRepository {
    suspend fun getDetails(movieId: Int): Response<MovieDetailsResponseVO>
    suspend fun getSimilarMovies(movieId: Int): Response<SimilarResultMovieResponseVO>
    suspend fun getMoviesVideos(movieId: Int): Response<VideosResultMovieResponseVO>
    fun addMovieToFavorites(movie: MovieDetail): Boolean
    fun removeMovieToFavorites(movie: MovieDetail): Boolean
    fun hasMovie(movie: MovieDetail): Boolean
}
