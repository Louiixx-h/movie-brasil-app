package br.com.luishenrique.moviesbrasil.home

import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.base.BaseFragment
import br.com.luishenrique.moviesbrasil.databinding.FragmentHomeBinding
import br.com.luishenrique.moviesbrasil.details.DetailsActivity
import br.com.luishenrique.moviesbrasil.home.adapters.MovieAdapter
import br.com.luishenrique.moviesbrasil.home.models.Movie
import br.com.luishenrique.moviesbrasil.utils.BASE_IMAGE
import br.com.luishenrique.moviesbrasil.utils.setImage
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeFragmentContract, MovieAdapter.ListenerMovie {

    private val adapterMovie: MovieAdapter by inject { parametersOf(this) }
    private val viewModel: HomeFragmentViewModelImpl by inject()

    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)

    override fun setUpViews() {
        initView()
        setupToolbar()
    }

    private fun setupToolbar() {
        with((requireActivity() as AppCompatActivity)) {
            val toolbar: Toolbar = findViewById(R.id.toolbar_main)
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            toolbar.title = getString(R.string.app_name)
        }
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
        binding.ivThumbnailLatestMovie.isVisible = true
        binding.tvTitleMovieLatest.isVisible = true

        binding.tvTitleMovieLatest.text = movie.title
        setImage(
            binding.ivThumbnailLatestMovie,
            requireContext(),
            BASE_IMAGE + movie.backdropPath
        )
    }


    override fun setMovies() {
        viewModel.moviePopularList.observe(requireActivity()) { responseMovie ->
            setBanner(responseMovie.results[FIRST_MOVIE])
            adapterMovie.movies = responseMovie.results
        }

        viewModel.movieFromSearch.observe(requireActivity()) { responseMovie ->
            adapterMovie.movies = responseMovie.results
            binding.ivThumbnailLatestMovie.setImageDrawable(null)
            binding.ivThumbnailLatestMovie.isVisible = false
            binding.tvTitleMovieLatest.isVisible = false
        }
    }

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
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) = Unit

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                if(s.isNullOrEmpty()) {
                    val iconId = R.drawable.ic_baseline_search_24
                    val icon = ContextCompat.getDrawable(requireContext(), iconId)
                    addRightIcon(icon)
                } else {
                    val iconId = R.drawable.ic_baseline_cancel_24
                    val icon = ContextCompat.getDrawable(requireContext(), iconId)
                    addRightIcon(icon)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                val text = this@setSearchInput.text.toString()
                viewModel.searchMovie(text.replace(" ", "+"))
            }
        })
    }

    private fun EditText.addRightIcon(icon: Drawable?) {
        this.setCompoundDrawablesWithIntrinsicBounds(
            null,
            null,
            icon,
            null
        )
    }

    override fun setProgressBar() {
        viewModel.progressBar.observe(requireActivity()) { stateProgressBar ->
            changeVisibilityProgressBar(stateProgressBar)
        }
    }

    private fun changeVisibilityProgressBar(stateProgressBar: Boolean) {
        binding.progressBar.isVisible = stateProgressBar
    }

    override fun onClick(movie: Movie) {
        goToDetails(movie)
    }

    override fun goToDetails(movie: Movie) {
        startActivity(DetailsActivity.newInstance(requireActivity(), movie.id))
    }

    companion object {

        @JvmStatic
        private val FIRST_MOVIE = 0

        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}