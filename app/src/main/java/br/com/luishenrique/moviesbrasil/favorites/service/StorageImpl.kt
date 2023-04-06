package br.com.luishenrique.moviesbrasil.favorites.service

import android.content.Context
import android.content.SharedPreferences

object StorageImpl : Storage {

    private const val MODE = Context.MODE_PRIVATE
    private const val NAME = "br.com.luislabs.moviebrasil.storage"
    private lateinit var sharedPreferences: SharedPreferences

    override fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(NAME, MODE)
    }

    var getMovies: String
        get() = sharedPreferences.getString("movies", "") ?: ""
        set(value) = sharedPreferences.edit{ it.putString("movie", value) }

    private inline fun SharedPreferences.edit(
        operation: (SharedPreferences.Editor) -> Unit
    ) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }
}
