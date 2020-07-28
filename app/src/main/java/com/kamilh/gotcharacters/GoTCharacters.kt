package com.kamilh.gotcharacters

import com.audioburst.audioburst_player.AudioburstPlayer
import com.audioburst.audioburst_player.SdkKeys
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

        AudioburstPlayer.init(
            context = this,
            sdkKeys = SdkKeys(
                applicationKey = "5bea6355b9cd4e78aee3403346617b1c",
                experienceId = "0823a4b7-3296-45d0-a368-a33411f9c1e0"
            )
        )
    }
}
