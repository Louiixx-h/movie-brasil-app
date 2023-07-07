package br.com.luishenrique.moviesbrasil.home

import br.com.luishenrique.moviesbrasil.home.models.Movie
import br.com.luishenrique.moviesbrasil.home.models.ResultMovie

interface HomeFragmentContract {
    fun setupListMovies()
    fun setupBanner(movies: List<Movie>)
    fun setupMovies(movies: List<Movie>)
    fun setupObserver()
    fun handleSuccess(resultMovie: ResultMovie?)
    fun handleSearchSuccess(resultMovie: ResultMovie?)
    fun handleLoading(stateProgressBar: Boolean)
    fun goToDetails(movie: Movie)
}