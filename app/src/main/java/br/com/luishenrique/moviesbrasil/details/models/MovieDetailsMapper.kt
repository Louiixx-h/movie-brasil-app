package br.com.luishenrique.moviesbrasil.details.models

import br.com.luishenrique.moviesbrasil.details.models.responses.GenreResponseVO
import br.com.luishenrique.moviesbrasil.details.models.responses.MovieDetailsResponseVO

object MovieDetailsMapper {
    fun transform(movieDetails: MovieDetailsResponseVO?) = MovieDetail(
        backdropPath = movieDetails?.backdropPath.orEmpty(),
        genres = getGenres(movieDetails?.genres),
        homepage = movieDetails?.homepage.orEmpty(),
        id = movieDetails?.id ?: 0,
        originalTitle = movieDetails?.originalTitle.orEmpty(),
        overview = movieDetails?.overview.orEmpty(),
        popularity = movieDetails?.popularity ?: 0.0,
        releaseDate = movieDetails?.releaseDate.orEmpty(),
        voteAverage = movieDetails?.voteAverage ?: 0.0,
        voteCount = movieDetails?.voteCount ?: 0,
    )

    private fun getGenres(genres: List<GenreResponseVO>?) = genres?.map {
        Genre(id = it.id ?: 0, name = it.name.orEmpty())
    }.orEmpty()
}
