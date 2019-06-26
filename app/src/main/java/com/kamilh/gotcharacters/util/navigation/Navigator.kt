package com.kamilh.gotcharacters.util.navigation

import androidx.fragment.app.Fragment

interface Navigator {

    fun push(fragment: Fragment)

    fun replace(fragment: Fragment)

    fun pop()

    var stackSizeChangeListener: StackSizeChangeListener?

    var stackSize: Int

    interface StackSizeChangeListener {
        fun onStackSizeChanged(size: Int)
    }
}
