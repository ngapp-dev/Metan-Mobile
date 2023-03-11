package com.ngapp.metanmobile.data.repository.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import androidx.annotation.VisibleForTesting
import androidx.room.withTransaction
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Tasks
import com.ngapp.framework.extension.isGoogleServicesAvailable
import com.ngapp.metanmobile.data.local.db.MetanMobileDatabase
import com.ngapp.metanmobile.data.model.dto.location.LocationDto
import com.ngapp.metanmobile.data.model.dto.location.toLocationDtoList
import com.ngapp.metanmobile.data.model.dto.location.toLocationEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class LocationRepository @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val database: MetanMobileDatabase,
    @ApplicationContext val context: Context
) {

    suspend fun getLocations(locationPermissionGranted: Boolean): List<LocationDto> {
        return if (locationPermissionGranted) {
            updateLocation()
            database.locationDao().getLocations().toLocationDtoList()
        } else {
            database.locationDao().getLocations().toLocationDtoList()
        }
    }

    @SuppressLint("MissingPermission")
    suspend fun getLocationData(): Location? = withContext(Dispatchers.IO) {
        val flpc = LocationServices.getFusedLocationProviderClient(context)
        try {
            return@withContext Tasks.await(flpc.lastLocation)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return@withContext null
    }


    @SuppressLint("MissingPermission")
    suspend fun updateLocation() {
        runCatching {
            context.isGoogleServicesAvailable()
        }.onSuccess {
            val location = getLocationData()?.toLocationEntity()
            database.withTransaction {
                location?.let {
                    database.locationDao().deleteLocations()
                    database.locationDao().insertLocation(it)
                }
            }
        }
    }
}

