package br.com.luishenrique.moviesbrasil.home.models.responses

import com.google.gson.annotations.SerializedName

data class MovieResponseVO(
    @SerializedName("id") val id: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("vote_average") val voteAverage: Double?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("genre_ids") val genres: List<Int>?,
)