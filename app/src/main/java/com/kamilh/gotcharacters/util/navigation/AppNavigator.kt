package com.kamilh.gotcharacters.util.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.kamilh.gotcharacters.R
import javax.inject.Inject

class AppNavigator @Inject constructor(
    private val fragmentOwner: FragmentOwner
) : Navigator {

    override var stackSize: Int = 0

    override var stackSizeChangeListener: Navigator.StackSizeChangeListener? = null

    init {
        fragmentOwner.fragmentManager.addOnBackStackChangedListener {
            stackSize = fragmentOwner.fragmentManager.backStackEntryCount
            stackSizeChangeListener?.onStackSizeChanged(stackSize)
        }
    }

    override fun push(fragment: Fragment) {
        addToStack(fragment, Animation.Push)
    }

    override fun replace(fragment: Fragment) {
        fragmentOwner.fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        addToStack(fragment, Animation.Replace)
    }

    override fun pop() {
        fragmentOwner.fragmentManager.popBackStack()
    }

    private fun addToStack(fragment: Fragment, animation: Animation) {
        fragmentOwner.fragmentManager
            .beginTransaction()
            .setCustomAnimations(animation.enter, animation.exit, animation.popEnter, animation.popExit)
            .addToBackStack(null)
            .replace(fragmentOwner.container, fragment)
            .commit()
    }

    private sealed class Animation constructor(
        val enter: Int,
        val exit: Int,
        val popEnter: Int,
        val popExit: Int
    ) {

        object Push : Animation(
            enter = R.anim.slide_in_from_right,
            exit = R.anim.slide_out_to_left,
            popEnter = R.anim.slide_in_from_left,
            popExit = R.anim.slide_out_to_right
        )

        object Replace : Animation(
            enter = android.R.anim.fade_in,
            exit = android.R.anim.fade_out,
            popEnter = android.R.anim.fade_in,
            popExit = android.R.anim.fade_out
        )
    }
}
