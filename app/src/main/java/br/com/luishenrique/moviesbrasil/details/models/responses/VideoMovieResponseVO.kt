package br.com.luishenrique.moviesbrasil.details.models.responses

data class VideoMovieResponseVO(
    val iso639_1: String?,
    val iso3166_1: String?,
    val name: String?,
    val key: String?,
    val publishedAt: String?,
    val site: String?,
    val size: Long?,
    val type: String?,
    val official: Boolean?,
    val id: String?
)
