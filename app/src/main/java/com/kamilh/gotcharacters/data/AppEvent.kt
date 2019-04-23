package com.kamilh.gotcharacters.data

sealed class AppEvent

class Loading(val inProgress: Boolean): AppEvent() {
    companion object {
        val inProgress = Loading(inProgress = true)
        val finished = Loading(inProgress = false)
    }
}

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

sealed class Navigation : AppEvent()
