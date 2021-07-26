package br.com.luishenrique.moviesbrasil.view.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.luishenrique.moviesbrasil.data.repository.*
import br.com.luishenrique.moviesbrasil.data.models.Movie
import br.com.luishenrique.moviesbrasil.data.models.ResponseMovieNowPlaying
import br.com.luishenrique.moviesbrasil.data.models.ResponseMoviePopular
import br.com.luishenrique.moviesbrasil.data.models.ResponseMovieTopRated
import br.com.luishenrique.moviesbrasil.data.repository.movie_latest.MovieLatestClient
import br.com.luishenrique.moviesbrasil.data.repository.movie_popular.MoviePopularClient
import br.com.luishenrique.moviesbrasil.data.repository.movie_recent.MovieRecentClient
import br.com.luishenrique.moviesbrasil.data.repository.now_playing.MovieNowPlayingClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.ExecutionException

class HomeActivityViewModel: ViewModel() {

    private val _progressBar = MutableLiveData<Boolean>()
    val progressBar: LiveData<Boolean>
        get() = _progressBar

    private val _movieTopRatedList = MutableLiveData<ResponseMovieTopRated>()
    val movieTopRatedList: LiveData<ResponseMovieTopRated>
        get() = _movieTopRatedList

    private val _moviePopularList = MutableLiveData<ResponseMoviePopular>()
    val moviePopularList: LiveData<ResponseMoviePopular>
        get() = _moviePopularList

    private val _movieLatest = MutableLiveData<Movie>()
    val movieLatest: LiveData<Movie>
        get() = _movieLatest

    private val _moviesNowPlaying = MutableLiveData<ResponseMovieNowPlaying>()
    val moviesNowPlaying: LiveData<ResponseMovieNowPlaying>
        get() = _moviesNowPlaying

    fun getMoviesNowPlaying() {
        _progressBar.postValue(true)
        val call = MovieNowPlayingClient.getInstanceMovieNowPlaying()
        call?.getNowPlaying()
            ?.enqueue(object: retrofit2.Callback<ResponseMovieNowPlaying> {
                override fun onResponse(call: Call<ResponseMovieNowPlaying>,
                                        response: Response<ResponseMovieNowPlaying>) {
                    if (response.isSuccessful) {
                        _moviesNowPlaying.postValue(response.body())
                    }
                    _progressBar.postValue(false)
                }
                override fun onFailure(call: Call<ResponseMovieNowPlaying>, t: Throwable) {
                    _progressBar.postValue(false)
                    Log.i(TAG_MOVIE_NOW_PLAYING, "Error getMoviesNowPlaying: ${t.message}")
                }
            })
    }

    fun getMoviesPopular() {
        _progressBar.postValue(true)
        val call = MoviePopularClient.getInstanceMoviePopular()
        call?.getMoviesPopular()
            ?.enqueue(object: retrofit2.Callback<ResponseMoviePopular> {
            override fun onResponse(call: Call<ResponseMoviePopular>,
                                    response: Response<ResponseMoviePopular>) {
                if (response.isSuccessful) {
                    _moviePopularList.postValue(response.body())
                }
                _progressBar.postValue(false)
            }
            override fun onFailure(call: Call<ResponseMoviePopular>, t: Throwable) {
                _progressBar.postValue(false)
                Log.i(TAG_MOVIE_POPULAR, "Error getMoviesPopular: ${t.message}")
            }
        })
    }

    fun getMoviesRecent() {
        _progressBar.postValue(true)
        val call = MovieRecentClient.getInstanceMovieRecent()
        call?.getMoviesTopRated()
            ?.enqueue(object: retrofit2.Callback<ResponseMovieTopRated>{
                override fun onResponse(call: Call<ResponseMovieTopRated>,
                                        response: Response<ResponseMovieTopRated>) {
                    if (response.isSuccessful) {
                        _movieTopRatedList.postValue(response.body())
                    }
                    _progressBar.postValue(false)
                }
                override fun onFailure(call: Call<ResponseMovieTopRated>, t: Throwable) {
                    _progressBar.postValue(false)
                    Log.i(TAG_MOVIE_RECENT, "Error getMoviesRecent: ${t.message}")
                }
            })
    }

    fun getMovieLatest() {
        val call = MovieLatestClient.getInstanceMovieLatest()
        call?.getMoviesPopular()
            ?.enqueue(object: retrofit2.Callback<Movie>{
                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    if (response.isSuccessful) {
                        _movieLatest.postValue(response.body())
                    }
                }
                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    Log.i(TAG_MOVIE_LATEST, "Error getMovieLatest: ${t.message}")
                }
            })
    }

    fun startLaunch(block: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                _progressBar.postValue(true)
                delay(1000)
                block()
            } catch (ex: ExecutionException) {
                Log.e("error-viewmodel", "startLaunch: ${ex.message}")
            } finally {
                _progressBar
            }
        }
    }
}