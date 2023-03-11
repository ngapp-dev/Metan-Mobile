package com.ngapp.metanmobile.app

import com.ngapp.framework.base.app.AppInitializer
import com.ngapp.framework.base.app.CoreApplication
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MetanMobileApp : CoreApplication() {

    @Inject
    lateinit var initializer: AppInitializer

    override fun onCreate() {
        super.onCreate()
        initializer.init(this)
    }
}