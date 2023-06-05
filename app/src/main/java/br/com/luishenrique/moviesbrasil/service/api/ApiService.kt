package br.com.luishenrique.moviesbrasil.service.api

import br.com.luishenrique.moviesbrasil.BuildConfig
import br.com.luishenrique.moviesbrasil.details.models.responses.MovieDetailsResponseVO
import br.com.luishenrique.moviesbrasil.home.models.responses.ResultMovieResponseVO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(MOVIE_POPULAR)
    suspend fun getMoviesPopular(
        @Query("api_key") key: String = BuildConfig.API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = 1
    ): Response<ResultMovieResponseVO>

    @GET(MOVIE_DETAILS)
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") key: String = BuildConfig.API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = 1
    ): Response<MovieDetailsResponseVO>

    @GET(MOVIE_SEARCH)
    suspend fun searchByTitle(
        @Query("api_key") key: String = BuildConfig.API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("query") query: String,
        @Query("include_adult") adult: Boolean = false,
    ): Response<ResultMovieResponseVO>

    @GET(MOVIE_GENRES)
    suspend fun getGenres(
        @Query("api_key") key: String = BuildConfig.API_KEY,
        @Query("language") language: String = LANGUAGE,
    ): Response<ResultMovieResponseVO>

    companion object {
        const val MOVIE_POPULAR = "movie/popular"
        const val MOVIE_DETAILS = "movie/{movie_id}"
        const val MOVIE_SEARCH = "search/movie"
        const val MOVIE_GENRES = "genre/movie/list"
        const val LANGUAGE = "pt-br"
    }
}