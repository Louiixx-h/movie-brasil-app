package br.com.luishenrique.moviesbrasil.details.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.data.models.Widget

class HeaderComponentView @JvmOverloads constructor(
    ctx: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(ctx, attrs, defStyleAttr), Component {

    private lateinit var mTitle: TextView
    private lateinit var mImage: ImageView

    init {
        initComponent(context)
    }

    override fun initComponent(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.component_header, this)

        mTitle = findViewById(R.id.headerTitle)
        mImage = findViewById(R.id.headerImage)
    }

    override fun setInfo(widget: Widget) {
        mTitle.text = widget.title
    }

    companion object {
        @JvmStatic
        fun newInstance(context: Context, widget: Widget) : View {
            return HeaderComponentView(context).apply {
                setInfo(widget)
            }
        }
    }
}