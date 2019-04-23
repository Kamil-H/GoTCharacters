package com.kamilh.gotcharacters.di

import com.kamilh.gotcharacters.views.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            MainActivityModule::class,
            MainActivityFragmentsModule::class,
            ViewModelModule::class
        ])
    internal abstract fun contributeMainActivity(): MainActivity
}
