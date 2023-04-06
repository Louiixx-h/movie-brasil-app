package br.com.luishenrique.moviesbrasil.home.repository

import br.com.luishenrique.moviesbrasil.home.models.responses.ResultMovieResponseVO
import br.com.luishenrique.moviesbrasil.service.api.MovieService
import retrofit2.Response

class HomeRepositoryImpl : HomeRepository {

    override suspend fun getMovies(): Response<ResultMovieResponseVO> {
        return MovieService.getInstance().getMoviesPopular()
    }

    override suspend fun searchMovie(title: String): Response<ResultMovieResponseVO> {
        return MovieService.getInstance().searchByTitle(query = title)
    }
}