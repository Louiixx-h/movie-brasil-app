package br.com.luishenrique.moviesbrasil.details.models.responses

import com.google.gson.annotations.SerializedName

data class GenreResponseVO (
    @SerializedName("id")
    val id: Long?,

    @SerializedName("name")
    val name: String?
)