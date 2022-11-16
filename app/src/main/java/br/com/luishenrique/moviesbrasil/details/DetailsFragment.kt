package br.com.luishenrique.moviesbrasil.details

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.data.models.Detail
import br.com.luishenrique.moviesbrasil.data.models.TypeDetailEnum
import br.com.luishenrique.moviesbrasil.details.components.ButtonComponentView
import br.com.luishenrique.moviesbrasil.details.components.DescriptionComponentView
import br.com.luishenrique.moviesbrasil.details.components.HeaderComponentView

class DetailsFragment : Fragment(R.layout.fragment_details), DetailsContract.View {

    private val presenter: DetailsContract.Presenter by lazy { DetailsPresenterImpl(this) }

    private lateinit var containerDetail: LinearLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun init() {
        containerDetail = requireActivity().findViewById(R.id.containerDetail)
        presenter.getDetail()
    }

    override fun render(detail: Detail) {
        detail.widgets?.forEach {
            val component: View = when(it.type) {
                TypeDetailEnum.HEADER -> HeaderComponentView.newInstance(requireContext(), it)
                TypeDetailEnum.DIVISOR -> TODO()
                TypeDetailEnum.DESCRIPTION -> DescriptionComponentView.newInstance(requireContext(), it)
                TypeDetailEnum.BUTTON -> ButtonComponentView.newInstance(requireContext(), it)
                else -> {
                    throw Exception("Erro: Type não encontrado!")
                }
            }

            containerDetail.addView(component)
        }
    }

    override fun context(): Context {
        return requireContext()
    }

    companion object {
        @JvmStatic
        fun newInstance() = DetailsFragment()
    }
}
