package br.com.luishenrique.moviesbrasil.common

import androidx.lifecycle.ViewModel
import br.com.luishenrique.moviesbrasil.network.Network
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseViewModel : ViewModel(), KoinComponent {

    protected val network: Network by inject()
}