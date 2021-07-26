package br.com.luishenrique.moviesbrasil.data.repository.now_playing

import br.com.luishenrique.moviesbrasil.data.models.ResponseMovieNowPlaying
import br.com.luishenrique.moviesbrasil.data.repository.KEY_USER
import br.com.luishenrique.moviesbrasil.data.repository.LANGUAGE
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieNowPlayingApi {

    @GET("movie/now_playing")
    fun getNowPlaying(
        @Query("api_key") key: String = KEY_USER,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = 1
    ): Call<ResponseMovieNowPlaying>
}