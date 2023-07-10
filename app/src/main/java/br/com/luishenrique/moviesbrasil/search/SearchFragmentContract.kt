package br.com.luishenrique.moviesbrasil.search

import br.com.luishenrique.moviesbrasil.search.models.Movie
import br.com.luishenrique.moviesbrasil.search.models.ResultMovie

interface SearchFragmentContract {
    fun setupListMovies()
    fun setupMovies(movies: List<Movie>)
    fun setupObserver()
    fun handleSuccess(resultMovie: ResultMovie?)
    fun handleSearchSuccess(resultMovie: ResultMovie?)
    fun goToDetails(movie: Movie)
    fun showLoader()
    fun hideLoader()
}