package br.com.luishenrique.moviesbrasil.home

import androidx.lifecycle.*
import br.com.luishenrique.moviesbrasil.home.models.MovieMapper
import br.com.luishenrique.moviesbrasil.home.repository.HomeRepository
import br.com.luishenrique.moviesbrasil.home.models.ResultMovie
import kotlinx.coroutines.*

class HomeFragmentViewModel: ViewModel() {

    private var job: Job? = null
    private val repository: HomeRepository = HomeRepository()

    private val _progressBar = MutableLiveData<Boolean>()
    val progressBar: LiveData<Boolean> = _progressBar

    private val _moviePopularList = MutableLiveData<ResultMovie>()
    val moviePopularList: LiveData<ResultMovie> = _moviePopularList

    private val _movieFromSearch = MutableLiveData<ResultMovie>()
    val movieFromSearch: LiveData<ResultMovie> = _movieFromSearch

    fun getMovies() {
        _progressBar.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                val response = repository.getMovies()

                if (response.isSuccessful && response.body() != null) {
                    _moviePopularList.value = MovieMapper.transform(response.body()!!)
                }

                _progressBar.postValue(false)
            }
        }
    }

    fun searchMovie(title: String) {
        job?.cancel()

        job = viewModelScope.launch(Dispatchers.IO) {

            if (title.isBlank()) {
                getMovies()
                job?.cancel()
                return@launch
            }

            delay(2000)

            withContext(Dispatchers.Main) {
                _progressBar.postValue(true)
                val response = repository.searchMovie(title)

                if (response.isSuccessful && response.body() != null) {
                    _movieFromSearch.value = MovieMapper.transform(response.body()!!)
                }
                _progressBar.postValue(false)
            }
        }
    }
}