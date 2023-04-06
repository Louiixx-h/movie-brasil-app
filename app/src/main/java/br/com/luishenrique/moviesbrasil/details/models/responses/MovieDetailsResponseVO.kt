package br.com.luishenrique.moviesbrasil.details.models.responses

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponseVO (
    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("genres")
    val genres: List<GenreResponseVO>?,

    @SerializedName("homepage")
    val homepage: String?,

    @SerializedName("id")
    val id: Long?,

    @SerializedName("title")
    val originalTitle: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("popularity")
    val popularity: Double?,

    @SerializedName("release_date")
    val releaseDate: String?,

    @SerializedName("vote_average")
    val voteAverage: Double?,

    @SerializedName("vote_count")
    val voteCount: Long?
)