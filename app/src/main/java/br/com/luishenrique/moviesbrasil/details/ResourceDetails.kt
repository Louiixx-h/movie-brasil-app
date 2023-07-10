package br.com.luishenrique.moviesbrasil.details

sealed class ResourceDetails<T>(
    val data: T? = null,
    val message: String? = null,
    val value: Boolean? = null
) {
    class Success<T>(data: T) : ResourceDetails<T>(data)
    class Error<T>(message: String, data: T? = null) : ResourceDetails<T>(data, message)
    class Loading<T> : ResourceDetails<T>()
    class AddedToFavorites<T> : ResourceDetails<T>()
    class RemovedToFavorites<T> : ResourceDetails<T>()
}

sealed class ResourceSimilarMovie<T>(
    val data: T? = null,
    val message: String? = null,
    val value: Boolean? = null
) {
    class Success<T>(data: T) : ResourceSimilarMovie<T>(data)
}

sealed class ResourceMoviesVideos<T>(
    val data: T? = null,
    val message: String? = null,
    val value: Boolean? = null
) {
    class Success<T>(data: T) : ResourceMoviesVideos<T>(data)
}