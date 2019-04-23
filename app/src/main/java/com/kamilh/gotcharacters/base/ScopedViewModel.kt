package com.kamilh.gotcharacters.base

import androidx.lifecycle.ViewModel
import com.kamilh.gotcharacters.util.AppDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren

abstract class ScopedViewModel constructor(
    appDispatchers: AppDispatchers
) : ViewModel() {

    private val job = SupervisorJob()

    val scope: CoroutineScope = CoroutineScope(appDispatchers.io + job)

    override fun onCleared() {
        super.onCleared()
        scope.coroutineContext.cancelChildren()
    }
}
