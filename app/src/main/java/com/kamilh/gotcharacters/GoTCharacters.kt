package com.kamilh.gotcharacters

import com.facebook.stetho.Stetho
import com.kamilh.gotcharacters.di.DaggerAppComponent
import dagger.android.support.DaggerApplication

class GoTCharacters : DaggerApplication() {

    override fun applicationInjector() = DaggerAppComponent.builder().application(this).build()

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}
