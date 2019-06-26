package com.kamilh.gotcharacters.di

import com.kamilh.gotcharacters.util.navigation.AppNavigator
import com.kamilh.gotcharacters.util.navigation.FragmentOwner
import com.kamilh.gotcharacters.util.navigation.Navigator
import com.kamilh.gotcharacters.util.navigation.StackSizeProvider
import com.kamilh.gotcharacters.views.character_details.CharacterDetailsFragment
import com.kamilh.gotcharacters.views.characters.CharactersFragment
import com.kamilh.gotcharacters.views.main.MainActivity
import com.kamilh.gotcharacters.views.main.MainNavigationController
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentsModule {

    @ContributesAndroidInjector
    internal abstract fun contributeCharactersFragment(): CharactersFragment

    @ContributesAndroidInjector
    internal abstract fun contributeCharacterDetailsFragment(): CharacterDetailsFragment

    @Binds
    internal abstract fun bindsMainFragmentOwner(mainActivity: MainActivity): FragmentOwner

    @Binds
    abstract fun provideNavigator(navigator: AppNavigator): Navigator

    @Binds
    abstract fun bingStackSizeProvider(mainNavigationController: MainNavigationController): StackSizeProvider
}
