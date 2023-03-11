package com.ngapp.framework.extension

import android.content.Context
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability

class ServiceVersionUpdateException(message: String?) : Exception(message)

class PlayServiceNotFoundException(message: String?) : Exception(message)

fun Context.isGoogleServicesAvailable() {
    val availability = GoogleApiAvailability.getInstance()
    val resultCode = availability.isGooglePlayServicesAvailable(this)
    when (resultCode) {
        ConnectionResult.SUCCESS -> {
            return
//            throw PlayServiceNotFoundException("Seems that this device does not have Google services, functionality may be limited")
        }
        ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED -> {
            throw ServiceVersionUpdateException("Google services version is outdated, please update")
        }
        else -> {
            return
        }
    }
}
