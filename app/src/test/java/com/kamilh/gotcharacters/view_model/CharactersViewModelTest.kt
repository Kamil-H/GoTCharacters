package com.kamilh.gotcharacters.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.kamilh.gotcharacters.data.Character
import com.kamilh.gotcharacters.data.PaginationRequest
import com.kamilh.gotcharacters.data.mapper.CharacterToItemView
import com.kamilh.gotcharacters.di.AppEventBus
import com.kamilh.gotcharacters.extensions.mock
import com.kamilh.gotcharacters.extensions.whenever
import com.kamilh.gotcharacters.interactors.GetCharacters
import com.kamilh.gotcharacters.util.AppDispatchers
import com.kamilh.gotcharacters.util.ResourceProvider
import com.kamilh.gotcharacters.views.characters.CharactersViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito

class CharactersViewModelTest {

    @Rule
    @JvmField
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private val appDispatchers = AppDispatchers(
        io = Dispatchers.Unconfined,
        computation = Dispatchers.Unconfined,
        main = Dispatchers.Unconfined
    )
    private val appEventBus = mock<AppEventBus>()
    private val resourceProvider = mock<ResourceProvider>()
    private val getCharacters = mock<GetCharacters>()
    private val characterToItemView = mock<CharacterToItemView>()

    private val charactersViewModel by lazy { CharactersViewModel(
        appDispatchers,
        appEventBus,
        resourceProvider,
        getCharacters,
        characterToItemView)
    }

    @Test
    fun `CharactersViewModel test if isLoading last value is false`() = runBlocking {
        val observer = mock<Observer<Boolean>>()
        val request = PaginationRequest<Character>(page = 1, pageSize = 20, list = listOf())

        whenever(getCharacters.invoke(request)).thenReturn(any())

        charactersViewModel.onLoadMore()

        charactersViewModel.isLoading.observeForever(observer)

        Mockito.verify(observer).onChanged(false)
    }
}
