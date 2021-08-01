package br.com.luishenrique.moviesbrasil.presentation.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.data.models.ResponseMovie
import br.com.luishenrique.moviesbrasil.presentation.adapters.AdapterMovie
import br.com.luishenrique.moviesbrasil.utils.BASE_IMAGE
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.content_home_app.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), HomeActivityContract {

    private lateinit var viewModel: HomeActivityViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configViewModel()
        initView()
    }

    override fun initView() {
        setProgressBar()
        getMoviesNowPlaying()
        getMoviesPopular()
        getMoviesRecent()
    }

    override fun configViewModel() {
        viewModel = ViewModelProvider(this).get(HomeActivityViewModel::class.java)
    }

    override fun getMoviesNowPlaying() {
        viewModel.getMoviesNowPlaying()
        viewModel.moviesNowPlaying.observe(requireActivity()) { responseMovie ->
            rv_movies_now_playing.adapter = AdapterMovie(requireContext(), responseMovie.results)
            setBanner(responseMovie)
        }
    }

    private fun setBanner(movie: ResponseMovie) {
        tv_title_movie_latest.text = movie.results[0].title
        Glide.with(this)
            .load(BASE_IMAGE + movie.results[0].backdropPath)
            .into(iv_thumbnail_latest_movie)
    }

    override fun getMoviesPopular() {
        viewModel.getMoviesPopular()
        viewModel.moviePopularList.observe(requireActivity()) { responseMovie ->
            rv_movies_popular.adapter = AdapterMovie(requireContext(), responseMovie.results)
        }
    }

    override fun getMoviesRecent() {
        viewModel.getMoviesTopRated()
        viewModel.movieTopRatedList.observe(requireActivity()) { responseMovie ->
            rv_movies_top_rated.adapter = AdapterMovie(requireContext(), responseMovie.results)
        }
    }

    override fun setProgressBar() {
        viewModel.progressBar.observe(requireActivity()) { stateProgressBar ->
            changeVisibilityProgressBar(stateProgressBar)
        }
    }

    private fun changeVisibilityProgressBar(stateProgressBar: Boolean) {
        if (stateProgressBar) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}