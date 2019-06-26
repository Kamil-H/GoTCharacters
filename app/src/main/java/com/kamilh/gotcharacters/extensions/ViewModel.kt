package com.kamilh.gotcharacters.extensions

import androidx.lifecycle.ViewModel
import com.kamilh.gotcharacters.custom_views.AppToolbar

fun ViewModel.appToolbarConfiguration(stackSize: Int, title: String, onBackCallback: () -> Unit) = AppToolbar.Configuration(
    title = title,
    navigationButtonCallback = navigationCallback(stackSize, onBackCallback)
)

private fun navigationCallback(stackSize: Int, onBackCallback: () -> Unit): (() -> (Unit))? {
    return if (stackSize > 1) {
        onBackCallback
    } else null
}
