package com.kamilh.gotcharacters.views.main

import androidx.lifecycle.MutableLiveData
import com.kamilh.gotcharacters.data.Navigation
import com.kamilh.gotcharacters.di.ActivityScope
import com.kamilh.gotcharacters.util.navigation.Navigator
import com.kamilh.gotcharacters.util.navigation.StackSizeProvider
import com.kamilh.gotcharacters.views.character_details.CharacterDetailsFragment
import com.kamilh.gotcharacters.views.characters.CharactersFragment
import javax.inject.Inject

@ActivityScope
class MainNavigationController @Inject constructor(
    private val navigator: Navigator
) : StackSizeProvider {

    override val stackSize = MutableLiveData<Int>()

    init {
        navigator.stackSizeChangeListener = object : Navigator.StackSizeChangeListener {
            override fun onStackSizeChanged(size: Int) {
                stackSize.value = size
            }
        }
    }

    fun handle(main: Navigation.Main) =
        when (main) {
            Navigation.Main.OpenRequested -> TODO()
            Navigation.Main.CharactersRequested -> navigator.push(CharactersFragment.instance())
            Navigation.Main.BackButtonClicked -> navigator.pop()
            is Navigation.Main.DetailsRequested -> navigator.push(
                CharacterDetailsFragment.instance(
                    CharacterDetailsFragment.Arguments(main.name)
                )
            )
        }
}
