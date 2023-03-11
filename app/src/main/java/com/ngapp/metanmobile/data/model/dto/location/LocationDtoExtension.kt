package com.ngapp.metanmobile.data.model.dto.location

import android.location.Location
import com.ngapp.metanmobile.data.model.local.location.LocationEntity
import com.ngapp.metanmobile.data.model.dto.location.LocationDto

fun LocationEntity.toLocationDto() = LocationDto(
    id = id,
    time = time,
    latitude = latitude,
    longitude = longitude
)

fun List<LocationEntity>.toLocationDtoList() = map { it.toLocationDto() }

fun LocationDto.toLocationEntity() = LocationEntity(
    id = id,
    time = time,
    latitude = latitude,
    longitude = longitude
)

fun Location.toLocationEntity() = LocationEntity(
    time = time,
    latitude = latitude,
    longitude = longitude
)

