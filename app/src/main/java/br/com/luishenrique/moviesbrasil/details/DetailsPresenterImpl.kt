package br.com.luishenrique.moviesbrasil.details

import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.data.models.Detail
import com.google.gson.Gson

class DetailsPresenterImpl(private val view: DetailsContract.View) : DetailsContract.Presenter {

    private lateinit var detail: Detail

    override fun getDetail() {
        val json = view.context()
            .resources
            .openRawResource(R.raw.detail)
            .bufferedReader()
            .use { it.readText() }

        detail = Gson().fromJson(json, Detail::class.java)

        view.render(detail)
    }
}
