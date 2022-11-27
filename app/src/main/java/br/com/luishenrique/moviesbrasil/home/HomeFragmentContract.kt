package br.com.luishenrique.moviesbrasil.home

import br.com.luishenrique.moviesbrasil.home.models.Movie

interface HomeFragmentContract {
    fun initView()
    fun setMovies()
    fun setProgressBar()
    fun setBanner(movie: Movie)
    fun setListMovies()
    fun goToDetails(movie: Movie)
}
