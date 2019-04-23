package com.kamilh.gotcharacters.di

import com.kamilh.gotcharacters.views.character_details.CharacterDetailsFragment
import com.kamilh.gotcharacters.views.characters.CharactersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentsModule {

    @ContributesAndroidInjector
    internal abstract fun contributeCharactersFragment(): CharactersFragment

    @ContributesAndroidInjector
    internal abstract fun contributeCharacterDetailsFragment(): CharacterDetailsFragment
}
