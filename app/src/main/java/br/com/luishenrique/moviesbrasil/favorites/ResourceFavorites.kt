package br.com.luishenrique.moviesbrasil.favorites

sealed class ResourceFavorites<T>(
    val data: T? = null,
    val message: String? = null,
    val value: Boolean? = null
) {
    class Success<T>(data: T) : ResourceFavorites<T>(data)
    class Error<T>(message: String, data: T? = null) : ResourceFavorites<T>(data, message)
    class Loading<T>(value: Boolean) : ResourceFavorites<T>(value = value)
}