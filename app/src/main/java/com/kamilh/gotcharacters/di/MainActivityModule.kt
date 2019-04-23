package com.kamilh.gotcharacters.di

import androidx.appcompat.app.AppCompatActivity
import com.kamilh.gotcharacters.data.AppEvent
import com.kamilh.gotcharacters.views.main.MainActivity
import com.kamilh.gotcharacters.util.SingleLiveEvent
import dagger.Module
import dagger.Provides

typealias AppEventBus = SingleLiveEvent<AppEvent>

@Module
object MainActivityModule {

    @Provides @JvmStatic @ActivityScope
    fun provideAppEventBus() = AppEventBus()

    @Provides @JvmStatic @ActivityScope
    fun provideActivity(mainActivity: MainActivity): AppCompatActivity = mainActivity
}
