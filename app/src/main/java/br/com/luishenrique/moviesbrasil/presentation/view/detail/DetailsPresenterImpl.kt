package br.com.luishenrique.moviesbrasil.presentation.view.detail

import br.com.luishenrique.moviesbrasil.data.models.Detail
import com.google.gson.Gson

class DetailsPresenterImpl(private val view: DetailsContract.View) : DetailsContract.Presenter {

    private val detail: Detail by lazy { Gson().fromJson("", Detail::class.java) }

    override fun getDetail() {
        view.render(detail)
    }
}
