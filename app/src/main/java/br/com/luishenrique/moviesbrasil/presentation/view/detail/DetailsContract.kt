package br.com.luishenrique.moviesbrasil.presentation.view.detail

import br.com.luishenrique.moviesbrasil.data.models.Detail

interface DetailsContract {
    interface View {
        fun init()
        fun render(detail: Detail)
    }

    interface Presenter {
        fun getDetail()
    }
}
