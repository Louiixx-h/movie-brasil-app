package br.com.luishenrique.moviesbrasil.data.repository.movie_recent

import br.com.luishenrique.moviesbrasil.data.models.ResponseMovieTopRated
import br.com.luishenrique.moviesbrasil.data.repository.KEY_USER
import br.com.luishenrique.moviesbrasil.data.repository.LANGUAGE
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieRecentApi {

    @GET("movie/top_rated")
    fun getMoviesTopRated(
        @Query("api_key") key: String = KEY_USER,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = 1
    ): Call<ResponseMovieTopRated>
}