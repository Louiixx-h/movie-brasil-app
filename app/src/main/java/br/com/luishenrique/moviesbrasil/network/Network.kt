package br.com.luishenrique.moviesbrasil.network

import retrofit2.Response

interface Network {

    suspend fun <T : Any> callResponse(
        block: suspend () -> Response<T>,
        onSuccess: (T) -> Unit,
        onError: (Exception) -> Unit,
        finally: () -> Unit
    )

    suspend fun <T : Any> call(
        block: suspend () -> T?,
        onSuccess: (T) -> Unit,
        onError: (Exception) -> Unit,
        finally: () -> Unit
    )
}