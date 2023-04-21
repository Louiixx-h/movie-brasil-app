package br.com.luishenrique.moviesbrasil.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.luishenrique.moviesbrasil.common.BaseViewModel
import br.com.luishenrique.moviesbrasil.details.models.MovieDetail
import br.com.luishenrique.moviesbrasil.details.models.MovieDetailsMapper
import br.com.luishenrique.moviesbrasil.details.models.responses.MovieDetailsResponseVO
import br.com.luishenrique.moviesbrasil.details.repository.DetailsRepository
import kotlinx.coroutines.launch

class DetailsFragmentViewModelImpl(
    private val repository: DetailsRepository
): BaseViewModel(), DetailsFragmentViewModel {

    private val _command = MutableLiveData<ResourceDetails<MovieDetail>>()
    override val command: LiveData<ResourceDetails<MovieDetail>> = _command

    private val isMovieSaved: MutableLiveData<Boolean> = MutableLiveData()
    private val movieSaved: MutableLiveData<MovieDetail> = MutableLiveData()

    override fun getDetails(movieId: Int) {
        onLoading(true)

        viewModelScope.launch {
            network.callResponse(
                block = { repository.getDetails(movieId) },
                onSuccess = { onSuccessGetMovie(ResourceDetails.Success(it)) },
                onError = { onErrorGetMovie(it) },
                finally = { onLoading(false) }
            )
        }
    }

    override fun onSuccessGetMovie(response: ResourceDetails<MovieDetailsResponseVO>) {
        val movie = MovieDetailsMapper.transform(response.data)

        movieSaved.postValue(movie)
        movieIsSaved()

        _command.postValue(ResourceDetails.Success(movie))
    }

    override fun movieIsSaved() {
        if(movieSaved.value == null) return

        val response = repository.hasMovie(movieSaved.value!!)
        isMovieSaved.value = response

        if(isMovieSaved.value == true) {
            _command.postValue(ResourceDetails.AddedToFavorites())
        } else {
            _command.postValue(ResourceDetails.RemovedToFavorites())
        }
    }

    override fun onErrorGetMovie(exception: Exception) {
        _command.postValue(ResourceDetails.Error(exception.message.orEmpty()))
    }

    override fun onLoading(value: Boolean) {
        _command.postValue(ResourceDetails.Loading(value))
    }

    override fun clickOnFavorite() {
        if (isMovieSaved.value == true) {
            removeMovieToFavorites()
        } else {
            addMovieToFavorites()
        }
    }

    override fun addMovieToFavorites() {
        val isAddedToFavorites = movieSaved.value?.let { repository.addMovieToFavorites(it) }

        if(isAddedToFavorites == true) {
            isMovieSaved.postValue(true)
            _command.postValue(ResourceDetails.AddedToFavorites())
        }
    }

    override fun removeMovieToFavorites() {
        val isRemovedToFavorites = movieSaved.value?.let { repository.removeMovieToFavorites(it) }

        if(isRemovedToFavorites == true) {
            isMovieSaved.postValue(false)
            _command.postValue(ResourceDetails.RemovedToFavorites())
        }
    }
}
