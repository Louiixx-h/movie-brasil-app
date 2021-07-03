package br.com.luishenrique.moviesbrasil.views.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.adapters.MovieNowPlayingAdapater
import br.com.luishenrique.moviesbrasil.adapters.MoviePopularHomeAdapater
import br.com.luishenrique.moviesbrasil.adapters.MovieTopRatedHomeAdapater
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_home_app.*

class HomeActivity : AppCompatActivity(), HomeActivityContract {

    private lateinit var viewModel: HomeActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    override fun initView() {
        configViewModel()
        getMoviesNowPlaying()
        getMoviesPopular()
        getMoviesRecent()
    }

    override fun configViewModel() {
        viewModel = ViewModelProvider(this).get(HomeActivityViewModel::class.java)
    }

    override fun getMoviesRecent() {
        viewModel.getMoviesNowPlaying()
        viewModel.moviesNowPlaying.observe(this) { movieNowPlaying ->
            rv_movies_now_playing.adapter = MovieNowPlayingAdapater(
                this, movieNowPlaying.results
            )
            tv_title_movie_latest.text = movieNowPlaying.results[0].title
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/original${
                    movieNowPlaying.results[0].backdrop_path
                }")
                .into(iv_thumbnail_latest_movie)
        }
    }

    override fun getMoviesPopular() {
        viewModel.getMoviesPopular()
        viewModel.moviePopularList.observe(this) { moviePopularList ->
            rv_movies_popular.adapter = MoviePopularHomeAdapater(
                this, moviePopularList.results
            )
        }
    }

    override fun getMoviesNowPlaying() {
        viewModel.getMoviesRecent()
        viewModel.movieTopRatedList.observe(this) { movieTopRatedist ->
            rv_movies_top_rated.adapter = MovieTopRatedHomeAdapater(
                this, movieTopRatedist.results
            )
        }
    }
}