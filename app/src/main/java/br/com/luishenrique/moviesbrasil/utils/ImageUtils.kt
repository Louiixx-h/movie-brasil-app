package br.com.luishenrique.moviesbrasil.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

fun setImage(image: ImageView, context: Context, path: String) {
    Glide.with(context)
        .load(path)
        .into(image)
}
