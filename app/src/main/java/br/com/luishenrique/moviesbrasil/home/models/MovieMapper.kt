package br.com.luishenrique.moviesbrasil.home.models

import br.com.luishenrique.moviesbrasil.home.models.responses.GenreResponseVO
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
        backdropPath = movie.backdropPath,
        genres = getGenres(movie.genres),
    )

    private fun getGenres(genres: List<GenreResponseVO>?) = genres?.map {
        Genre(id = it.id, name = "")
    }
}