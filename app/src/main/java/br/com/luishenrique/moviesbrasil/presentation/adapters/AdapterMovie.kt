package br.com.luishenrique.moviesbrasil.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.data.models.Movie
import br.com.luishenrique.moviesbrasil.utils.BASE_IMAGE
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*

class AdapterMovie(
    private val movies: List<Movie>,
    private val onClickItem: (Movie) -> Unit
): RecyclerView.Adapter<AdapterMovie.MovieViewHolder>() {

    inner class MovieViewHolder(
        private val view: View
    ): RecyclerView.ViewHolder(view) {

        fun bind(movie: Movie) {
            itemView.tv_title_item_movie.text = movie.title
            itemView.tv_vote_item_movie.text = movie.voteAverage

            Glide.with(view.context)
                .load(BASE_IMAGE+movie.posterPath)
                .into(itemView.iv_thumbnail_item_movie)

            onClickItem.invoke(movie)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterMovie.MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_movie,
                parent,
                false
            )
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterMovie.MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}