package com.kamilh.gotcharacters.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.kamilh.gotcharacters.R
import kotlinx.android.synthetic.main.view_item.view.*

class ItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, configuration: Configuration? = null
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.view_item, this)

        orientation = VERTICAL
        background = ContextCompat.getDrawable(context, R.color.colorPrimary)

        configuration?.let(this::setUp)
    }

    fun setUp(configuration: Configuration) {
        subtitleTextView.text = configuration.upperText
        titleTextView.text = configuration.middleText
        smallTextView.text = configuration.bottomText
    }

    data class Configuration(
        val id: Int,
        val upperText: String,
        val middleText: String,
        val bottomText: String
    )
}
