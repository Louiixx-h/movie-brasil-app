package br.com.luishenrique.moviesbrasil.home

sealed class ResourceHome<T>(
    val data: T? = null,
    val message: String? = null,
    val value: Boolean? = null
) {
    class Success<T>(data: T) : ResourceHome<T>(data)
    class Error<T>(message: String, data: T? = null) : ResourceHome<T>(data, message)
    class Loading<T> : ResourceHome<T>()
}