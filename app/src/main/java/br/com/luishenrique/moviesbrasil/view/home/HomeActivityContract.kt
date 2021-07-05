package br.com.luishenrique.moviesbrasil.view.home

interface HomeActivityContract {
    fun initView()
    fun configViewModel()
    fun getMoviesNowPlaying()
    fun getMoviesPopular()
    fun getMoviesRecent()
}
