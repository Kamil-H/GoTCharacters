package com.kamilh.gotcharacters.data

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavDirections

sealed class NavAction {

    object Pop : NavAction()
    class Push(val direction: Direction) : NavAction()
}

data class Direction(
    @IdRes val resId: Int,
    val args: Bundle? = null
) : NavDirections {

    override fun getArguments(): Bundle = args ?: Bundle.EMPTY

    override fun getActionId() = resId
}
