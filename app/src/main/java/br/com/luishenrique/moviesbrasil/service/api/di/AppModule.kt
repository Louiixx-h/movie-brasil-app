package br.com.luishenrique.moviesbrasil.service.api.di

import br.com.luishenrique.moviesbrasil.service.api.ApiService
import br.com.luishenrique.moviesbrasil.utils.BASE_URL
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val serviceModule = module {
    single { createService() }
}

private fun createService(): ApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(ApiService::class.java)
}