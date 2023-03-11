package com.ngapp.metanmobile.data.model.local.location

import org.junit.Assert
import org.junit.Test

class LocationEntityTest {
    @Test
    fun checkCorrectAttributes() {
        val id = 1
        val latitude = 0.0
        val longitude = 0.0

        val entity = LocationEntity(
            id = id,
            time = System.currentTimeMillis(),
            latitude = latitude,
            longitude = longitude,
        )

        Assert.assertEquals(id, entity.id)
        Assert.assertEquals(latitude, entity.latitude)
        Assert.assertEquals(longitude, entity.longitude)
    }
}