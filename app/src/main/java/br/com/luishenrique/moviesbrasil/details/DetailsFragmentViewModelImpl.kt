package br.com.luishenrique.moviesbrasil.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.luishenrique.moviesbrasil.details.models.MovieDetail
import br.com.luishenrique.moviesbrasil.details.models.MovieDetailsMapper
import br.com.luishenrique.moviesbrasil.details.repository.DetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DetailsFragmentViewModelImpl(
    private val repository: DetailsRepository
): ViewModel(), DetailsFragmentViewModel {

    private val _progressBar = MutableLiveData<Boolean>()
    override val progressBar: LiveData<Boolean> = _progressBar

    private val _isFavorite = MutableLiveData<Boolean>()
    override val isFavorite: LiveData<Boolean> = _isFavorite

    private val _movieDetail = MutableLiveData<MovieDetail>()
    override val movieDetail: LiveData<MovieDetail> = _movieDetail

    override fun getDetails(movieId: Int) {
        viewModelScope.launch {
            _progressBar.postValue(true)

            val deferred = async(Dispatchers.IO) { repository.getDetails(movieId) }
            val response = deferred.await()

            if (response.isSuccessful && response.body() != null) {
                _movieDetail.value = MovieDetailsMapper.transform(response.body()!!)
                _isFavorite.value = _movieDetail.value?.let { repository.hasMovie(it) }
            }

            _progressBar.postValue(false)
        }
    }

    override fun clickOnFavorite() {
        if (_isFavorite.value == true) {
            removeMovieToFavorites()
        } else {
            addMovieToFavorites()
        }
    }

    override fun addMovieToFavorites() {
        val isAddedToFavorites = _movieDetail.value?.let { repository.addMovieToFavorites(it) }

        if(isAddedToFavorites == true) {
            _isFavorite.value = true
        }
    }

    override fun removeMovieToFavorites() {
        val isRemovedToFavorites = _movieDetail.value?.let { repository.removeMovieToFavorites(it) }

        if(isRemovedToFavorites == true) {
            _isFavorite.value = false
        }
    }
}
