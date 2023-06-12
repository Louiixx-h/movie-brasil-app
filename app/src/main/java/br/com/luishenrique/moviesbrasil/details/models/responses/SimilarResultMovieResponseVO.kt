package br.com.luishenrique.moviesbrasil.details.models.responses

import com.google.gson.annotations.SerializedName

data class SimilarResultMovieResponseVO(
    @SerializedName("results") val results: List<SimilarMovieResponseVO>?
)