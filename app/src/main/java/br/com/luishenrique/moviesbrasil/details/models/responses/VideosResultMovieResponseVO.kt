package br.com.luishenrique.moviesbrasil.details.models.responses

import com.google.gson.annotations.SerializedName

data class VideosResultMovieResponseVO(
    @SerializedName("id") val id: Long?,
    @SerializedName("results") val results: List<VideoMovieResponseVO>?
)