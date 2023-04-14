package br.com.luishenrique.moviesbrasil.favorites

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.base.BaseFragment
import br.com.luishenrique.moviesbrasil.databinding.FragmentFavoritesBinding
import br.com.luishenrique.moviesbrasil.details.DetailsActivity
import br.com.luishenrique.moviesbrasil.details.models.MovieDetail
import br.com.luishenrique.moviesbrasil.favorites.adapters.AdapterFavoritesMovie
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>(),
    AdapterFavoritesMovie.ListenerMovie, FavoritesFragmentContract {

    private val adapterMovie: AdapterFavoritesMovie by inject { parametersOf(this) }
    private val viewModel: FavoritesFragmentViewModelImpl by inject()

    override fun getViewBinding() = FragmentFavoritesBinding.inflate(layoutInflater)

    override fun setUpViews() {
        setupToolbar()
        setComponents()
        setProgressBar()
    }

    private fun setupToolbar() {
        with((requireActivity() as AppCompatActivity)) {
            val toolbar: Toolbar  = findViewById(R.id.toolbar_main)
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            toolbar.title = getString(R.string.favorites)
        }
    }

    override fun onResume() {
        super.onResume()
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

    override fun renderMovies(movie: List<MovieDetail>) {
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

    override fun onClick(movie: MovieDetail) {
        goToDetails(movie)
    }

    override fun goToDetails(movie: MovieDetail) {
        startActivity(DetailsActivity.newInstance(requireActivity(), movie.id.toInt()))
    }

    override fun removeMovie(movie: MovieDetail) {

    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoritesFragment()
    }
}