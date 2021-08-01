package br.com.luishenrique.moviesbrasil.data.repository.api

import br.com.luishenrique.moviesbrasil.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieService {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getInstance(): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}