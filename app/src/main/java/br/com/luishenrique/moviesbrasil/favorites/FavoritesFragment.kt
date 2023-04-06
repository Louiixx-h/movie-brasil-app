package br.com.luishenrique.moviesbrasil.favorites

import android.view.View
import androidx.lifecycle.ViewModelProvider
import br.com.luishenrique.moviesbrasil.base.BaseFragment
import br.com.luishenrique.moviesbrasil.databinding.FragmentFavoritesBinding
import br.com.luishenrique.moviesbrasil.details.DetailsActivity
import br.com.luishenrique.moviesbrasil.favorites.adapters.AdapterFavoritesMovie
import br.com.luishenrique.moviesbrasil.home.models.Movie

class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>(),
    AdapterFavoritesMovie.ListenerMovie, FavoritesFragmentContract {

    private val adapterMovie: AdapterFavoritesMovie by lazy {
        AdapterFavoritesMovie(this)
    }
    private val viewModel: FavoritesFragmentViewModelImpl by lazy {
        ViewModelProvider(this)[FavoritesFragmentViewModelImpl::class.java]
    }

    override fun getViewBinding() = FragmentFavoritesBinding.inflate(layoutInflater)

    override fun setUpViews() {
        init()
        setComponents()
        setProgressBar()
    }

    override fun init() {
        viewModel.getMovies()
    }

    override fun setComponents() {
        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            renderMovies(movies)
        }
    }

    override fun setProgressBar() {
        viewModel.progressBar.observe(requireActivity()) { stateProgressBar ->
            changeVisibilityProgressBar(stateProgressBar)
        }
    }

    override fun renderMovies(movie: List<Movie>) {
        adapterMovie.movies = movie
        binding.rvFavorites.adapter = adapterMovie
    }

    override fun changeVisibilityProgressBar(stateProgressBar: Boolean) {
        if (stateProgressBar) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onClick(movie: Movie) {
        goToDetails(movie)
    }

    override fun goToDetails(movie: Movie) {
        startActivity(DetailsActivity.newInstance(requireActivity(), movie.id))
    }

    override fun removeMovie(movie: Movie) {

    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoritesFragment()
    }
}