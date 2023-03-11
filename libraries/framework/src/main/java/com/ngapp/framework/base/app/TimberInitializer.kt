package com.ngapp.framework.base.app

import com.ngapp.framework.platform.isHMS
import timber.log.Timber

class TimberInitializer(private val isDev: Boolean) : AppInitializer {
    override fun init(application: CoreApplication) {
        if (isDev) {
            Timber.plant(Timber.DebugTree())
        } else {
            if (application.applicationContext.isHMS()) {
                Timber.plant(FirebaseCrashlyticsReportTree())
            } else {
                Timber.plant(FirebaseCrashlyticsReportTree())
            }
        }
    }
}