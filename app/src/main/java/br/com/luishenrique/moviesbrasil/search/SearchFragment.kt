package br.com.luishenrique.moviesbrasil.search

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.common.BaseFragment
import br.com.luishenrique.moviesbrasil.databinding.FragmentSearchBinding
import br.com.luishenrique.moviesbrasil.details.DetailsActivity
import br.com.luishenrique.moviesbrasil.search.adapters.SearchMovieAdapter
import br.com.luishenrique.moviesbrasil.search.models.Movie
import br.com.luishenrique.moviesbrasil.search.models.ResultMovie
import br.com.luishenrique.moviesbrasil.utils.onClickRightIcon
import br.com.luishenrique.moviesbrasil.utils.textChange
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SearchFragment : BaseFragment<FragmentSearchBinding>(),
    SearchFragmentContract,
    SearchMovieAdapter.ListenerMovie {

    private val adapterMovie by inject<SearchMovieAdapter> { parametersOf(this) }
    private val viewModel by inject<SearchFragmentViewModelImpl>()

    override fun getViewBinding() = FragmentSearchBinding.inflate(layoutInflater)

    override fun setUpViews(view: View) {
        val movieName = arguments?.getString(MOVIE_NAME).orEmpty()
        viewModel.getMovies(movieName)
        binding.searchMovie.setSearchInput()
        setupListMovies()
        setupObserver()
    }

    override fun setupObserver() {
        viewModel.command.observe(viewLifecycleOwner) { response ->
            when(response) {
                is ResourceSearch.Success -> {
                    handleSuccess(response.data)
                    hideLoader()
                }
                is ResourceSearch.Error -> {
                    errorScreen()
                    hideLoader()
                }
                is ResourceSearch.Loading -> {
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
    }

    override fun showLoader() {
        binding.searchShimmerContainer.startShimmer()
        binding.searchShimmerContainer.isVisible = true
        binding.searchContentGroup.isVisible = false
    }

    override fun hideLoader() {
        binding.searchShimmerContainer.stopShimmer()
        binding.searchShimmerContainer.isVisible = false
        binding.searchContentGroup.isVisible = true
    }

    override fun setupMovies(movies: List<Movie>) {
        adapterMovie.movies = movies
    }

    override fun setupListMovies() {
        binding.rvMovies.adapter = adapterMovie
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
                viewModel.getMovies(text.replace(" ", "+"))
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
        const val MOVIE_NAME = "movie_name"

        fun newInstance(movieName: String) = SearchFragment().apply {
            arguments = Bundle().apply {
                putString(MOVIE_NAME, movieName)
            }
        }
    }
}