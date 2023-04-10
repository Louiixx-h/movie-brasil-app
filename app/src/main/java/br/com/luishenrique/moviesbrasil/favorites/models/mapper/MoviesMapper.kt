package br.com.luishenrique.moviesbrasil.favorites.models.mapper

import br.com.luishenrique.moviesbrasil.home.models.Movie
import br.com.luishenrique.moviesbrasil.home.models.responses.MovieResponseVO

object MoviesMapper {
    fun transform(movies: List<MovieResponseVO>) = movies.map {
        Movie(
            id = it.id ?: 0,
            title = it.title.orEmpty(),
            voteAverage = it.voteAverage ?: 0.0,
            posterPath = it.posterPath.orEmpty(),
            backdropPath = it.backdropPath.orEmpty(),
            genres = it.genres.orEmpty()
        )
    }
}

