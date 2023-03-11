package com.ngapp.metanmobile.data.model.dto

import com.ngapp.metanmobile.data.model.dto.location.LocationDto
import org.junit.Assert
import org.junit.Test

class LocationDtoTest {

    @Test
    fun checkCorrectAttributes() {
        val id = 1
        val latitude = 0.0
        val longitude = 0.0

        val dto = LocationDto(
            id = id,
            time = System.currentTimeMillis(),
            latitude = latitude,
            longitude = longitude
        )

        Assert.assertEquals(id, dto.id)
        Assert.assertEquals(latitude, dto.latitude)
        Assert.assertEquals(longitude, dto.longitude)
    }
}