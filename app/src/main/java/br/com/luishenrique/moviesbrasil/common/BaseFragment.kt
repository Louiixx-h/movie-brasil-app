package br.com.luishenrique.moviesbrasil.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import br.com.luishenrique.moviesbrasil.R

abstract class BaseFragment<VBinding : ViewBinding> : Fragment(), ErrorScreenListener {

    protected lateinit var binding: VBinding
    protected abstract fun getViewBinding(): VBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
    }

    private fun init() {
        binding = getViewBinding()
    }

    open fun setUpViews(view: View) {}

    override fun onClickPrimaryButton() = Unit

    protected fun errorScreen() {
        with(parentFragmentManager.beginTransaction()) {
            add(R.id.fragmentContainer, ErrorScreenFragment(this@BaseFragment), "error_screen")
            commit()
        }
    }
}