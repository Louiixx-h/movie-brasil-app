package br.com.luishenrique.moviesbrasil.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.luishenrique.moviesbrasil.details.models.MovieDetail
import br.com.luishenrique.moviesbrasil.details.models.MovieDetailsMapper
import br.com.luishenrique.moviesbrasil.details.repository.DetailsRepository
import br.com.luishenrique.moviesbrasil.details.repository.DetailsRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel: ViewModel() {

    private val repository: DetailsRepository = DetailsRepositoryImpl()

    private val _progressBar = MutableLiveData<Boolean>()
    val progressBar: LiveData<Boolean> = _progressBar

    private val _movieDetail = MutableLiveData<MovieDetail>()
    val movieDetail: LiveData<MovieDetail> = _movieDetail

    fun getDetails(movieId: Int) {
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
