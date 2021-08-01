package br.com.luishenrique.moviesbrasil.presentation.view.home

interface HomeActivityContract {
    fun initView()
    fun configViewModel()
    fun getMoviesNowPlaying()
    fun getMoviesPopular()
    fun getMoviesRecent()
    fun setProgressBar()
}
