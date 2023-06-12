package br.com.luishenrique.moviesbrasil.details.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.details.models.SimilarMovie
import br.com.luishenrique.moviesbrasil.utils.setImage

class SimilarMovieAdapter(
    private val listenerMovie: ListenerMovie
): RecyclerView.Adapter<SimilarMovieAdapter.ViewHolder>() {

    private var items: MutableList<SimilarMovie> = mutableListOf()

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val itemContainer: View = view.findViewById(R.id.similar_movie_item_container)
        private val banner: ImageView = view.findViewById(R.id.iv_thumbnail_similar_item_movie)

        fun bind(movie: SimilarMovie) {
            setImage(banner, view.context, movie.posterPath)

            itemContainer.setOnClickListener {
                listenerMovie.onClick(movie)
            }
        }
    }

    fun setItems(items: List<SimilarMovie>) {
        this.items = items.toMutableList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_movie_short, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    interface ListenerMovie {
        fun onClick(movie: SimilarMovie)
    }
}
