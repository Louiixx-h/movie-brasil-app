package br.com.luishenrique.moviesbrasil.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.home.models.Genre

class GenreAdapter : RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    var items: List<Genre> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameGenre: TextView = view.findViewById(R.id.name_genre)

        fun bind(genre: Genre) {
            nameGenre.text = genre.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_genre,
                parent,
                false
            )

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = if(items.size < 3) items.size else 3
}