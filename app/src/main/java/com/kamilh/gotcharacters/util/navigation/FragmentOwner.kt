package com.kamilh.gotcharacters.util.navigation

import androidx.fragment.app.FragmentManager

interface FragmentOwner {

    val container: Int

    val fragmentManager: FragmentManager
}
