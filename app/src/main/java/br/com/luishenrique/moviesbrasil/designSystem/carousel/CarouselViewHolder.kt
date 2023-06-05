package br.com.luishenrique.moviesbrasil.designSystem.carousel

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.luishenrique.moviesbrasil.databinding.ItemCarouselBinding

class CarouselViewHolder(val binding: ItemCarouselBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        val customDiffUtils = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }
}