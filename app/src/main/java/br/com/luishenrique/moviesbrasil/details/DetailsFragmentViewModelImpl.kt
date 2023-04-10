package br.com.luishenrique.moviesbrasil.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.luishenrique.moviesbrasil.details.models.MovieDetail
import br.com.luishenrique.moviesbrasil.details.models.MovieDetailsMapper
import br.com.luishenrique.moviesbrasil.details.repository.DetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsFragmentViewModelImpl(
    private val repository: DetailsRepository
): ViewModel(), DetailsFragmentViewModel {

    private val _progressBar = MutableLiveData<Boolean>()
    override val progressBar: LiveData<Boolean> = _progressBar

    private val _movieDetail = MutableLiveData<MovieDetail>()
    override val movieDetail: LiveData<MovieDetail> = _movieDetail

    override fun getDetails(movieId: Int) {
        _progressBar.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                val response = repository.getDetails(movieId)

                if (response.isSuccessful && response.body() != null) {
                    _movieDetail.value = MovieDetailsMapper.transform(response.body()!!)
                }

                _progressBar.postValue(false)
            }
        }
    }
}
