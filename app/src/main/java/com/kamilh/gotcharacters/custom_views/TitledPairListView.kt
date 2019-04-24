package com.kamilh.gotcharacters.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.kamilh.gotcharacters.R
import kotlinx.android.synthetic.main.view_titled_pair_list.view.*

class TitledPairListView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, configuration: Configuration? = null
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.view_titled_pair_list, this)

        configuration?.let(this::setUp)
    }

    fun setUp(configuration: Configuration) {
        progressBar.isVisible = configuration.state == State.Loading
        contentContainer.isVisible = configuration.state == State.Data

        titleTextView.text = configuration.title

        pairsContainer.removeAllViews()
        configuration.pairs.map { it.toView(context) }.forEach { pairsContainer.addView(it) }
    }

    data class Configuration(
        val title: String,
        val state: State = State.Data,
        val pairs: List<PairTextView.Configuration>
    ) {

        companion object {
            fun loading() = Configuration(
                title = "",
                state = State.Loading,
                pairs = listOf()
            )
        }
    }

    enum class State { Loading, Data }
}
