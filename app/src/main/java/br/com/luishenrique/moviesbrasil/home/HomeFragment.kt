package br.com.luishenrique.moviesbrasil.home

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import br.com.luishenrique.moviesbrasil.BuildConfig
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.common.BaseFragment
import br.com.luishenrique.moviesbrasil.databinding.FragmentHomeBinding
import br.com.luishenrique.moviesbrasil.details.DetailsActivity
import br.com.luishenrique.moviesbrasil.home.adapters.MovieAdapter
import br.com.luishenrique.moviesbrasil.home.models.Movie
import br.com.luishenrique.moviesbrasil.home.models.ResultMovie
import br.com.luishenrique.moviesbrasil.utils.onClickRightIcon
import br.com.luishenrique.moviesbrasil.utils.textChange
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HomeFragment : BaseFragment<FragmentHomeBinding>(),
    HomeFragmentContract,
    MovieAdapter.ListenerMovie {

    private val adapterMovie by inject<MovieAdapter> { parametersOf(this) }
    private val viewModel by inject<HomeFragmentViewModelImpl>()

    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)

    override fun setUpViews(view: View) {
        viewModel.getMovies()
        binding.contentHome.searchMovie.setSearchInput()
        setupListMovies()
        setupObserver()

        (requireActivity() as HomeActivity).run {
            setSupportActionBar(binding.toolbar)
            setupNavController()
        }
    }

    override fun setupObserver() {
        viewModel.command.observe(viewLifecycleOwner) { response ->
            when(response) {
                is ResourceHome.Success -> {
                    binding.contentHome.emptyView.isVisible = false
                    handleSuccess(response.data)
                    hideLoader()
                }
                is ResourceHome.SearchSuccess -> {
                    binding.contentHome.emptyView.isVisible = true
                    handleSearchSuccess(response.data)
                    hideLoader()
                }
                is ResourceHome.Error -> {
                    errorScreen()
                    hideLoader()
                }
                is ResourceHome.Loading -> {
                    showLoader()
                }
            }
        }
    }

    override fun handleSuccess(resultMovie: ResultMovie?) {
        setupMovies(resultMovie?.results.orEmpty())
    }

    override fun handleSearchSuccess(resultMovie: ResultMovie?) {
        setupMovies(resultMovie?.results.orEmpty())
        binding.ivThumbnailLatestMovie.isVisible = false
    }

    override fun showLoader() {
        binding.shimmerHomeContainer.shimmerDetailsContainer.startShimmer()
        binding.shimmerHomeContainer.shimmerDetailsContainer.isVisible = true
        binding.appBar.isVisible = false
        binding.listContainer.isVisible = false
    }

    override fun hideLoader() {
        binding.shimmerHomeContainer.shimmerDetailsContainer.stopShimmer()
        binding.shimmerHomeContainer.shimmerDetailsContainer.isVisible = false
        binding.appBar.isVisible = true
        binding.listContainer.isVisible = true
    }

    override fun setupMovies(movies: List<Movie>) {
        setupBanner(movies.slice(0 .. 5))
        adapterMovie.movies = movies
    }

    override fun setupListMovies() {
        binding.contentHome.rvMovies.adapter = adapterMovie
    }

    override fun setupBanner(movies: List<Movie>) {
        binding.ivThumbnailLatestMovie.isVisible = true
        binding.ivThumbnailLatestMovie.setList(movies.map { BuildConfig.BASE_URL_IMAGE + it.posterPath })
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
}