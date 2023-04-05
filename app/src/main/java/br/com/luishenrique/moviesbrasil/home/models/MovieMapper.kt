package br.com.luishenrique.moviesbrasil.home.models

import br.com.luishenrique.moviesbrasil.home.models.responses.MovieResponseVO
import br.com.luishenrique.moviesbrasil.home.models.responses.ResultMovieResponseVO

object MovieMapper {

    fun transform(resultMovie: ResultMovieResponseVO) = ResultMovie(
        results = resultMovie.results?.map { getMovie(it) }.orEmpty()
    )

    private fun getMovie(movie: MovieResponseVO) = Movie(
        id = movie.id ?: 0,
        title = movie.title.orEmpty(),
        voteAverage = movie.voteAverage ?: 0.0,
        posterPath = movie.posterPath.orEmpty(),
        backdropPath = movie.backdropPath.orEmpty(),
        genres = movie.genres.orEmpty(),
    )
}