package com.kamilh.gotcharacters.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.AppBarLayout
import com.kamilh.gotcharacters.R
import kotlinx.android.synthetic.main.toolbar_app.view.*

class AppToolbar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppBarLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.toolbar_app, this)
    }

    fun setUp(configuration: Configuration) {
        toolbar.title = configuration.title

        toolbar.navigationIcon = navigationIcon(configuration.navigationButtonCallback)
        configuration.navigationButtonCallback?.let { navigationButtonCallback ->
            toolbar.setNavigationOnClickListener {
                navigationButtonCallback()
            }
        }
    }

    private fun navigationIcon(callback: (() -> Unit)?) =
        if (callback != null) {
            ContextCompat.getDrawable(context, R.drawable.ic_arrow_back_white)
        } else {
            null
        }

    data class Configuration(
        val title: String,
        val navigationButtonCallback: (() -> Unit)? = null
    )
}
