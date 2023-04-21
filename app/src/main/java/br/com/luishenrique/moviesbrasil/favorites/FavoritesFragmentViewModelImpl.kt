package br.com.luishenrique.moviesbrasil.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.luishenrique.moviesbrasil.common.BaseViewModel
import br.com.luishenrique.moviesbrasil.details.models.MovieDetail
import br.com.luishenrique.moviesbrasil.favorites.repository.FavoritesRepository
import kotlinx.coroutines.launch

class FavoritesFragmentViewModelImpl(
    private val repository: FavoritesRepository
): BaseViewModel(), FavoritesFragmentViewModel {
    
    private val _command = MutableLiveData<ResourceFavorites<List<MovieDetail>>>()
    override val command: LiveData<ResourceFavorites<List<MovieDetail>>> = _command

    override fun getMovies() {
        onLoading(true)

        viewModelScope.launch {
            network.call(
                block = { repository.getMovies() },
                onSuccess = { onSuccessGetMovies(ResourceFavorites.Success(it)) },
                onError = { onErrorGetMovie(it) },
                finally = { onLoading(false) }
            )
        }
    }

    override fun onSuccessGetMovies(response: ResourceFavorites<List<MovieDetail>>) {
        if(response.data == null) {
            onErrorGetMovie(Exception("Lista vazia!"))
        }

        _command.postValue(ResourceFavorites.Success(response.data!!))
    }

    override fun onErrorGetMovie(exception: Exception) {
        _command.postValue(ResourceFavorites.Error(exception.message.orEmpty()))
    }

    override fun onLoading(value: Boolean) {
        _command.postValue(ResourceFavorites.Loading(value))
    }
}