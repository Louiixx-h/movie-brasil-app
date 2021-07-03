package br.com.luishenrique.moviesbrasil.views.home

interface HomeActivityContract {
    fun initView()
    fun configViewModel()
    fun getMoviesNowPlaying()
    fun getMoviesPopular()
    fun getMoviesRecent()
}
