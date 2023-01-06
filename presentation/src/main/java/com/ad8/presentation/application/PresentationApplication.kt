package com.ad8.presentation.application

import android.content.Context
import android.content.res.Configuration
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.multidex.MultiDexApplication
import com.orhanobut.hawk.Hawk

open class PresentationApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build()
    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        // In some cases modifying newConfig leads to unexpected behavior,
        // so it's better to edit new instance.
        val configuration = Configuration(newConfig)
        adjustFontScale(applicationContext, configuration)

    }
    fun adjustFontScale(context: Context, configuration: Configuration) {
        if (configuration.fontScale != 1f) {
            configuration.fontScale = 1f
            val metrics: DisplayMetrics = context.resources.displayMetrics
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            wm.defaultDisplay.getMetrics(metrics)
            metrics.scaledDensity = configuration.fontScale * metrics.density
            context.resources.updateConfiguration(configuration, metrics)
        }
}}