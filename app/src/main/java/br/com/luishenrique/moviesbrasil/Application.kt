package br.com.luishenrique.moviesbrasil

import br.com.luishenrique.moviesbrasil.details.di.details
import br.com.luishenrique.moviesbrasil.favorites.di.favorites
import br.com.luishenrique.moviesbrasil.home.di.homeModule
import br.com.luishenrique.moviesbrasil.service.di.serviceModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Application : android.app.Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@Application)
            modules(appModule())
        }
    }

    private fun appModule() = listOf(
        serviceModule,
        homeModule,
        details,
        favorites
    )
}