package br.com.luishenrique.moviesbrasil.favorites.di

import br.com.luishenrique.moviesbrasil.favorites.FavoritesFragmentViewModelImpl
import br.com.luishenrique.moviesbrasil.favorites.adapters.AdapterFavoritesMovie
import br.com.luishenrique.moviesbrasil.favorites.repository.FavoritesRepository
import br.com.luishenrique.moviesbrasil.favorites.repository.FavoritesRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favorites = module {
    factory { params -> AdapterFavoritesMovie(params.get()) }
    factory<FavoritesRepository> { FavoritesRepositoryImpl(get()) }
    viewModel { FavoritesFragmentViewModelImpl(get()) }
}