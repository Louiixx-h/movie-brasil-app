package br.com.luishenrique.moviesbrasil.details.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.data.models.Widget

class ButtonComponentView @JvmOverloads constructor(
    ctx: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(ctx, attrs, defStyleAttr), Component {

    private lateinit var mButton: Button

    init {
        initComponent(ctx)
    }

    override fun initComponent(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.component_button, this)

        mButton = findViewById(R.id.buttonButton)
    }

    override fun setInfo(widget: Widget) {
        mButton.text = widget.title
    }

    companion object {
        @JvmStatic
        fun newInstance(ctx: Context, widget: Widget) : View {
            return ButtonComponentView(ctx).apply {
                setInfo(widget)
            }
        }
    }
}