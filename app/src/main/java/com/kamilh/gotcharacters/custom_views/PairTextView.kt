package com.kamilh.gotcharacters.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.kamilh.gotcharacters.R
import kotlinx.android.synthetic.main.view_pair_text.view.*

class PairTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, configuration: Configuration? = null
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.view_pair_text, this)

        orientation = HORIZONTAL

        configuration?.let(this::setUp)
    }

    fun setUp(configuration: Configuration) {
        leftTextView.text = configuration.left
        rightTextView.text = configuration.right
    }

    data class Configuration(
        val left: String,
        val right: String? = null
    ) {
        fun toView(context: Context) = PairTextView(context = context, configuration = this)
    }
}
