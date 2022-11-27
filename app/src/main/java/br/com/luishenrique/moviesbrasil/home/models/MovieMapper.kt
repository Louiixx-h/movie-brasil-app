package br.com.luishenrique.moviesbrasil.home.models

import br.com.luishenrique.moviesbrasil.home.models.responses.MovieResponseVO
import br.com.luishenrique.moviesbrasil.home.models.responses.ResultMovieResponseVO

object MovieMapper {

    fun transform(resultMovie: ResultMovieResponseVO) : ResultMovie {
        return ResultMovie(
            results = resultMovie.results.map { getMovie(it) }
        )
    }

    private fun getMovie(movie: MovieResponseVO) = Movie(
        id = movie.id,
        title = movie.title,
        voteAverage = movie.voteAverage,
        posterPath = movie.posterPath,
        backdropPath = movie.backdropPath
    )
}