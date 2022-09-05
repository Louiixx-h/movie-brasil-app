package br.com.luishenrique.moviesbrasil.presentation.components

import android.content.Context
import br.com.luishenrique.moviesbrasil.data.models.Widget

interface Component {
    fun initComponent(context: Context)
    fun setInfo(widget: Widget)
}
