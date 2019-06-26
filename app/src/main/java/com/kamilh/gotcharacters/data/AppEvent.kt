package com.kamilh.gotcharacters.data

sealed class AppEvent

data class Alert(
    val title: String,
    val message: String,
    val isCancelable: Boolean = true,
    val positiveButton: Action? = null,
    val negativeButton: Action? = null,
    val cancelAction: (() -> (Unit))? = null
) : AppEvent() {

    fun addOkAction(callback: (() -> Unit)? = {}): Alert {
        if (callback != null) {
            return this.copy(positiveButton = Action("OK", callback))
        }
        return this.copy(positiveButton = Action("OK") { })
    }

    data class Action(
        val title: String,
        val callback: () -> (Unit)
    )
}

sealed class Navigation : AppEvent() {

    sealed class Main : Navigation() {

        object OpenRequested : Main()
        object CharactersRequested : Main()
        object BackButtonClicked : Main()

        class DetailsRequested(val name: String) : Main()
    }
}
