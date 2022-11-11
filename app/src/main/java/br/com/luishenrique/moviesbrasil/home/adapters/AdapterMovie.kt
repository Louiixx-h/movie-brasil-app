package br.com.luishenrique.moviesbrasil.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.home.models.Movie
import br.com.luishenrique.moviesbrasil.utils.BASE_IMAGE
import br.com.luishenrique.moviesbrasil.utils.setImage
import com.bumptech.glide.Glide

class AdapterMovie(
    private val listenerMovie: ListenerMovie
): RecyclerView.Adapter<AdapterMovie.MovieViewHolder>() {

    var movies: List<Movie> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class MovieViewHolder(
        private val view: View
    ): RecyclerView.ViewHolder(view) {

        private val title: TextView = itemView.findViewById(R.id.tv_title_item_movie)
        private val voteAverage: TextView = itemView.findViewById(R.id.tv_vote_item_movie)
        private val thumbnail: ImageView = itemView.findViewById(R.id.iv_thumbnail_item_movie)

        fun bind(movie: Movie) {
            title.text = movie.title
            voteAverage.text = movie.voteAverage

            setImage(
                thumbnail,
                view.context,
                BASE_IMAGE + movie.backdropPath
            )

            listenerMovie.onClick(movie)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_movie,
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
        fun onClick(movie: Movie)
    }
}