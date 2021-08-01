package br.com.luishenrique.moviesbrasil.data.repository

import br.com.luishenrique.moviesbrasil.data.models.*
import br.com.luishenrique.moviesbrasil.data.repository.api.MovieService
import retrofit2.Response

class MainRepository {

    suspend fun getMoviesNowPlaying(): Response<ResponseMovie> {
        return MovieService.getInstance().getNowPlaying()
    }

    suspend fun getMoviesTopRated(): Response<ResponseMovie> {
        return MovieService.getInstance().getMoviesTopRated()
    }

    suspend fun getMoviesPopular(): Response<ResponseMovie> {
        return MovieService.getInstance().getMoviesPopular()
    }
}