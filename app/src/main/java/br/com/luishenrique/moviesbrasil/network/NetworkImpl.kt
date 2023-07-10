package br.com.luishenrique.moviesbrasil.network

import kotlinx.coroutines.*
import retrofit2.Response

class NetworkImpl: Network {

    override suspend fun <T : Any> callResponse(
        block: suspend () -> Response<T>,
        onSuccess: (T) -> Unit,
        onError: (Exception) -> Unit,
        finally: (() -> Unit)?
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = block.invoke()
                if (response.isSuccessful && response.body() != null) {
                    val data = response.body()!!
                    withContext(Dispatchers.Main) { onSuccess(data) }
                } else {
                    withContext(Dispatchers.Main) { onError(Exception("Network call unsuccessful")) }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError(e) }
            } finally {
                withContext(Dispatchers.Main) { finally?.invoke() }
            }
        }
    }

    override suspend fun <T : Any> call(
        block: suspend () -> T?,
        onSuccess: (T) -> Unit,
        onError: (Exception) -> Unit,
        finally: (() -> Unit)?
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = block.invoke()
                if (response != null) {
                    withContext(Dispatchers.Main) { onSuccess(response) }
                } else {
                    withContext(Dispatchers.Main) { onError(Exception("Network call unsuccessful")) }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError(e) }
            } finally {
                withContext(Dispatchers.Main) { finally?.invoke() }
            }
        }
    }
}