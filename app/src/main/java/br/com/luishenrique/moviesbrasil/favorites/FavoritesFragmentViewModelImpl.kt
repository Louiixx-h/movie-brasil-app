package br.com.luishenrique.moviesbrasil.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.luishenrique.moviesbrasil.favorites.models.mapper.MoviesMapper
import br.com.luishenrique.moviesbrasil.favorites.repository.FavoritesRepository
import br.com.luishenrique.moviesbrasil.favorites.repository.FavoritesRepositoryImpl
import br.com.luishenrique.moviesbrasil.home.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesFragmentViewModelImpl: ViewModel(), FavoritesFragmentViewModel {

    private val repository: FavoritesRepository = FavoritesRepositoryImpl()

    private val _progressBar = MutableLiveData<Boolean>()
    override val progressBar: LiveData<Boolean> = _progressBar

    private val _movies = MutableLiveData<List<Movie>>()
    override val movies: LiveData<List<Movie>> = _movies

    override fun getMovies() {
        _progressBar.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                val response = repository.getMovies()

                if (response.isNotEmpty()) {
                    _movies.value = MoviesMapper.transform(response)
                }

                _progressBar.postValue(false)
            }
        }
    }
}