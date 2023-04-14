package br.com.luishenrique.moviesbrasil.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.luishenrique.moviesbrasil.details.models.MovieDetail
import br.com.luishenrique.moviesbrasil.favorites.repository.FavoritesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesFragmentViewModelImpl(
    private val repository: FavoritesRepository
): ViewModel(), FavoritesFragmentViewModel {

    private val _progressBar = MutableLiveData<Boolean>()
    override val progressBar: LiveData<Boolean> = _progressBar

    private val _movies = MutableLiveData<List<MovieDetail>>()
    override val movies: LiveData<List<MovieDetail>> = _movies

    override fun getMovies() {
        _progressBar.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                val response = repository.getMovies()

                if (response.isNotEmpty()) {
                    _movies.value = response
                }

                _progressBar.postValue(false)
            }
        }
    }
}