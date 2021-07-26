package br.com.luishenrique.moviesbrasil.data.repository.movie_latest

import br.com.luishenrique.moviesbrasil.data.models.Movie
import br.com.luishenrique.moviesbrasil.data.repository.KEY_USER
import br.com.luishenrique.moviesbrasil.data.repository.LANGUAGE
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieLatestApi {

    @GET("movie/latest")
    fun getMoviesPopular(
        @Query("api_key") key: String = KEY_USER,
        @Query("language") language: String = LANGUAGE,
    ): Call<Movie>
}