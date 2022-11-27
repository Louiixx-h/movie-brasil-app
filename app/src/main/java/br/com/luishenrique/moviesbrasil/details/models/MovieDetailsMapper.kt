package br.com.luishenrique.moviesbrasil.details.models

import br.com.luishenrique.moviesbrasil.details.models.responses.GenreResponseVO
import br.com.luishenrique.moviesbrasil.details.models.responses.MovieDetailsResponseVO

object MovieDetailsMapper {
    fun transform(movieDetails: MovieDetailsResponseVO?) = MovieDetail(
        backdropPath = movieDetails?.backdropPath,
        genres = getGenres(movieDetails?.genres),
        homepage = movieDetails?.homepage,
        id = movieDetails?.id,
        originalTitle = movieDetails?.originalTitle,
        overview = movieDetails?.overview,
        popularity = movieDetails?.popularity,
        releaseDate = movieDetails?.releaseDate,
        voteAverage = movieDetails?.voteAverage,
        voteCount = movieDetails?.voteCount,
    )

    private fun getGenres(genres: List<GenreResponseVO>?) = genres?.map {
        Genre(id = it.id, name = it.name)
    }
}
