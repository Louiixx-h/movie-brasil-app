package br.com.luishenrique.moviesbrasil.home

import androidx.lifecycle.*
import br.com.luishenrique.moviesbrasil.common.BaseViewModel
import br.com.luishenrique.moviesbrasil.home.models.MovieMapper
import br.com.luishenrique.moviesbrasil.home.models.ResultMovie
import br.com.luishenrique.moviesbrasil.home.models.responses.ResultMovieResponseVO
import br.com.luishenrique.moviesbrasil.home.repository.HomeRepository
import kotlinx.coroutines.*

class HomeFragmentViewModelImpl(
    private val repository: HomeRepository
) : BaseViewModel(), HomeFragmentViewModel {

    private val _command = MutableLiveData<ResourceHome<ResultMovie>>()
    override val command: LiveData<ResourceHome<ResultMovie>> = _command

    override fun getMovies() {
        onLoading()

        viewModelScope.launch {
            network.callResponse(
                block = { repository.getMovies() },
                onSuccess = { onSuccessGetMovies(ResourceHome.Success(it)) },
                onError = { onErrorGetMovies(it) }
            )
        }
    }

    override fun onSuccessGetMovies(res: ResourceHome.Success<ResultMovieResponseVO>) {
        if(res.data == null) {
            onErrorGetMovies(Exception())
        }

        val movies = MovieMapper.transform(res.data!!)
        _command.value = ResourceHome.Success(movies)
    }

    override fun onErrorGetMovies(exception: Exception) {
        _command.value = ResourceHome.Error(exception.message.orEmpty())
    }

    override fun onLoading() {
        _command.value = ResourceHome.Loading()
    }
}