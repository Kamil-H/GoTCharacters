package com.kamilh.gotcharacters.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kamilh.gotcharacters.views.character_details.CharacterDetailsViewModel
import com.kamilh.gotcharacters.views.characters.CharactersViewModel
import com.kamilh.gotcharacters.views.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds @IntoMap @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds @IntoMap @ViewModelKey(CharactersViewModel::class)
    abstract fun bindCharactersViewModel(viewModel: CharactersViewModel): ViewModel

    @Binds @IntoMap @ViewModelKey(CharacterDetailsViewModel::class)
    abstract fun bindCharacterDetailsViewModel(viewModel: CharacterDetailsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
