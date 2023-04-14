package br.com.luishenrique.moviesbrasil.details

import android.os.Bundle
import android.view.View
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.base.BaseFragment
import br.com.luishenrique.moviesbrasil.databinding.FragmentDetailsBinding
import br.com.luishenrique.moviesbrasil.details.adapters.GenreAdapter
import br.com.luishenrique.moviesbrasil.details.models.MovieDetail
import br.com.luishenrique.moviesbrasil.utils.BASE_IMAGE
import br.com.luishenrique.moviesbrasil.utils.setImage
import org.koin.android.ext.android.inject

class DetailsFragment : BaseFragment<FragmentDetailsBinding>(), DetailsFragmentContract {

    private val viewModel: DetailsFragmentViewModelImpl by inject()
    private val genreAdapter: GenreAdapter by inject()
    private val movieId: Int? by lazy { arguments?.getInt(DetailsActivity.DETAILS_ID) }

    override fun getViewBinding() = FragmentDetailsBinding.inflate(layoutInflater)

    override fun setUpViews() {
        init()
        setObservers()
        setCallbacks()
    }

    override fun init() {
        viewModel.getDetails(movieId!!)
    }

    override fun setObservers() {
        viewModel.movieDetail.observe(viewLifecycleOwner) { movieDetail ->
            renderDetails(movieDetail)
        }

        viewModel.progressBar.observe(viewLifecycleOwner) { stateProgressBar ->
            changeVisibilityProgressBar(stateProgressBar)
        }

        viewModel.isFavorite.observe(viewLifecycleOwner) {
            changeIconFavorite(it)
        }
    }

    override fun setCallbacks() {
        binding.imgAddMyList.setOnClickListener {
            clickOnFavorite()
        }
    }

    override fun clickOnFavorite() {
        viewModel.clickOnFavorite()
    }

    override fun renderDetails(movieDetail: MovieDetail) {
        setImage(binding.ivPoster, BASE_IMAGE + movieDetail.backdropPath)
        genreAdapter.items = movieDetail.genres
        binding.tvTitle.text = movieDetail.originalTitle
        binding.overview.text = movieDetail.overview
        binding.ratingStar.rating = movieDetail.voteAverage.toFloat().div(2)
        binding.rvGenres.adapter = genreAdapter
    }

    override fun changeVisibilityProgressBar(stateProgressBar: Boolean) {
        if (stateProgressBar) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun changeIconFavorite(isFavorited: Boolean) {
        if (isFavorited) {
            binding.imgAddMyList.setImageResource(R.drawable.baseline_check_24)
        } else {
            binding.imgAddMyList.setImageResource(R.drawable.baseline_add_24)
        }
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
