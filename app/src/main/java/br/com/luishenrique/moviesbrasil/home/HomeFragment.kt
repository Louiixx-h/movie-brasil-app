package br.com.luishenrique.moviesbrasil.home

import android.view.View
import androidx.lifecycle.ViewModelProvider
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.base.BaseFragment
import br.com.luishenrique.moviesbrasil.databinding.FragmentHomeBinding
import br.com.luishenrique.moviesbrasil.details.DetailsActivity
import br.com.luishenrique.moviesbrasil.details.DetailsFragment
import br.com.luishenrique.moviesbrasil.home.adapters.AdapterMovie
import br.com.luishenrique.moviesbrasil.home.models.Movie
import br.com.luishenrique.moviesbrasil.utils.BASE_IMAGE
import br.com.luishenrique.moviesbrasil.utils.setImage

class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeFragmentContract, AdapterMovie.ListenerMovie {

    private lateinit var adapterMovie: AdapterMovie
    private lateinit var viewModel: HomeFragmentViewModel

    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)

    override fun setUpViews() {
        configViewModel()
        initView()
    }

    override fun initView() {
        adapterMovie = AdapterMovie(this)
        viewModel.getMoviesPopular()

        setListMovies()
        setProgressBar()
        setMovies()
    }

    override fun setListMovies() {
        binding.contentHome.rvMovies.adapter = adapterMovie
    }

    override fun configViewModel() {
        viewModel = ViewModelProvider(this)[HomeFragmentViewModel::class.java]
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
            (binding.contentHome.rvMovies.adapter as AdapterMovie).movies = responseMovie.results
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
        requireContext().startActivity(DetailsActivity.newInstance(requireContext()))
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}