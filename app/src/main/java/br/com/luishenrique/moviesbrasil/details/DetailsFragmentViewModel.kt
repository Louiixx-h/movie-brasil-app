package br.com.luishenrique.moviesbrasil.details

import androidx.lifecycle.LiveData
import br.com.luishenrique.moviesbrasil.details.models.MovieDetail
import br.com.luishenrique.moviesbrasil.details.models.SimilarResultMovie
import br.com.luishenrique.moviesbrasil.details.models.VideosResultMovie
import br.com.luishenrique.moviesbrasil.details.models.responses.MovieDetailsResponseVO
import br.com.luishenrique.moviesbrasil.details.models.responses.SimilarResultMovieResponseVO
import br.com.luishenrique.moviesbrasil.details.models.responses.VideosResultMovieResponseVO

interface DetailsFragmentViewModel {
    val command: LiveData<ResourceDetails<MovieDetail>>
    val commandVideos: LiveData<ResourceMoviesVideos<VideosResultMovie>>
    val commandSimilar: LiveData<ResourceSimilarMovie<SimilarResultMovie>>

    fun getDetails(movieId: Int)
    fun getSimilarMovies(movieId: Int)
    fun onSuccessGetMovie(response: ResourceDetails<MovieDetailsResponseVO>)
    fun onSuccessGetSimilarMovie(response: ResourceSimilarMovie<SimilarResultMovieResponseVO>)
    fun onErrorGetMovie(exception: Exception)
    fun onLoading(value: Boolean)
    fun clickOnFavorite()
    fun addMovieToFavorites()
    fun removeMovieToFavorites()
    fun movieIsSaved()
    fun getMoviesVideos(movieId: Int)
    fun onSuccessGetMoviesVideos(response: ResourceMoviesVideos<VideosResultMovieResponseVO>)
}