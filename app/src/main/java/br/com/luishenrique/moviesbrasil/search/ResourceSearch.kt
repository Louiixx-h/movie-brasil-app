package br.com.luishenrique.moviesbrasil.search

sealed class ResourceSearch<T>(
    val data: T? = null,
    val message: String? = null,
    val value: Boolean? = null
) {
    class Success<T>(data: T) : ResourceSearch<T>(data)
    class Error<T>(message: String, data: T? = null) : ResourceSearch<T>(data, message)
    class Loading<T> : ResourceSearch<T>()
}