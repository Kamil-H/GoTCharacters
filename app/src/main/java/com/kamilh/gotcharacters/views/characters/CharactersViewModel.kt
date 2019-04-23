package com.kamilh.gotcharacters.views.characters

import com.kamilh.gotcharacters.base.ScopedViewModel
import com.kamilh.gotcharacters.di.AppEventBus
import com.kamilh.gotcharacters.interactors.GetCharacters
import com.kamilh.gotcharacters.util.AppDispatchers
import com.kamilh.gotcharacters.util.ResourceProvider
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    appDispatchers: AppDispatchers,
    private val appEventBus: AppEventBus,
    private val resourceProvider: ResourceProvider,
    private val getCharacters: GetCharacters
) : ScopedViewModel(appDispatchers) {

}
