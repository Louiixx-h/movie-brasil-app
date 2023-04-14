package br.com.luishenrique.moviesbrasil.home.repository

import br.com.luishenrique.moviesbrasil.home.models.responses.ResultMovieResponseVO
import br.com.luishenrique.moviesbrasil.service.api.ApiService
import retrofit2.Response

class HomeRepositoryImpl(private val service: ApiService) : HomeRepository {

    override suspend fun getMovies(): Response<ResultMovieResponseVO> {
        return service.getMoviesPopular()
    }

    override suspend fun searchMovie(title: String): Response<ResultMovieResponseVO> {
        return service.searchByTitle(query = title)
    }
}