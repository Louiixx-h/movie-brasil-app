package br.com.luishenrique.moviesbrasil.home.models.responses

import com.google.gson.annotations.SerializedName

data class ResultMovieResponseVO(
    @SerializedName("results") val results: List<MovieResponseVO>
)