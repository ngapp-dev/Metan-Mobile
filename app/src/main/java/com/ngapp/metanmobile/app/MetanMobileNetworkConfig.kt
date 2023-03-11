package com.ngapp.metanmobile.app

import com.ngapp.framework.base.app.NetworkConfig
import com.ngapp.metanmobile.BuildConfig

class MetanMobileNetworkConfig : NetworkConfig() {
    override fun baseUrl(): String {
        return BuildConfig.BASE_URL
    }

    override fun githubUrl(): String {
        return BuildConfig.GITHUB_URL
    }

    override fun timeOut(): Long {
        return 30L
    }

    override fun isDev(): Boolean {
        return BuildConfig.DEBUG
    }
}