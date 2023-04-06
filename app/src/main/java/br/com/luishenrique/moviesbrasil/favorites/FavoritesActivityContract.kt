package br.com.luishenrique.moviesbrasil.favorites

import androidx.fragment.app.Fragment

interface FavoritesActivityContract {
    fun setFragment(fragment: Fragment)
    fun setToolbar()
}