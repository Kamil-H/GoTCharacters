package com.kamilh.gotcharacters.views.character_details

import com.kamilh.gotcharacters.base.ScopedViewModel
import com.kamilh.gotcharacters.di.AppEventBus
import com.kamilh.gotcharacters.util.AppDispatchers
import com.kamilh.gotcharacters.util.ResourceProvider
import javax.inject.Inject

class CharacterDetailsViewModel @Inject constructor(
    appDispatchers: AppDispatchers,
    private val appEventBus: AppEventBus,
    private val resourceProvider: ResourceProvider
) : ScopedViewModel(appDispatchers) {

}
