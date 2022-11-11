package br.com.luishenrique.moviesbrasil.data.models

import com.google.gson.annotations.SerializedName

data class Widget(
    @SerializedName("type") val type: TypeDetailEnum? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("image") val image: String? = null,
    @SerializedName("color") val color: String? = null,
    @SerializedName("description") val description: String? = null,
)
