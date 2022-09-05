package br.com.luishenrique.moviesbrasil.data.models

import com.google.gson.annotations.SerializedName

data class Detail(
    @SerializedName("widgets") val widgets: List<Widget>? = null
)