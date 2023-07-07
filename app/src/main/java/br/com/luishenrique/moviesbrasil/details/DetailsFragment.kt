package br.com.luishenrique.moviesbrasil.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.common.BaseFragment
import br.com.luishenrique.moviesbrasil.databinding.FragmentDetailsBinding
import br.com.luishenrique.moviesbrasil.details.adapters.GenreAdapter
import br.com.luishenrique.moviesbrasil.details.adapters.SimilarMovieAdapter
import br.com.luishenrique.moviesbrasil.details.models.*
import br.com.luishenrique.moviesbrasil.utils.delayOnLifecycle
import br.com.luishenrique.moviesbrasil.utils.setImage
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class DetailsFragment : BaseFragment<FragmentDetailsBinding>(),
    DetailsFragmentContract,
    SimilarMovieAdapter.ListenerMovie {

    private val similarMovieAdapter: SimilarMovieAdapter by inject { parametersOf(this) }
    private val viewModel: DetailsFragmentViewModelImpl by inject()
    private val genreAdapter: GenreAdapter by inject()
    private val movieId: Int? by lazy { arguments?.getInt(DetailsActivity.DETAILS_ID) }

    override fun getViewBinding() = FragmentDetailsBinding.inflate(layoutInflater)

    override fun setUpViews(view: View) {
        viewModel.getDetails(movieId!!)
        viewModel.getSimilarMovies(movieId!!)
        viewModel.getMoviesVideos(movieId!!)
        setupObserver()
        setupCallbacks()
    }

    override fun setupObserver() {
        viewModel.command.observe(viewLifecycleOwner, detailsInfoObserver)
        viewModel.commandVideos.observe(viewLifecycleOwner, moviesVideosObserver)
        viewModel.commandSimilar.observe(viewLifecycleOwner, movieSimilarObserver)
    }

    private val detailsInfoObserver: (response: ResourceDetails<MovieDetail>) -> Unit = { response ->
        when(response) {
            is ResourceDetails.Success -> response.data?.let { renderDetails(it) }
            is ResourceDetails.Error -> errorScreen()
            is ResourceDetails.Loading -> response.value?.let { changeVisibilityProgressBar(it) }
            is ResourceDetails.AddedToFavorites -> addedMovieToFavorites()
            is ResourceDetails.RemovedToFavorites -> removedMovieToFavorites()
        }
    }

    private val moviesVideosObserver: (response: ResourceMoviesVideos<VideosResultMovie>) -> Unit = { response ->
        var movieVideo: VideoMovie? = null
        val videos = response.data?.results

        if (!videos.isNullOrEmpty()) {
            movieVideo = videos.find { it.type == "Trailer" }
        }

        binding.imgPlay.setOnClickListener {
            if(movieVideo != null) {
                val key = movieVideo.key
                val url = "https://www.youtube.com/watch?v=${key}"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
        }
    }

    private val movieSimilarObserver: (response: ResourceSimilarMovie<SimilarResultMovie>) -> Unit = { response ->
        response.data?.results?.let { similarMovieAdapter.setItems(it) }
        binding.rvMoviesSimilar.adapter = similarMovieAdapter
    }

    override fun setupCallbacks() {
        binding.imgAddMyList.setOnClickListener {
            clickOnFavorite()
        }
    }

    override fun onClick(movie: SimilarMovie) {
        goToDetails(movie)
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
        with(binding) {
            setImage(ivPoster, movieDetail.backdropPath)
            genreAdapter.items = movieDetail.genres
            tvTitle.text = movieDetail.originalTitle
            overview.text = movieDetail.overview
            tvReleaseDate.text = Regex("\\d{4}").find(movieDetail.releaseDate)?.value
            tvVoteAvarage.text = String.format("%.2f", movieDetail.voteAverage)
            rvGenres.adapter = genreAdapter
        }
        view?.delayOnLifecycle(10) { viewModel.movieIsSaved() }
    }

    override fun changeVisibilityProgressBar(stateProgressBar: Boolean) {
        binding.progressBar.isVisible = stateProgressBar
    }

    override fun goToDetails(movie: SimilarMovie) {
        startActivity(DetailsActivity.newInstance(requireActivity(), movie.id))
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
