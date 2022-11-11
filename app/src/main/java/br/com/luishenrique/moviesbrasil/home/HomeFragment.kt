package br.com.luishenrique.moviesbrasil.home

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.databinding.FragmentHomeBinding
import br.com.luishenrique.moviesbrasil.home.adapters.AdapterMovie
import br.com.luishenrique.moviesbrasil.home.models.Movie
import br.com.luishenrique.moviesbrasil.utils.BASE_IMAGE
import br.com.luishenrique.moviesbrasil.utils.setImage

class HomeFragment : Fragment(R.layout.fragment_home), HomeFragmentContract, AdapterMovie.ListenerMovie {

    private lateinit var fragmentHomeBinding: FragmentHomeBinding
    private lateinit var adapterMovie: AdapterMovie
    private lateinit var viewModel: HomeFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
        fragmentHomeBinding.contentHome.rvMovies.adapter = adapterMovie
    }

    override fun configViewModel() {
        viewModel = ViewModelProvider(this)[HomeFragmentViewModel::class.java]
    }

    override fun setBanner(movie: Movie) {
        fragmentHomeBinding.tvTitleMovieLatest.text = movie.title

        setImage(
            fragmentHomeBinding.ivThumbnailLatestMovie,
            requireContext(),
            BASE_IMAGE + movie.backdropPath
        )
    }

    override fun setMovies() {
        viewModel.moviePopularList.observe(requireActivity()) { responseMovie ->
            setBanner(responseMovie.results[0])
            (fragmentHomeBinding.contentHome.rvMovies.adapter as AdapterMovie).movies = responseMovie.results
        }
    }

    override fun setProgressBar() {
        viewModel.progressBar.observe(requireActivity()) { stateProgressBar ->
            changeVisibilityProgressBar(stateProgressBar)
        }
    }

    private fun changeVisibilityProgressBar(stateProgressBar: Boolean) {
        if (stateProgressBar) {
            fragmentHomeBinding.progressBar.visibility = View.VISIBLE
        } else {
            fragmentHomeBinding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onClick(movie: Movie) {

    }
}