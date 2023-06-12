package br.com.luishenrique.moviesbrasil.details.models

import br.com.luishenrique.moviesbrasil.details.models.responses.SimilarMovieResponseVO
import br.com.luishenrique.moviesbrasil.details.models.responses.SimilarResultMovieResponseVO

object SimilarMovieMapper {

    fun transform(movieSimilar: SimilarResultMovieResponseVO?) = SimilarResultMovie(
        results = getResults(movieSimilar?.results)
    )

    private fun getResults(movies: List<SimilarMovieResponseVO>?) = movies?.map {
        SimilarMovie(
            id = it.id ?: 0,
            title = it.title.orEmpty(),
            voteAverage = it.voteAverage ?: 0.0,
            posterPath = it.posterPath.orEmpty(),
        )
    }.orEmpty()
}
