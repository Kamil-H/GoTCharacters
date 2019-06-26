package com.kamilh.gotcharacters.views.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kamilh.gotcharacters.base.ScopedViewModel
import com.kamilh.gotcharacters.custom_views.ItemView
import com.kamilh.gotcharacters.data.Character
import com.kamilh.gotcharacters.data.Navigation
import com.kamilh.gotcharacters.data.PaginationRequest
import com.kamilh.gotcharacters.data.PaginationResponse
import com.kamilh.gotcharacters.data.mapper.CharacterToItemView
import com.kamilh.gotcharacters.di.AppEventBus
import com.kamilh.gotcharacters.interactors.GetCharacters
import com.kamilh.gotcharacters.repository.Resource
import com.kamilh.gotcharacters.util.AppDispatchers
import com.kamilh.gotcharacters.util.ResourceProvider
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    appDispatchers: AppDispatchers,
    private val appEventBus: AppEventBus,
    private val resourceProvider: ResourceProvider,
    private val getCharacters: GetCharacters,
    private val characterToItemView: CharacterToItemView
) : ScopedViewModel(appDispatchers) {

    private val _isLoading = MutableLiveData<Boolean>()
    private val _items = MutableLiveData<List<ItemView.Configuration>>()

    val isLoading: LiveData<Boolean> = _isLoading
    val items: LiveData<List<ItemView.Configuration>> = _items

    private val firstPage = PaginationRequest(page = 1, list = listOf<Character>())
    private var response: PaginationResponse<Character>? = null

    init {
        load(firstPage)
    }

    private fun load(request: PaginationRequest<Character>) {
        scope.launch {
            updateUi { _isLoading.value = true }
            val resource = getCharacters(request)
            updateUi { _isLoading.value = false }
            when (resource) {
                is Resource.Data -> onResponse(resource.result)
                is Resource.Error -> updateUi { appEventBus.value = handle(resourceProvider, resource.repositoryError) }
            }
        }
    }

    private suspend fun onResponse(response: PaginationResponse<Character>) {
        this.response = response
        val items = response.list.map { characterToItemView.map(it) }
        updateUi {
            _items.value = items
        }
    }

    fun onItemClicked(item: ItemView.Configuration) {
        response?.list?.firstOrNull { it.id == item.id }?.let {
            appEventBus.value = Navigation.Main.DetailsRequested(it.name)
        }
    }

    fun onLoadMore() {
        load(response?.run { if (canLoadMore) nextPage else firstPage } ?: firstPage)
    }
}
