package com.ngapp.metanmobile.app.extension

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri


fun Context.createInternetIntent(url: String) {
    val activity = (this as ContextWrapper).baseContext
    val internetIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    internetIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    activity.startActivity(internetIntent)
}