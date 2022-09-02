package br.com.luishenrique.moviesbrasil.data.models

data class Widget(
    val type: TypeDetailEnum,
    val title: String? = null,
    val image: String? = null,
    val color: String? = null,
    val description: String? = null,
)
