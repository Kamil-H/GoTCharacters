package com.kamilh.gotcharacters.util.navigation

import androidx.lifecycle.LiveData

interface StackSizeProvider {

    val stackSize: LiveData<Int>
}
