package com.ngapp.metanmobile.app.tools

import android.content.Context
import com.ngapp.framework.extension.deviceId
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent

class MetanMobileAnalytics constructor(private val context: Context) : Analytics {

    private val analytics = FirebaseAnalytics.getInstance(context).apply {
        setUserId(context.deviceId())
        setAnalyticsCollectionEnabled(true)
    }

    override fun trackScreenView(screenName: String, className: String) {
        analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
            param(FirebaseAnalytics.Param.SCREEN_CLASS, className)
        }
    }
}