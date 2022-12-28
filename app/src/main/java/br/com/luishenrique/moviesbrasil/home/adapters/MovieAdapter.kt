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

class MovieAdapter(
    private val listenerMovie: ListenerMovie
): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    var movies: List<Movie> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class MovieViewHolder(
        private val view: View
    ): RecyclerView.ViewHolder(view) {

        private val thumbnail: ImageView = itemView.findViewById(R.id.iv_thumbnail_item_movie)

        fun bind(movie: Movie) {
            setImage(
                thumbnail,
                view.context,
                BASE_IMAGE + movie.posterPath
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