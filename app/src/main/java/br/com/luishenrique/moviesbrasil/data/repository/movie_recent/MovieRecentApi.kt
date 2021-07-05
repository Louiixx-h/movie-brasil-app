package br.com.luishenrique.moviesbrasil.data.repository.movie_recent

import br.com.luishenrique.moviesbrasil.data.models.ResponseMovieTopRated
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieRecentApi {

    @GET("movie/top_rated")
    fun getMoviesTopRated(
        @Query("api_key") key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<ResponseMovieTopRated>
}