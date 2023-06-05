package br.com.luishenrique.moviesbrasil.utils

import android.content.Context
import android.widget.ImageView
import androidx.fragment.app.Fragment
import br.com.luishenrique.moviesbrasil.BuildConfig
import com.bumptech.glide.Glide

fun setImage(image: ImageView, context: Context, path: String?) {
    if (path == null) return
    Glide.with(context)
        .load(BuildConfig.BASE_URL_IMAGE + path)
        .into(image)
}

fun Fragment.setImage(image: ImageView, path: String?) {
    if (path == null) return
    Glide.with(requireContext())
        .load(BuildConfig.BASE_URL_IMAGE + path)
        .into(image)
}
