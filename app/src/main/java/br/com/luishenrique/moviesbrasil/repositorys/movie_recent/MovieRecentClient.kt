package br.com.luishenrique.moviesbrasil.repositorys.movie_recent

import br.com.luishenrique.moviesbrasil.repositorys.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieRecentClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getInstanceMovieRecent(): MovieRecentApi? {
        return retrofit.create(MovieRecentApi::class.java)
    }
}