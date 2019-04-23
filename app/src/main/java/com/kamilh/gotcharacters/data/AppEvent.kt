package com.kamilh.gotcharacters.data

import com.kamilh.gotcharacters.R
import com.kamilh.gotcharacters.views.character_details.CharacterDetailsFragment

sealed class AppEvent

data class Alert(
    val title: String,
    val message: String,
    val isCancelable: Boolean = true,
    val positiveButton: Action? = null,
    val negativeButton: Action? = null,
    val cancelAction: (() -> (Unit))? = null
) : AppEvent() {

    fun addOkAction(callback: (() -> Unit) = {}): Alert {
        return this.copy(positiveButton = Action("OK", callback))
    }

    data class Action(
        val title: String,
        val callback: () -> (Unit)
    )
}

sealed class Navigation : AppEvent() {

    abstract val action: NavAction

    sealed class Main : Navigation() {

        class DetailsRequested(private val name: String) : Main() {
            override val action: NavAction
                get() = NavAction.Push(
                    Direction(
                        R.id.action_charactersFragment_to_characterDetailsFragment,
                        args = CharacterDetailsFragment.Arguments(name).toBundle()
                    )
                )
        }
    }
}
