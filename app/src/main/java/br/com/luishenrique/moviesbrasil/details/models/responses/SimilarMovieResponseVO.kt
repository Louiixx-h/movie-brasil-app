package br.com.luishenrique.moviesbrasil.details.models.responses

import com.google.gson.annotations.SerializedName

data class SimilarMovieResponseVO(
    @SerializedName("id") val id: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("vote_average") val voteAverage: Double?,
    @SerializedName("poster_path") val posterPath: String?
)