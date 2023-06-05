package br.com.luishenrique.moviesbrasil.details

import android.os.Bundle
import androidx.core.view.isVisible
import br.com.luishenrique.moviesbrasil.BuildConfig
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.common.BaseFragment
import br.com.luishenrique.moviesbrasil.databinding.FragmentDetailsBinding
import br.com.luishenrique.moviesbrasil.details.adapters.GenreAdapter
import br.com.luishenrique.moviesbrasil.details.models.MovieDetail
import br.com.luishenrique.moviesbrasil.utils.delayOnLifecycle
import br.com.luishenrique.moviesbrasil.utils.setImage
import org.koin.android.ext.android.inject

class DetailsFragment : BaseFragment<FragmentDetailsBinding>(), DetailsFragmentContract {

    private val viewModel: DetailsFragmentViewModelImpl by inject()
    private val genreAdapter: GenreAdapter by inject()
    private val movieId: Int? by lazy { arguments?.getInt(DetailsActivity.DETAILS_ID) }

    override fun getViewBinding() = FragmentDetailsBinding.inflate(layoutInflater)

    override fun setUpViews() {
        viewModel.getDetails(movieId!!)

        setupObserver()
        setupCallbacks()
    }

    override fun setupObserver() {
        viewModel.command.observe(viewLifecycleOwner) { response ->
            when(response) {
                is ResourceDetails.Success -> response.data?.let { renderDetails(it) }
                is ResourceDetails.Error -> errorScreen()
                is ResourceDetails.Loading -> response.value?.let { changeVisibilityProgressBar(it) }
                is ResourceDetails.AddedToFavorites -> addedMovieToFavorites()
                is ResourceDetails.RemovedToFavorites -> removedMovieToFavorites()
            }
        }
    }

    override fun setupCallbacks() {
        binding.imgAddMyList.setOnClickListener {
            clickOnFavorite()
        }
    }

    override fun clickOnFavorite() {
        viewModel.clickOnFavorite()
    }

    override fun addedMovieToFavorites() {
        binding.imgAddMyList.setImageResource(R.drawable.baseline_check_24)
    }

    override fun removedMovieToFavorites() {
        binding.imgAddMyList.setImageResource(R.drawable.baseline_add_24)
    }

    override fun renderDetails(movieDetail: MovieDetail) {
        setImage(binding.ivPoster, BuildConfig.BASE_URL_IMAGE + movieDetail.backdropPath)
        genreAdapter.items = movieDetail.genres
        binding.tvTitle.text = movieDetail.originalTitle
        binding.overview.text = movieDetail.overview
        binding.ratingStar.rating = movieDetail.voteAverage.toFloat().div(2)
        binding.rvGenres.adapter = genreAdapter

        view?.delayOnLifecycle(10) { viewModel.movieIsSaved() }
    }

    override fun changeVisibilityProgressBar(stateProgressBar: Boolean) {
        binding.progressBar.isVisible = stateProgressBar
    }

    companion object {
        @JvmStatic
        fun newInstance(movieId: Int) = DetailsFragment().apply {
            val args = Bundle()
            args.putInt(DetailsActivity.DETAILS_ID, movieId)
            arguments = args
        }
    }
}
