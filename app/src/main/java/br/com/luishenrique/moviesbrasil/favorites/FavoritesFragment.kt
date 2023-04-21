package br.com.luishenrique.moviesbrasil.favorites

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.common.BaseFragment
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
        setupObserver()
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

    override fun setupObserver() {
        viewModel.command.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ResourceFavorites.Error -> errorScreen()
                is ResourceFavorites.Loading -> setupLoading(response.value == true)
                is ResourceFavorites.Success -> renderMovies(response.data)
            }
        }
    }

    override fun renderMovies(movie: List<MovieDetail>?) {
        if (movie == null) {
            errorScreen()
            return
        }

        adapterMovie.movies = movie
        binding.rvFavorites.adapter = adapterMovie
    }

    override fun setupLoading(stateProgressBar: Boolean) {
        binding.progressBar.isVisible = stateProgressBar
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