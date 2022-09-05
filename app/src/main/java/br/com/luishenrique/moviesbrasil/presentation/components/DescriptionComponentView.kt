package br.com.luishenrique.moviesbrasil.presentation.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.data.models.Widget
import br.com.luishenrique.moviesbrasil.presentation.view.detail.DetailsFragment

class DescriptionComponentView @JvmOverloads constructor(
    ctx: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(ctx, attrs, defStyleAttr), Component {

    private lateinit var mTitle: TextView
    private lateinit var mDescription: TextView

    init {
        initComponent(context)
    }

    override fun initComponent(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.component_description, this)

        mTitle = findViewById(R.id.descriptionTitle)
        mDescription = findViewById(R.id.descriptionDescription)
    }

    override fun setInfo(widget: Widget) {
        mTitle.text = widget.title
        mDescription.text = widget.description
    }

    companion object {
        @JvmStatic
        fun newInstance(context: Context, widget: Widget) : View {
            return DescriptionComponentView(context).apply {
                setInfo(widget)
            }
        }
    }
}