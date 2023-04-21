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

    private var job: Job? = null

    private val _command = MutableLiveData<ResourceHome<ResultMovie>>()
    override val command: LiveData<ResourceHome<ResultMovie>> = _command

    override fun getMovies() {
        onLoading(true)

        viewModelScope.launch {
            network.callResponse(
                block = { repository.getMovies() },
                onSuccess = { onSuccessGetMovies(ResourceHome.Success(it)) },
                onError = { onErrorGetMovies(it) },
                finally = { onLoading(false) }
            )
        }
    }

    override fun searchMovie(title: String) {
        job?.cancel()

        if (title.isBlank()) {
            getMovies()
            return
        }

        job = viewModelScope.launch {
            delay(2000)

            onLoading(true)

            network.callResponse(
                block = { repository.searchMovie(title) },
                onSuccess = { onSuccessGetMovies(ResourceHome.SearchSuccess(it)) },
                onError = { onErrorGetMovies(it) },
                finally = { onLoading(false) }
            )
        }
    }

    override fun onSuccessGetMovies(res: ResourceHome<ResultMovieResponseVO>) {
        if(res.data == null) {
            onErrorGetMovies(Exception())
        }

        val movies = MovieMapper.transform(res.data!!)

        when (res) {
            is ResourceHome.Success -> {
                _command.value = ResourceHome.Success(movies)
            }
            is ResourceHome.SearchSuccess -> {
                _command.value = ResourceHome.SearchSuccess(movies)
            }
            else -> {}
        }
    }

    override fun onErrorGetMovies(exception: Exception) {
        _command.value = ResourceHome.Error(exception.message.orEmpty())
    }

    override fun onLoading(value: Boolean) {
        _command.value = ResourceHome.Loading(value)
    }
}