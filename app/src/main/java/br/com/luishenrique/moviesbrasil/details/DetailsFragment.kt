package br.com.luishenrique.moviesbrasil.details

import android.os.Bundle
import android.view.View
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
        setComponents()
        setProgressBar()
    }

    override fun init() {
        viewModel.getDetails(movieId!!)
    }

    override fun setComponents() {
        viewModel.movieDetail.observe(viewLifecycleOwner) { movieDetail ->
            renderDetails(movieDetail)
        }
    }

    override fun setProgressBar() {
        viewModel.progressBar.observe(requireActivity()) { stateProgressBar ->
            changeVisibilityProgressBar(stateProgressBar)
        }
    }

    override fun renderDetails(movieDetail: MovieDetail) {
        setImage(binding.ivPoster, BASE_IMAGE + movieDetail.backdropPath)
        binding.tvTitle.text = movieDetail.originalTitle
        binding.overview.text = movieDetail.overview
        binding.ratingStar.rating = movieDetail.voteAverage.toFloat().div(2)

        genreAdapter.items = movieDetail.genres
        binding.rvGenres.adapter = genreAdapter
    }

    override fun changeVisibilityProgressBar(stateProgressBar: Boolean) {
        if (stateProgressBar) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
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
