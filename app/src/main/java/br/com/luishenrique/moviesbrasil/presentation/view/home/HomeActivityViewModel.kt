package br.com.luishenrique.moviesbrasil.presentation.view.home

import android.util.Log
import androidx.lifecycle.*
import br.com.luishenrique.moviesbrasil.data.models.*
import br.com.luishenrique.moviesbrasil.data.repository.*
import kotlinx.coroutines.*
import java.util.concurrent.ExecutionException

class HomeActivityViewModel: ViewModel() {

    private val repository: MainRepository = MainRepository()

    private val _progressBar = MutableLiveData<Boolean>()
    val progressBar: LiveData<Boolean>
        get() = _progressBar

    private val _movieTopRatedList = MutableLiveData<ResponseMovie>()
    val movieTopRatedList: LiveData<ResponseMovie>
        get() = _movieTopRatedList

    private val _moviePopularList = MutableLiveData<ResponseMovie>()
    val moviePopularList: LiveData<ResponseMovie>
        get() = _moviePopularList

    private val _moviesNowPlaying = MutableLiveData<ResponseMovie>()
    val moviesNowPlaying: LiveData<ResponseMovie>
        get() = _moviesNowPlaying

    fun getMoviesNowPlaying() {
        startLaunch(
            success = {
                val response = repository.getMoviesNowPlaying()
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        _moviesNowPlaying.value = response.body()
                    }
                }
            },
            error = {
                Log.e("movie", "getMoviesNowPlaying: ", it)
            }
        )
    }

    fun getMoviesTopRated() {
        startLaunch(
            success = {
                val response = repository.getMoviesTopRated()
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        _movieTopRatedList.value = response.body()
                    }
                }
            },
            error = {
                Log.e("movie", "getMoviesTopRated: ", it)
            }
        )
    }

    fun getMoviesPopular() {
        startLaunch(
            success = {
                val response = repository.getMoviesPopular()
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        _moviePopularList.value = response.body()
                    }
                }
            },
            error = {
                Log.e("movie", "getMoviesPopular: ", it)
            }
        )
    }

    private fun startLaunch(success: suspend () -> Unit, error: (ExecutionException) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _progressBar.postValue(true)
                success()
            } catch (ex: ExecutionException) {
                error(ex)
            } finally {
                _progressBar.postValue(false)
            }
        }
    }
}