package br.com.luishenrique.moviesbrasil.network.di

import br.com.luishenrique.moviesbrasil.network.Network
import br.com.luishenrique.moviesbrasil.network.NetworkImpl
import org.koin.dsl.module

val network = module {
    factory<Network> { NetworkImpl() }
}