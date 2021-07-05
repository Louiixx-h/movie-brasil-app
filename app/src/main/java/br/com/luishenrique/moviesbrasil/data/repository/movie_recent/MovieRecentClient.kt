package br.com.luishenrique.moviesbrasil.data.repository.movie_recent

import br.com.luishenrique.moviesbrasil.data.repository.BASE_URL
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