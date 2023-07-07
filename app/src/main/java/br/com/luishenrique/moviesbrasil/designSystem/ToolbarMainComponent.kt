package br.com.luishenrique.moviesbrasil.designSystem

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import br.com.luishenrique.moviesbrasil.databinding.ToolbarMainBinding

class ToolbarMainComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyle: Int = 0
) : Toolbar(context, attrs, defStyle) {

    private var binding: ToolbarMainBinding? = null
    private val _binding get() = binding!!

    init {
        inflateLayout()
    }

    private fun inflateLayout() {
        binding = ToolbarMainBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun hideAppIcon() {
        //_binding.appLogo.isVisible = false
    }

    fun showAppIcon() {
        //_binding.appLogo.isVisible = true
    }

    fun setTitle(title: String) {
        _binding.toolbarMain.title = title
    }
}