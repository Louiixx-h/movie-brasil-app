package br.com.luishenrique.moviesbrasil.home

import android.graphics.drawable.Drawable
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.common.BaseFragment
import br.com.luishenrique.moviesbrasil.databinding.FragmentHomeBinding
import br.com.luishenrique.moviesbrasil.details.DetailsActivity
import br.com.luishenrique.moviesbrasil.home.adapters.MovieAdapter
import br.com.luishenrique.moviesbrasil.home.models.Movie
import br.com.luishenrique.moviesbrasil.home.models.ResultMovie
import br.com.luishenrique.moviesbrasil.utils.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeFragmentContract, MovieAdapter.ListenerMovie {

    private val adapterMovie: MovieAdapter by inject { parametersOf(this) }
    private val viewModel: HomeFragmentViewModelImpl by inject()

    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)

    override fun setUpViews() {
        viewModel.getMovies()
        binding.contentHome.searchMovie.setSearchInput()

        setupToolbar()
        setupListMovies()
        setupObserver()
    }

    override fun setupObserver() {
        viewModel.command.observe(viewLifecycleOwner) { response ->
            when(response) {
                is ResourceHome.Success -> {
                    handleSuccess(response.data)
                }
                is ResourceHome.SearchSuccess -> {
                    handleSearchSuccess(response.data)
                }
                is ResourceHome.Error -> {
                    errorScreen()
                }
                is ResourceHome.Loading -> {
                    handleLoading(response.value ?: false)
                }
            }
        }
    }

    override fun handleSuccess(resultMovie: ResultMovie?) {
        setupMovies(resultMovie?.results?.get(FIRST_MOVIE), resultMovie?.results.orEmpty())
    }

    override fun handleSearchSuccess(resultMovie: ResultMovie?) {
        setupMovies(resultMovie?.results?.get(FIRST_MOVIE), resultMovie?.results.orEmpty())
        binding.ivThumbnailLatestMovie.setImageDrawable(null)
        binding.ivThumbnailLatestMovie.isVisible = false
        binding.tvTitleMovieLatest.isVisible = false
    }

    override fun handleLoading(stateProgressBar: Boolean) {
        binding.progressBar.isVisible = stateProgressBar
    }

    override fun setupMovies(firstMovie: Movie?, movies: List<Movie>) {
        if(firstMovie !== null) {
            setupBanner(firstMovie)
        }

        adapterMovie.movies = movies
    }

    override fun setupToolbar() {
        with((requireActivity() as AppCompatActivity)) {
            val toolbar: Toolbar = findViewById(R.id.toolbar_main)
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            toolbar.title = getString(R.string.app_name)
        }
    }

    override fun setupListMovies() {
        binding.contentHome.rvMovies.adapter = adapterMovie
    }

    override fun setupBanner(movie: Movie) {
        binding.ivThumbnailLatestMovie.isVisible = true
        binding.tvTitleMovieLatest.isVisible = true

        binding.tvTitleMovieLatest.text = movie.title
        setImage(
            binding.ivThumbnailLatestMovie,
            requireContext(),
            BASE_IMAGE + movie.backdropPath
        )
    }

    override fun onClick(movie: Movie) {
        goToDetails(movie)
    }

    override fun goToDetails(movie: Movie) {
        startActivity(DetailsActivity.newInstance(requireActivity(), movie.id))
    }

    private fun EditText.setSearchInput() {
        onClickRightIcon {
            if (this.text.toString().isNotBlank()) {
                this.text.clear()
            }
        }
        textChange(
            onTextChanged = {
                if(it.isNullOrEmpty()) {
                    val iconId = R.drawable.ic_baseline_search_24
                    val icon = ContextCompat.getDrawable(requireContext(), iconId)
                    addRightIcon(icon)
                } else {
                    val iconId = R.drawable.ic_baseline_cancel_24
                    val icon = ContextCompat.getDrawable(requireContext(), iconId)
                    addRightIcon(icon)
                }
            },
            afterTextChanged = {
                val text = this@setSearchInput.text.toString()
                viewModel.searchMovie(text.replace(" ", "+"))
            }
        )
    }

    private fun EditText.addRightIcon(icon: Drawable?) {
        this.setCompoundDrawablesWithIntrinsicBounds(
            null,
            null,
            icon,
            null
        )
    }

    companion object {

        @JvmStatic
        private val FIRST_MOVIE = 0

        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}