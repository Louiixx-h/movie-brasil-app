package br.com.luishenrique.moviesbrasil.views.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.adapters.MoviePopularHomeAdapater
import br.com.luishenrique.moviesbrasil.adapters.MovieTopRatedHomeAdapater
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_home_app.*

class HomeActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(HomeActivityViewModel::class.java)

        viewModel.getMoviesPopular()
        viewModel.moviePopularList.observe(this) { moviePopularList ->
            tv_title_movie_latest.text = moviePopularList.results[0].title
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/original${
                    moviePopularList.results[0].backdrop_path
                }")
                .into(iv_thumbnail_latest_movie)
        }
        viewModel.moviePopularList.observe(this) { moviePopularList ->
            rv_movies_popular.adapter = MoviePopularHomeAdapater(this, moviePopularList.results)
        }
        rv_movies_popular.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.HORIZONTAL, false)

        viewModel.getMoviesRecent()
        viewModel.movieTopRatedList.observe(this) { movieTopRatedist ->
            rv_movies_top_rated.adapter = MovieTopRatedHomeAdapater(this, movieTopRatedist.results)
        }
        rv_movies_top_rated.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.HORIZONTAL, false)

    }
}