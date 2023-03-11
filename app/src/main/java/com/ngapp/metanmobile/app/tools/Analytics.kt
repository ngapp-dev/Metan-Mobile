package com.ngapp.metanmobile.app.tools

interface Analytics {
    fun trackScreenView(screenName: String, className: String)
}