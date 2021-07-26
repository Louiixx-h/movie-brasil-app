package br.com.luishenrique.moviesbrasil.data.repository.movie_popular

import br.com.luishenrique.moviesbrasil.data.models.ResponseMoviePopular
import br.com.luishenrique.moviesbrasil.data.repository.KEY_USER
import br.com.luishenrique.moviesbrasil.data.repository.LANGUAGE
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviePopularApi {

    @GET("movie/popular")
    fun getMoviesPopular(
        @Query("api_key") key: String = KEY_USER,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = 1
    ): Call<ResponseMoviePopular>
}