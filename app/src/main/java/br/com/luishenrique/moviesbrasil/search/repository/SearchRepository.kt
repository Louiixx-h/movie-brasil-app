package br.com.luishenrique.moviesbrasil.search.repository

import br.com.luishenrique.moviesbrasil.search.models.responses.ResultMovieResponseVO
import retrofit2.Response

interface SearchRepository {
    suspend fun getMovies(title: String): Response<ResultMovieResponseVO>
}