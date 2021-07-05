package br.com.luishenrique.moviesbrasil.data.repository.now_playing

import br.com.luishenrique.moviesbrasil.data.repository.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieNowPlayingClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getInstanceMovieNowPlaying(): MovieNowPlayingApi? {
        return retrofit.create(MovieNowPlayingApi::class.java)
    }
}