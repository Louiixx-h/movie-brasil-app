package br.com.luishenrique.moviesbrasil.search.di

import br.com.luishenrique.moviesbrasil.search.SearchFragmentViewModelImpl
import br.com.luishenrique.moviesbrasil.search.adapters.SearchMovieAdapter
import br.com.luishenrique.moviesbrasil.search.repository.SearchRepository
import br.com.luishenrique.moviesbrasil.search.repository.SearchRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    factory { params -> SearchMovieAdapter(params.get()) }
    factory<SearchRepository> { SearchRepositoryImpl(get()) }
    viewModel { SearchFragmentViewModelImpl(get()) }
}