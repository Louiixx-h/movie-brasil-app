package br.com.luishenrique.moviesbrasil.favorites.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.details.models.MovieDetail
import br.com.luishenrique.moviesbrasil.utils.BASE_IMAGE
import br.com.luishenrique.moviesbrasil.utils.setImage

class AdapterFavoritesMovie(
    private val listenerMovie: ListenerMovie
): RecyclerView.Adapter<AdapterFavoritesMovie.MovieViewHolder>() {

    var movies: List<MovieDetail> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class MovieViewHolder(
        private val view: View
    ): RecyclerView.ViewHolder(view) {

        private val title: TextView = itemView.findViewById(R.id.tv_title_item_movie)
        private val thumbnail: ImageView = itemView.findViewById(R.id.iv_thumbnail_item_movie)

        fun bind(movie: MovieDetail) {
            title.text = movie.originalTitle

            setImage(
                thumbnail,
                view.context,
                BASE_IMAGE + movie.backdropPath
            )

            view.setOnClickListener { listenerMovie.onClick(movie) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_favorite_movie,
                parent,
                false
            )
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    interface ListenerMovie {
        fun onClick(movie: MovieDetail)
        fun removeMovie(movie: MovieDetail)
    }
}