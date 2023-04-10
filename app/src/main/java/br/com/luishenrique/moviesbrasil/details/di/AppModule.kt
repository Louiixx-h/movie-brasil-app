package br.com.luishenrique.moviesbrasil.details.di

import br.com.luishenrique.moviesbrasil.details.DetailsFragmentViewModelImpl
import br.com.luishenrique.moviesbrasil.details.adapters.GenreAdapter
import br.com.luishenrique.moviesbrasil.details.repository.DetailsRepository
import br.com.luishenrique.moviesbrasil.details.repository.DetailsRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val details = module {
    factory { GenreAdapter() }
    factory<DetailsRepository> { DetailsRepositoryImpl(get()) }
    viewModel { DetailsFragmentViewModelImpl(get()) }
}