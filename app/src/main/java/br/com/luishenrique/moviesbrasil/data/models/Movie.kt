package br.com.luishenrique.moviesbrasil.data.models

import com.google.gson.annotations.SerializedName

data class ResponseMovie(
    @SerializedName("results") val results: List<Movie>
)

data class Movie(
    @SerializedName("title") val title: String,
    @SerializedName("vote_average") val voteAverage: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String)