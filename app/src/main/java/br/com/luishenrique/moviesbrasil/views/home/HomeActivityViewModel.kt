package br.com.luishenrique.moviesbrasil.views.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.luishenrique.moviesbrasil.models.*
import br.com.luishenrique.moviesbrasil.repositorys.*
import br.com.luishenrique.moviesbrasil.repositorys.movie_latest.MovieLatestClient
import br.com.luishenrique.moviesbrasil.repositorys.movie_popular.MoviePopularClient
import br.com.luishenrique.moviesbrasil.repositorys.movie_recent.MovieRecentClient
import br.com.luishenrique.moviesbrasil.repositorys.now_playing.MovieNowPlayingClient
import retrofit2.Call
import retrofit2.Response

class HomeActivityViewModel: ViewModel() {

    val movieTopRatedList = MutableLiveData<ResponseMovieTopRated>()
    val moviePopularList = MutableLiveData<ResponseMoviePopular>()
    val movieLatest = MutableLiveData<Movie>()
    val moviesNowPlaying = MutableLiveData<ResponseMovieNowPlaying>()

    fun getMoviesNowPlaying() {
        val call = MovieNowPlayingClient.getInstanceMovieNowPlaying()

        call?.getNowPlaying(KEY_USER)
            ?.enqueue(object: retrofit2.Callback<ResponseMovieNowPlaying> {
                override fun onResponse(
                    call: Call<ResponseMovieNowPlaying>,
                    response: Response<ResponseMovieNowPlaying>
                ) {
                    if (response.isSuccessful){
                        moviesNowPlaying.postValue(response.body())
                    }
                }
                override fun onFailure(call: Call<ResponseMovieNowPlaying>, t: Throwable) {
                    Log.i(TAG_MOVIE_NOW_PLAYING, "Error: ${t.message}")
                }
            })
    }

    fun getMoviesPopular() {
        val call = MoviePopularClient.getInstanceMoviePopular()

        call?.getMoviesPopular(KEY_USER, LANGUAGE, 1)
            ?.enqueue(object: retrofit2.Callback<ResponseMoviePopular> {
            override fun onResponse(
                call: Call<ResponseMoviePopular>,
                response: Response<ResponseMoviePopular>
            ) {
                if (response.isSuccessful){
                    moviePopularList.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<ResponseMoviePopular>, t: Throwable) {
                Log.i(TAG_MOVIE_POPULAR, "Error: ${t.message}")
            }
        })
    }

    fun getMoviesRecent() {
        val call = MovieRecentClient.getInstanceMovieRecent()

        call?.getMoviesTopRated(KEY_USER, LANGUAGE, 1)
            ?.enqueue(object: retrofit2.Callback<ResponseMovieTopRated>{
                override fun onResponse(
                    call: Call<ResponseMovieTopRated>,
                    response: Response<ResponseMovieTopRated>
                ) {
                    if (response.isSuccessful){
                        movieTopRatedList.postValue(response.body())
                    }
                }
                override fun onFailure(call: Call<ResponseMovieTopRated>, t: Throwable) {
                    Log.i(TAG_MOVIE_RECENT, "Error: ${t.message}")
                }
            })
    }

    fun getMovieLatest() {
        val call = MovieLatestClient.getInstanceMovieLatest()

        call?.getMoviesPopular(KEY_USER, LANGUAGE)
            ?.enqueue(object: retrofit2.Callback<Movie>{
                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    if (response.isSuccessful){
                        movieLatest.postValue(response.body())
                    }
                }
                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    Log.i(TAG_MOVIE_LATEST, "Error: ${t.message}")
                }
            })
    }
}