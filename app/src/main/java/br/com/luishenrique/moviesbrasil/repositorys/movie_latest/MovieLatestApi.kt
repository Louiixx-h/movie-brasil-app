package br.com.luishenrique.moviesbrasil.repositorys.movie_latest

import br.com.luishenrique.moviesbrasil.models.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieLatestApi {

    @GET("movie/latest")
    fun getMoviesPopular(
        @Query("api_key") key: String,
        @Query("language") language: String,
    ): Call<Movie>
}