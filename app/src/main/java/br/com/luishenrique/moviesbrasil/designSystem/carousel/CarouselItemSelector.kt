package br.com.luishenrique.moviesbrasil.designSystem.carousel

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import br.com.luishenrique.moviesbrasil.R

class CarouselItemSelector @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    private val item by lazy { findViewById<View>(R.id.item_carousel_component_selector) }

    init {
        LayoutInflater.from(context).inflate(R.layout.item_carousel_selector, this, true)
        deselect()
    }

    fun select() {
        val color = Color.WHITE
        item.backgroundTintList = ColorStateList.valueOf(color)
    }

    fun deselect() {
        val color = Color.GRAY
        item.backgroundTintList = ColorStateList.valueOf(color)
    }
}