package br.com.luishenrique.moviesbrasil.home

import androidx.fragment.app.Fragment

interface HomeActivityContract {
    fun setToolbar()
    fun setBottomNavigation()
    fun setFragment(fragment: Fragment)
}