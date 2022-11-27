package br.com.luishenrique.moviesbrasil.favorites.repository

import br.com.luishenrique.moviesbrasil.favorites.service.StorageImpl
import br.com.luishenrique.moviesbrasil.home.models.responses.MovieResponseVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class FavoritesRepositoryImpl : FavoritesRepository {

    override fun getMovies(): List<MovieResponseVO> {
        val json = StorageImpl.getMovies
        val listType: Type = object : TypeToken<ArrayList<MovieResponseVO?>?>() {}.type
        return Gson().fromJson(json, listType)
    }
}
