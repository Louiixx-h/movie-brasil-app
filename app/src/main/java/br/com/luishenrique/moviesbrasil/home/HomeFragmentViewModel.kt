package br.com.luishenrique.moviesbrasil.home

import androidx.lifecycle.*
import br.com.luishenrique.moviesbrasil.home.models.MovieMapper
import br.com.luishenrique.moviesbrasil.home.repository.HomeRepository
import br.com.luishenrique.moviesbrasil.home.models.ResultMovie
import kotlinx.coroutines.*

class HomeFragmentViewModel: ViewModel() {

    private val repository: HomeRepository = HomeRepository()

    private val _progressBar = MutableLiveData<Boolean>()
    val progressBar: LiveData<Boolean> = _progressBar

    private val _moviePopularList = MutableLiveData<ResultMovie>()
    val moviePopularList: LiveData<ResultMovie> = _moviePopularList

    fun getMoviesPopular() {
        _progressBar.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                val response = repository.getMoviesPopular()

                if (response.isSuccessful && response.body() != null) {
                    _moviePopularList.value = MovieMapper.transform(response.body()!!)
                }

                _progressBar.postValue(false)
            }
        }
    }
}