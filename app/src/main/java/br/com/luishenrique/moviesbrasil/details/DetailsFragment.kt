package br.com.luishenrique.moviesbrasil.details

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import br.com.luishenrique.moviesbrasil.base.BaseFragment
import br.com.luishenrique.moviesbrasil.databinding.FragmentDetailsBinding
import br.com.luishenrique.moviesbrasil.details.adapters.GenreAdapter
import br.com.luishenrique.moviesbrasil.details.models.MovieDetail
import br.com.luishenrique.moviesbrasil.utils.BASE_IMAGE
import br.com.luishenrique.moviesbrasil.utils.getRating
import br.com.luishenrique.moviesbrasil.utils.setImage

class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    private val movieId: Int? by lazy {
        arguments?.getInt(DetailsActivity.DETAILS_ID)
    }
    private val viewModel: DetailsViewModel by lazy {
        ViewModelProvider(this)[DetailsViewModel::class.java]
    }
    private val genreAdapter: GenreAdapter by lazy {
        GenreAdapter()
    }

    override fun getViewBinding() = FragmentDetailsBinding.inflate(layoutInflater)

    override fun setUpViews() {
        init()
        setComponents()
        setProgressBar()
    }

    private fun init() {
        viewModel.getDetails(movieId!!)
    }

    private fun setComponents() {
        viewModel.movieDetail.observe(viewLifecycleOwner) { movieDetail ->
            renderDetails(movieDetail)
        }
    }

    private fun setProgressBar() {
        viewModel.progressBar.observe(requireActivity()) { stateProgressBar ->
            changeVisibilityProgressBar(stateProgressBar)
        }
    }

    private fun renderDetails(movieDetail: MovieDetail) {
        setImage(binding.ivPoster, BASE_IMAGE + movieDetail.backdropPath)
        binding.tvTitle.text = movieDetail.originalTitle
        binding.overview.text = movieDetail.overview
        binding.ratingStar.rating = movieDetail.voteAverage?.toFloat()?.div(2) ?: 0f

        genreAdapter.items = movieDetail.genres ?: emptyList()
        binding.rvGenres.adapter = genreAdapter
    }

    private fun changeVisibilityProgressBar(stateProgressBar: Boolean) {
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
