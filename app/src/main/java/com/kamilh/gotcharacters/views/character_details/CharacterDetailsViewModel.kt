package com.kamilh.gotcharacters.views.character_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kamilh.gotcharacters.base.ScopedViewModel
import com.kamilh.gotcharacters.custom_views.TitledPairListView
import com.kamilh.gotcharacters.data.Character
import com.kamilh.gotcharacters.data.mapper.BookToTitledPairListView
import com.kamilh.gotcharacters.data.mapper.CharacterToGeneralInfoTitledPairListView
import com.kamilh.gotcharacters.data.mapper.CharacterToPlayedByTitledPairListView
import com.kamilh.gotcharacters.data.mapper.CharacterToTvSeriesTitledPairListView
import com.kamilh.gotcharacters.di.AppEventBus
import com.kamilh.gotcharacters.interactors.GetBooksById
import com.kamilh.gotcharacters.interactors.GetCharacterByName
import com.kamilh.gotcharacters.repository.Resource
import com.kamilh.gotcharacters.util.AppDispatchers
import com.kamilh.gotcharacters.util.ResourceProvider
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDetailsViewModel @Inject constructor(
    appDispatchers: AppDispatchers,
    private val appEventBus: AppEventBus,
    private val resourceProvider: ResourceProvider,
    private val getCharacterByName: GetCharacterByName,
    private val getBooksById: GetBooksById,
    private val characterToGeneralInfoTitledPairListView: CharacterToGeneralInfoTitledPairListView,
    private val characterToTvSeriesTitledPairListView: CharacterToTvSeriesTitledPairListView,
    private val characterToPlayedByTitledPairListView: CharacterToPlayedByTitledPairListView,
    private val bookToTitledPairListView: BookToTitledPairListView
) : ScopedViewModel(appDispatchers) {

    private val _isLoading = MutableLiveData<Boolean>()
    private val _generalInfo = MutableLiveData<TitledPairListView.Configuration>()
    private val _tvSeries = MutableLiveData<TitledPairListView.Configuration>()
    private val _playedBy = MutableLiveData<TitledPairListView.Configuration>()
    private val _books = MutableLiveData<TitledPairListView.Configuration>()

    val isLoading: LiveData<Boolean> = _isLoading
    val generalInfo: LiveData<TitledPairListView.Configuration> = _generalInfo
    val tvSeries: LiveData<TitledPairListView.Configuration> = _tvSeries
    val playedBy: LiveData<TitledPairListView.Configuration> = _playedBy
    val books: LiveData<TitledPairListView.Configuration> = _books

    fun onArguments(arguments: CharacterDetailsFragment.Arguments) {
        getCharacter(arguments.name)
    }

    private fun getCharacter(name: String) {
        scope.launch {
            updateUi { _isLoading.value = true }
            val resource = getCharacterByName(GetCharacterByName.Params(name))
            updateUi { _isLoading.value = false }
            when (resource) {
                is Resource.Data -> onResponse(resource.result)
                is Resource.Error -> updateUi { appEventBus.value = handle(resourceProvider, resource.repositoryError) }
            }
        }
    }

    private suspend fun onResponse(response: Character) {
        updateUi {
            _generalInfo.value = characterToGeneralInfoTitledPairListView.map(response)

            _tvSeries.value = characterToTvSeriesTitledPairListView.map(response)

            _playedBy.value = characterToPlayedByTitledPairListView.map(response)

            _books.value = TitledPairListView.Configuration.loading()
        }

        when (val booksResource = getBooksById(GetBooksById.Params(response.books))) {
            is Resource.Data -> updateUi { _books.value = bookToTitledPairListView.map(booksResource.result) }
            is Resource.Error -> updateUi { appEventBus.value = handle(resourceProvider, booksResource.repositoryError) }
        }
    }
}
