package br.com.luishenrique.moviesbrasil.details

import android.content.Context
import br.com.luishenrique.moviesbrasil.data.models.Detail

interface DetailsContract {
    interface View {
        fun context() : Context
        fun init()
        fun render(detail: Detail)
    }

    interface Presenter {
        fun getDetail()
    }
}
