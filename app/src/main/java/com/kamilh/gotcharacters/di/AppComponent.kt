package com.kamilh.gotcharacters.di

import com.kamilh.gotcharacters.GoTCharacters
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityModule::class])
interface AppComponent : AndroidInjector<GoTCharacters> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: GoTCharacters): Builder
        fun build(): AppComponent
    }

    override fun inject(app: GoTCharacters)
}
