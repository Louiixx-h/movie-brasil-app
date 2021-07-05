package br.com.luishenrique.moviesbrasil.data.repository.movie_popular

import br.com.luishenrique.moviesbrasil.data.repository.BASE_URL
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