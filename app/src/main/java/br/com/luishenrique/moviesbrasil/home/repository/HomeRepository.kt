package br.com.luishenrique.moviesbrasil.home.repository

import br.com.luishenrique.moviesbrasil.home.models.responses.ResultMovieResponseVO
import retrofit2.Response

interface HomeRepository {
    suspend fun getMovies(): Response<ResultMovieResponseVO>
}