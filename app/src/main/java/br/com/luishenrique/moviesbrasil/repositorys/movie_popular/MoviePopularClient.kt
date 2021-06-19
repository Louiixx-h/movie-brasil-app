package br.com.luishenrique.moviesbrasil.repositorys.movie_popular

import br.com.luishenrique.moviesbrasil.repositorys.BASE_URL
import br.com.luishenrique.moviesbrasil.repositorys.movie_latest.MovieLatestApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MoviePopularClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getInstanceMoviePopular(): MoviePopularApi? {
        return retrofit.create(MoviePopularApi::class.java)
    }
}