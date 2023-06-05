package br.com.luishenrique.moviesbrasil.designSystem.carousel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.databinding.ItemCarouselBinding
import com.bumptech.glide.Glide
import br.com.luishenrique.moviesbrasil.designSystem.carousel.CarouselViewHolder.Companion.customDiffUtils

class CarouselAdapter : ListAdapter<String, CarouselViewHolder>(customDiffUtils) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemCarouselBinding>(
            layoutInflater,
            R.layout.item_carousel,
            parent,
            false
        )
        return CarouselViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(getItem(position))
            .into(holder.binding.mtvItem)
    }
}