package br.com.luishenrique.moviesbrasil.utils

import java.text.DecimalFormat

fun Double.getRating() : Float {
    val def = DecimalFormat("0.0")
    return def.format(this).toFloat()
}