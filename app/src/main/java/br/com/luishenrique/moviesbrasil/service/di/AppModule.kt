package br.com.luishenrique.moviesbrasil.service.di

import br.com.luishenrique.moviesbrasil.BuildConfig
import br.com.luishenrique.moviesbrasil.service.Storage
import br.com.luishenrique.moviesbrasil.service.StorageImpl
import br.com.luishenrique.moviesbrasil.service.api.ApiService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val serviceModule = module {
    single { provideService(GsonConverterFactory.create()) }
    single<Storage> { StorageImpl(androidContext()) }
}

private fun provideService(factory: Converter.Factory): ApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(factory)
        .build()

    return retrofit.create(ApiService::class.java)
}