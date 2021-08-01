package br.com.luishenrique.moviesbrasil.data.repository.api

import br.com.luishenrique.moviesbrasil.data.models.*
import br.com.luishenrique.moviesbrasil.utils.KEY_USER
import br.com.luishenrique.moviesbrasil.utils.LANGUAGE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") key: String = KEY_USER,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = 1
    ): Response<ResponseMovie>

    @GET("movie/popular")
    suspend fun getMoviesPopular(
        @Query("api_key") key: String = KEY_USER,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = 1
    ): Response<ResponseMovie>

    @GET("movie/top_rated")
    suspend fun getMoviesTopRated(
        @Query("api_key") key: String = KEY_USER,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = 1
    ): Response<ResponseMovie>
}