package br.com.luishenrique.moviesbrasil.search.models.responses

import com.google.gson.annotations.SerializedName

data class ResultMovieResponseVO(
    @SerializedName("results") val results: List<MovieResponseVO>?
)