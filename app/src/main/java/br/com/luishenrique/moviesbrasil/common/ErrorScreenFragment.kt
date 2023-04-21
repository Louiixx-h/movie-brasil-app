package br.com.luishenrique.moviesbrasil.common

import br.com.luishenrique.moviesbrasil.databinding.FragmentErrorScreenBinding


class ErrorScreenFragment(
    private val listener: ErrorScreenListener
) : BaseFragment<FragmentErrorScreenBinding>() {

    override fun getViewBinding() = FragmentErrorScreenBinding.inflate(layoutInflater)

    override fun setUpViews() {
        binding.errorButton.setOnClickListener { listener.onClickPrimaryButton() }
    }
}