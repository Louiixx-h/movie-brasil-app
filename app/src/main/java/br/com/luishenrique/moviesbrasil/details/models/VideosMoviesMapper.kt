package br.com.luishenrique.moviesbrasil.details.models

import br.com.luishenrique.moviesbrasil.details.models.responses.VideoMovieResponseVO
import br.com.luishenrique.moviesbrasil.details.models.responses.VideosResultMovieResponseVO

object VideosMoviesMapper {
    fun transform(videos: VideosResultMovieResponseVO?) = VideosResultMovie(
        id = videos?.id ?: 0,
        results = getResult(videos?.results)
    )

    private fun getResult(results: List<VideoMovieResponseVO>?) = results?.map {
        VideoMovie(
            iso639_1 = it.iso639_1.orEmpty(),
            iso3166_1 = it.iso3166_1.orEmpty(),
            name = it.name.orEmpty(),
            key = it.key.orEmpty(),
            publishedAt = it.publishedAt.orEmpty(),
            site = it.site.orEmpty(),
            size = it.size ?: 0,
            type = it.type.orEmpty(),
            official = it.official ?: false,
            id = it.id.orEmpty()
        )
    }.orEmpty()
}
