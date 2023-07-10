package br.com.luishenrique.moviesbrasil.search

import androidx.lifecycle.*
import br.com.luishenrique.moviesbrasil.common.BaseViewModel
import br.com.luishenrique.moviesbrasil.search.models.MovieMapper
import br.com.luishenrique.moviesbrasil.search.models.ResultMovie
import br.com.luishenrique.moviesbrasil.search.models.responses.ResultMovieResponseVO
import br.com.luishenrique.moviesbrasil.search.repository.SearchRepository
import kotlinx.coroutines.*

class SearchFragmentViewModelImpl(
    private val repository: SearchRepository
) : BaseViewModel(), SearchFragmentViewModel {

    private var job: Job? = null

    private val _command = MutableLiveData<ResourceSearch<ResultMovie>>()
    override val command: LiveData<ResourceSearch<ResultMovie>> = _command

    override fun getMovies(movieName: String) {
        job?.cancel()

        job = viewModelScope.launch {
            delay(2000)

            onLoading()

            network.callResponse(
                block = { repository.getMovies(movieName) },
                onSuccess = { onSuccessGetMovies(ResourceSearch.Success(it)) },
                onError = { onErrorGetMovies(it) }
            )
        }
    }

    override fun onSuccessGetMovies(res: ResourceSearch.Success<ResultMovieResponseVO>) {
        if(res.data == null) {
            onErrorGetMovies(Exception())
        }

        val movies = MovieMapper.transform(res.data!!)
        _command.value = ResourceSearch.Success(movies)
    }

    override fun onErrorGetMovies(exception: Exception) {
        _command.value = ResourceSearch.Error(exception.message.orEmpty())
    }

    override fun onLoading() {
        _command.value = ResourceSearch.Loading()
    }
}