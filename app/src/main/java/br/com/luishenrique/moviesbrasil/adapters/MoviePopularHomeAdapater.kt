package br.com.luishenrique.moviesbrasil.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.data.models.MoviePopular
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviePopularHomeAdapater(
    private val context: Context,
    private val list: List<MoviePopular>?
) : RecyclerView.Adapter<MoviePopularHomeAdapater.MovieViewHolder>() {

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(moviePopular: MoviePopular) {
            itemView.tv_title_item_movie.text = moviePopular.title
            itemView.tv_vote_item_movie.text = moviePopular.vote_average
            Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500${moviePopular.poster_path}")
                .into(itemView.iv_thumbnail_item_movie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        list?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        if (list != null) {
            return list.size
        }
        return 0
    }
}
