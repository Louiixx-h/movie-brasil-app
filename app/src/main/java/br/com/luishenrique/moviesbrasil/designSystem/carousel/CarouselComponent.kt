package br.com.luishenrique.moviesbrasil.designSystem.carousel

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import br.com.luishenrique.moviesbrasil.R
import kotlinx.coroutines.*

class CarouselComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    private val viewPager by lazy { findViewById<ViewPager2>(R.id.carousel_component_view_pager) }
    private val selectorContainer by lazy { findViewById<LinearLayoutCompat>(R.id.carousel_component_selector_container) }
    private val adapter by lazy { CarouselAdapter() }
    private var job: Job? = null
    private var list: List<String> = emptyList()
    private var selectors: List<CarouselItemSelector> = emptyList()
    private var delay: Float = 0f
    private var lastPosition: Int = -1

    private val onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

            if (list.isEmpty()) return
            if (lastPosition >= 0) {
                deselectItem()
            }

            job?.cancel()
            setupPagination(position)
        }
    }

    init {
        inflateLayout()
        setupAttr(attrs)
    }

    private fun inflateLayout() {
        LayoutInflater.from(context).inflate(R.layout.component_carousel, this, true)
    }

    private fun setupAttr(attrs: AttributeSet) {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CarouselComponent)
        delay = attributes.getFloat(R.styleable.CarouselComponent_delay, 0f)
        attributes.recycle()
    }

    fun setList(list: List<String>) {
        this.list = list
        setupCarousel()
        setupPagination(0)
    }

    private fun setupCarousel() {
        viewPager.adapter = adapter
        viewPager.registerOnPageChangeCallback(onPageChangeCallback)
        adapter.submitList(list)
        selectors = List(list.size) { CarouselItemSelector(context) }
        selectorContainer.removeAllViews()
        selectors.forEach { selectorContainer.addView(it) }

        if(job != null) {
            job?.cancel()
        }
    }

    private fun setupPagination(position: Int) {
        val lastPosition = list.size-1
        job = MainScope().launch {
            withContext(Dispatchers.Main) {

                selectCurrentPage(position)
                selectItem(position)

                delay(delay.toLong())

                if(position >= lastPosition) {
                    deselectItem()
                    setupPagination(0)
                    return@withContext
                }

                deselectItem()
                setupPagination(position+1)
            }
        }
    }

    private fun selectCurrentPage(position: Int) {
        viewPager.currentItem = position
    }

    private fun selectItem(position: Int) {
        lastPosition = position
        (selectorContainer[position] as CarouselItemSelector).select()
    }

    private fun deselectItem() {
        (selectorContainer[lastPosition] as CarouselItemSelector).deselect()
    }
}