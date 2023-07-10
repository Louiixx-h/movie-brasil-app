package br.com.luishenrique.moviesbrasil.search.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.luishenrique.moviesbrasil.BuildConfig
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.search.models.Movie
import br.com.luishenrique.moviesbrasil.utils.setImage

class SearchMovieAdapter(
    private val listenerMovie: ListenerMovie
): RecyclerView.Adapter<SearchMovieAdapter.MovieViewHolder>() {

    var movies: List<Movie> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class MovieViewHolder(
        private val view: View
    ): RecyclerView.ViewHolder(view) {

        private val thumbnail: ImageView = itemView.findViewById(R.id.iv_thumbnail_item_movie)

        fun bind(movie: Movie) {
            setImage(thumbnail, view.context, BuildConfig.BASE_URL_IMAGE + movie.posterPath)
            view.setOnClickListener { listenerMovie.onClick(movie) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_movie,parent,false)
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