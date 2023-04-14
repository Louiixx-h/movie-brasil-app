package br.com.luishenrique.moviesbrasil.service

import android.content.Context
import android.content.SharedPreferences
import br.com.luishenrique.moviesbrasil.details.models.MovieDetail
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class StorageImpl(context: Context) : Storage {

    private val mode = Context.MODE_PRIVATE
    private val name = "br.com.luislabs.moviebrasil.storage"
    private var sharedPreferences: SharedPreferences
    override lateinit var moviesSaved: MutableList<MovieDetail>

    init {
        sharedPreferences = context.getSharedPreferences(name, mode)
        moviesSaved = getMovies()
    }

    private fun getMovies(): MutableList<MovieDetail> {
        val json = sharedPreferences.getString("movies", "") ?: ""
        val listType: Type = object : TypeToken<MutableList<MovieDetail?>?>() {}.type
        return Gson().fromJson(json, listType) ?: mutableListOf()
    }

    override fun addMovie(newMovie: MovieDetail) : Boolean {
        if(!hasMovie(newMovie)) {
            moviesSaved.add(newMovie)
            val json = moviesSaved.transformToJson()
            sharedPreferences.edit{ it.putString("movies", json) }
            return true
        }

        return false
    }

    override fun removeMovie(movie: MovieDetail): Boolean {
        var indexMovie = -1

        moviesSaved.forEachIndexed { index, movieDetail ->
            if(movieDetail.id == movie.id) {
                indexMovie = index
            }
        }

        if(indexMovie >= 0) {
            moviesSaved.removeAt(indexMovie)
            val json = moviesSaved.transformToJson()
            sharedPreferences.edit{ it.putString("movies", json) }
        }

        return indexMovie >= 0
    }

    override fun hasMovie(newMovie: MovieDetail) : Boolean {
        var hasMovie = false

        moviesSaved.forEach {
            if(it.id == newMovie.id) {
                hasMovie = true
            }
        }

        return hasMovie
    }

    private fun MutableList<MovieDetail>.transformToJson(): String {
        return Gson().toJson(this)
    }

    private inline fun SharedPreferences.edit(
        operation: (SharedPreferences.Editor) -> Unit
    ) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }
}
