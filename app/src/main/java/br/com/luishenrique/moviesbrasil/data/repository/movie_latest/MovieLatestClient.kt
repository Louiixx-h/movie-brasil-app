package br.com.luishenrique.moviesbrasil.data.repository.movie_latest

import br.com.luishenrique.moviesbrasil.data.repository.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieLatestClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getInstanceMovieLatest(): MovieLatestApi? {
        return retrofit.create(MovieLatestApi::class.java)
    }
}