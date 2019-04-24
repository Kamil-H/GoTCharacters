package com.kamilh.gotcharacters

import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import com.kamilh.gotcharacters.di.DaggerAppComponent
import dagger.android.support.DaggerApplication

class GoTCharacters : DaggerApplication() {

    override fun applicationInjector() = DaggerAppComponent.builder().application(this).build()

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}
