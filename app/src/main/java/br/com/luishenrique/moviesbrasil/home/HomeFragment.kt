package br.com.luishenrique.moviesbrasil.home

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.base.BaseFragment
import br.com.luishenrique.moviesbrasil.databinding.FragmentHomeBinding
import br.com.luishenrique.moviesbrasil.details.DetailsActivity
import br.com.luishenrique.moviesbrasil.home.adapters.GenreAdapter
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
    private val genreAdapter: GenreAdapter by lazy {
        GenreAdapter()
    }

    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)

    override fun setUpViews() {
        initView()
    }

    override fun initView() {
        viewModel.getMovies()
        binding.contentHome.searchMovie.setSearchInput()

        setListMovies()
        setProgressBar()
        setMovies()
    }

    override fun setListMovies() {
        binding.contentHome.rvMovies.adapter = adapterMovie
    }

    override fun setBanner(movie: Movie) {
        binding.ivThumbnailLatestMovie.visibility = View.VISIBLE
        binding.tvTitleMovieLatest.text = movie.title

        setImage(
            binding.ivThumbnailLatestMovie,
            requireContext(),
            BASE_IMAGE + movie.backdropPath
        )
    }

    override fun setMovies() {
        viewModel.moviePopularList.observe(requireActivity()) { responseMovie ->
            val firstMovie = responseMovie.results[0]
            (binding.contentHome.rvMovies.adapter as MovieAdapter).movies = responseMovie.results
            setBanner(firstMovie)

            genreAdapter.items = firstMovie.genres ?: emptyList()
            binding.rvGenres.adapter = genreAdapter
        }

        viewModel.movieFromSearch.observe(requireActivity()) { responseMovie ->
            (binding.contentHome.rvMovies.adapter as MovieAdapter).movies = responseMovie.results

            binding.ivThumbnailLatestMovie.setImageDrawable(null)
            binding.ivThumbnailLatestMovie.visibility = View.GONE
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun EditText.setSearchInput() {
        this.setOnTouchListener { _, event ->
            val drawableRight = 2
            val rightPosition = this.right
            val width = this.compoundDrawables[drawableRight].bounds.width()

            if(event.action == MotionEvent.ACTION_UP) {
                if(event.rawX >= (rightPosition - width)) {
                    if(this.text.toString().isNotBlank()) {
                        this.text.clear()
                    }
                }
            }
            false
        }

        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.isNullOrEmpty()) {
                    this@setSearchInput.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null,
                        ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_search_24),
                        null
                    )
                } else {
                    this@setSearchInput.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null,
                        ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_cancel_24),
                        null
                    )
                }
            }

            override fun afterTextChanged(s: Editable?) {
                val text = this@setSearchInput.text.toString()
                viewModel.searchMovie(text.replace(" ", "+"))
            }
        })
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