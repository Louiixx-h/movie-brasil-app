package br.com.luishenrique.moviesbrasil.favorites

import android.view.View
import androidx.lifecycle.ViewModelProvider
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.base.BaseFragment
import br.com.luishenrique.moviesbrasil.databinding.FragmentFavoritesBinding
import br.com.luishenrique.moviesbrasil.details.DetailsActivity
import br.com.luishenrique.moviesbrasil.favorites.adapters.AdapterFavoritesMovie
import br.com.luishenrique.moviesbrasil.home.models.Movie
import br.com.luishenrique.moviesbrasil.utils.toast

class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>(), AdapterFavoritesMovie.ListenerMovie {

    private val adapterMovie: AdapterFavoritesMovie by lazy {
        AdapterFavoritesMovie(this)
    }
    private val viewModel: FavoritesViewModel by lazy {
        ViewModelProvider(this)[FavoritesViewModel::class.java]
    }

    override fun getViewBinding() = FragmentFavoritesBinding.inflate(layoutInflater)

    override fun setUpViews() {
        init()
        setComponents()
        setProgressBar()
    }

    private fun init() {
        viewModel.getMovies()
    }

    private fun setComponents() {
        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            renderMovies(movies)
        }
    }

    private fun setProgressBar() {
        viewModel.progressBar.observe(requireActivity()) { stateProgressBar ->
            changeVisibilityProgressBar(stateProgressBar)
        }
    }

    private fun renderMovies(movie: List<Movie>) {
        adapterMovie.movies = movie
        binding.rvFavorites.adapter = adapterMovie
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

    private fun goToDetails(movie: Movie) {
        startActivity(DetailsActivity.newInstance(requireActivity(), movie.id!!))
    }

    override fun removeMovie(movie: Movie) {

    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoritesFragment()
    }
}