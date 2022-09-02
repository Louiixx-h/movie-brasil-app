package br.com.luishenrique.moviesbrasil.presentation.view.detail

import androidx.fragment.app.Fragment
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.data.models.Detail

class DetailsFragment : Fragment(R.layout.fragment_details), DetailsContract.View {

    private val presenter: DetailsContract.Presenter by lazy { DetailsPresenterImpl(this) }

    override fun init() {
        presenter.getDetail()
    }

    override fun render(detail: Detail) {
        detail.widgets?.forEach {



        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = DetailsFragment()
    }
}
