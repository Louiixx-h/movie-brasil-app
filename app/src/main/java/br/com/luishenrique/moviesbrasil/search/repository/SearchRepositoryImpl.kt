package br.com.luishenrique.moviesbrasil.search.repository

import br.com.luishenrique.moviesbrasil.search.models.responses.ResultMovieResponseVO
import br.com.luishenrique.moviesbrasil.service.api.ApiService
import retrofit2.Response

class SearchRepositoryImpl(private val service: ApiService) : SearchRepository {

    override suspend fun getMovies(title: String): Response<ResultMovieResponseVO> {
        return service.searchByTitle(query = title)
    }
}