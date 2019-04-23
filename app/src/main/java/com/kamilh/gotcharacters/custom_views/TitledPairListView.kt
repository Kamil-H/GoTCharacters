package com.kamilh.gotcharacters.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.kamilh.gotcharacters.R
import kotlinx.android.synthetic.main.view_titled_pair_list.view.*

class TitledPairListView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, configuration: Configuration? = null
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.view_titled_pair_list, this)

        orientation = VERTICAL

        configuration?.let(this::setUp)
    }

    fun setUp(configuration: Configuration) {
        titleTextView.text = configuration.title

        pairsContainer.removeAllViews()
        configuration.pairs.map { it.toView(context) }.forEach { pairsContainer.addView(it) }
    }

    data class Configuration(
        val title: String,
        val pairs: List<PairTextView.Configuration>
    )
}
