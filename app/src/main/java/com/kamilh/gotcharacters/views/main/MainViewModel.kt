package com.kamilh.gotcharacters.views.main

import androidx.lifecycle.LiveData
import com.kamilh.gotcharacters.base.ScopedViewModel
import com.kamilh.gotcharacters.data.*
import com.kamilh.gotcharacters.util.AppDispatchers
import com.kamilh.gotcharacters.util.SingleLiveEvent
import javax.inject.Inject

class MainViewModel @Inject constructor(
    appDispatchers: AppDispatchers
) : ScopedViewModel(appDispatchers) {

    private val _alert = SingleLiveEvent<Alert>()
    private val _shouldPop = SingleLiveEvent<Unit>()
    private val _direction = SingleLiveEvent<Direction>()

    val alert: LiveData<Alert> = _alert
    val shouldPop: LiveData<Unit> = _shouldPop
    val direction: LiveData<Direction> = _direction

    fun onAppEvent(appEvent: AppEvent) {
        when (appEvent) {
            is Alert -> _alert.value = appEvent
            is Navigation.Main -> {
                appEvent.action.let { navAction ->
                    when (navAction) {
                        is NavAction.Pop -> _shouldPop.value = Unit
                        is NavAction.Push -> _direction.value = navAction.direction
                    }
                }
            }
        }
    }
}
