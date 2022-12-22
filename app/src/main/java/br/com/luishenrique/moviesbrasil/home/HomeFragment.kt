package br.com.luishenrique.moviesbrasil.home

import android.view.View
import androidx.lifecycle.ViewModelProvider
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.base.BaseFragment
import br.com.luishenrique.moviesbrasil.databinding.FragmentHomeBinding
import br.com.luishenrique.moviesbrasil.details.DetailsActivity
import br.com.luishenrique.moviesbrasil.home.adapters.MovieAdapter
import br.com.luishenrique.moviesbrasil.home.models.Movie
import br.com.luishenrique.moviesbrasil.utils.BASE_IMAGE
import br.com.luishenrique.moviesbrasil.utils.setImage
import br.com.luishenrique.moviesbrasil.utils.toast

class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeFragmentContract, MovieAdapter.ListenerMovie {

    private val adapterMovie: MovieAdapter by lazy {
        MovieAdapter(this)
    }
    private val viewModel: HomeFragmentViewModel by lazy {
        ViewModelProvider(this)[HomeFragmentViewModel::class.java]
    }

    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)

    override fun setUpViews() {
        initView()
    }

    override fun initView() {
        viewModel.getMoviesPopular()

        setListMovies()
        setProgressBar()
        setMovies()
    }

    override fun setListMovies() {
        binding.contentHome.rvMovies.adapter = adapterMovie
    }

    override fun setBanner(movie: Movie) {
        binding.tvTitleMovieLatest.text = movie.title

        setImage(
            binding.ivThumbnailLatestMovie,
            requireContext(),
            BASE_IMAGE + movie.backdropPath
        )
    }

    override fun setMovies() {
        viewModel.moviePopularList.observe(requireActivity()) { responseMovie ->
            setBanner(responseMovie.results[0])
            (binding.contentHome.rvMovies.adapter as MovieAdapter).movies = responseMovie.results
        }
    }

    override fun setProgressBar() {
        viewModel.progressBar.observe(requireActivity()) { stateProgressBar ->
            changeVisibilityProgressBar(stateProgressBar)
        }
    }

    private fun changeVisibilityProgressBar(stateProgressBar: Boolean) {
        if (stateProgressBar) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onClick(movie: Movie) {
        if (movie.id == null) {
            toast(getString(R.string.error_on_loading_screen_details))
            return
        }
        goToDetails(movie)
    }

    override fun goToDetails(movie: Movie) {
        startActivity(DetailsActivity.newInstance(requireActivity(), movie.id!!))
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}