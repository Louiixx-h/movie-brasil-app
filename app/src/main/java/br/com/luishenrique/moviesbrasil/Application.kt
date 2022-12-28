package br.com.luishenrique.moviesbrasil

import br.com.luishenrique.moviesbrasil.favorites.service.StorageImpl

class Application : android.app.Application() {

    override fun onCreate() {
        super.onCreate()
        StorageImpl.init(context = this)
    }
}