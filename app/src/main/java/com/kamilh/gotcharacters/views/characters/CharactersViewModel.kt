package com.kamilh.gotcharacters.views.characters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kamilh.gotcharacters.base.ScopedViewModel
import com.kamilh.gotcharacters.data.Character
import com.kamilh.gotcharacters.data.Navigation
import com.kamilh.gotcharacters.data.PaginationRequest
import com.kamilh.gotcharacters.data.PaginationResponse
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
    private val getCharacters: GetCharacters
) : ScopedViewModel(appDispatchers) {

    private val _isLoading = MutableLiveData<Boolean>()

    val isLoading: LiveData<Boolean> = _isLoading

    private val list = mutableListOf<Character>()
    private val firstPage = PaginationRequest(page = 0)
    private var response: PaginationResponse<Character>? = null

    init {
        load(firstPage)
    }

    private fun load(request: PaginationRequest) {
        scope.launch {
            updateUi { _isLoading.value = true }
            val resource = getCharacters.invoke(request)
            updateUi { _isLoading.value = false }
            when (resource) {
                is Resource.Data -> onResponse(resource.result)
                is Resource.Error -> print(resource.repositoryError.message(resourceProvider))
            }
        }
    }

    private suspend fun onResponse(response: PaginationResponse<Character>) {
        this.response = response
        list.addAll(response.list)
        Log.i("onResponse", list.size.toString())
        updateUi {

        }
    }

    fun onRowClicked(i: Int) {
        val character = list.getOrNull(i)
        if (character != null) {
            appEventBus.value = Navigation.Main.DetailsRequested(character.name)
        } else {
            // TODO: Show error
        }
    }

    fun onLoadMore() {
        load(response?.run { if (canLoadMore) nextPage else firstPage } ?: firstPage)
    }
}
