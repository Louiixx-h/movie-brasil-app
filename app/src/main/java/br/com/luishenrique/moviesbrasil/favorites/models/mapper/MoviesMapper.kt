package br.com.luishenrique.moviesbrasil.favorites.models.mapper

import br.com.luishenrique.moviesbrasil.home.models.Movie
import br.com.luishenrique.moviesbrasil.home.models.responses.MovieResponseVO

object MoviesMapper {
    fun transform(movies: List<MovieResponseVO>) = movies.map {
        Movie(
            id = it.id,
            title = it.title,
            voteAverage = it.voteAverage,
            posterPath = it.posterPath,
            backdropPath = it.backdropPath
        )
    }
}

