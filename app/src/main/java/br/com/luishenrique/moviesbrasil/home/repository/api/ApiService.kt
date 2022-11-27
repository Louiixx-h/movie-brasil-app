package br.com.luishenrique.moviesbrasil.home.repository.api

import br.com.luishenrique.moviesbrasil.details.models.responses.MovieDetailsResponseVO
import br.com.luishenrique.moviesbrasil.home.models.responses.ResultMovieResponseVO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(MOVIE_POPULAR)
    suspend fun getMoviesPopular(
        @Query("api_key") key: String = KEY_USER,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = 1
    ): Response<ResultMovieResponseVO>

    @GET(MOVIE_DETAILS)
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") key: String = KEY_USER,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = 1
    ): Response<MovieDetailsResponseVO>

    companion object {
        const val MOVIE_POPULAR = "movie/popular"
        const val MOVIE_DETAILS = "movie/{movie_id}"

        const val KEY_USER = "86ef71989f8c4a9a7cc321d7a0f6a528"
        const val LANGUAGE = "en-US"
    }
}