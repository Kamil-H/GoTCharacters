package com.kamilh.gotcharacters.base

import androidx.lifecycle.ViewModel
import com.kamilh.gotcharacters.util.AppDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.withContext

abstract class ScopedViewModel constructor(
    protected val appDispatchers: AppDispatchers
) : ViewModel() {

    private val job = SupervisorJob()

    val scope: CoroutineScope = CoroutineScope(appDispatchers.io + job)

    protected suspend fun updateUi(block: () -> Unit) = withContext(appDispatchers.main) { block() }

    override fun onCleared() {
        super.onCleared()
        scope.coroutineContext.cancelChildren()
    }
}
