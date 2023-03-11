package com.ngapp.metanmobile.data.model.dto.location

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationDto(
    var id: Int,
    var time: Long,
    var latitude: Double,
    var longitude: Double,
) : Parcelable {
    companion object {
        fun init() = LocationDto(
            id = 1,
            time = System.currentTimeMillis(),
            latitude = 0.0,
            longitude = 0.0
        )
    }
}