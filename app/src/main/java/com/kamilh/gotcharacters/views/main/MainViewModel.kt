package com.kamilh.gotcharacters.views.main

import androidx.lifecycle.LiveData
import com.kamilh.gotcharacters.base.ScopedViewModel
import com.kamilh.gotcharacters.data.Alert
import com.kamilh.gotcharacters.data.AppEvent
import com.kamilh.gotcharacters.data.Navigation
import com.kamilh.gotcharacters.util.AppDispatchers
import com.kamilh.gotcharacters.util.SingleLiveEvent
import javax.inject.Inject

class MainViewModel @Inject constructor(appDispatchers: AppDispatchers) : ScopedViewModel(appDispatchers) {

    private val _alert = SingleLiveEvent<Alert>()
    private val _mainNavigationEvent = SingleLiveEvent<Navigation.Main>()

    val alert: LiveData<Alert> = _alert
    val mainNavigationEvent: LiveData<Navigation.Main> = _mainNavigationEvent

    fun onAppEvent(appEvent: AppEvent) {
        when (appEvent) {
            is Alert -> _alert.value = appEvent
            is Navigation.Main -> _mainNavigationEvent.value = appEvent
        }
    }

    fun checkState() {
        onAppEvent(Navigation.Main.CharactersRequested)
    }
}
