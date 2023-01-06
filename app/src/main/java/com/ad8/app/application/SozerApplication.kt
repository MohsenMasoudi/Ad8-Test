package com.ad8.app.application

import androidx.appcompat.app.AppCompatDelegate
import com.facebook.stetho.Stetho
import com.ad8.app.BuildConfig
import com.ad8.presentation.application.PresentationApplication
import com.ad8.presentation.util.extentions.Ext
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class SozerApplication : PresentationApplication() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        initStetho()
        Ext.with(this)


        /*  Crashlytics.Builder()
              .core(CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build())
              .build()
              .also { crashlyticsKit ->
                  Fabric.with(this, crashlyticsKit)
              }*/
    }
    /**
     * Stetho is debugging tools for getting remote data with Browser Inspect  In Iran we should use edge inspector not chrome
     */
    private fun initStetho() {
        if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)
    }
}