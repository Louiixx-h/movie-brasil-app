package br.com.luishenrique.moviesbrasil.home.repository

import br.com.luishenrique.moviesbrasil.home.models.responses.ResultMovieResponseVO
import br.com.luishenrique.moviesbrasil.home.repository.api.MovieService
import retrofit2.Response

class HomeRepository {

    suspend fun getMoviesPopular(): Response<ResultMovieResponseVO> {
        return MovieService.getInstance().getMoviesPopular()
    }
}