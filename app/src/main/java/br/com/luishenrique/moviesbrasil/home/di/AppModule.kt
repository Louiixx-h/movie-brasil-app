package br.com.luishenrique.moviesbrasil.home.di

import br.com.luishenrique.moviesbrasil.home.HomeFragmentViewModelImpl
import br.com.luishenrique.moviesbrasil.home.adapters.MovieAdapter
import br.com.luishenrique.moviesbrasil.home.repository.HomeRepository
import br.com.luishenrique.moviesbrasil.home.repository.HomeRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    factory { params -> MovieAdapter(params.get()) }
    factory<HomeRepository> { HomeRepositoryImpl(get()) }
    viewModel { HomeFragmentViewModelImpl(get()) }
}