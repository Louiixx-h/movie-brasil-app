package br.com.luishenrique.moviesbrasil.details

sealed class ResourceDetails<T>(
    val data: T? = null,
    val message: String? = null,
    val value: Boolean? = null
) {
    class Success<T>(data: T) : ResourceDetails<T>(data)
    class Error<T>(message: String, data: T? = null) : ResourceDetails<T>(data, message)
    class Loading<T>(value: Boolean) : ResourceDetails<T>(value = value)
    class AddedToFavorites<T> : ResourceDetails<T>()
    class RemovedToFavorites<T> : ResourceDetails<T>()
}