package com.ngapp.metanmobile.data.model.dto

import com.ngapp.metanmobile.data.model.dto.station.StationDto
import org.junit.Assert
import org.junit.Test

class StationDtoTest {
    @Test
    fun checkCorrectAttributes() {
        val id = 1
        val code = "agnks_grodno2"
        val title = "АГНКС Гродно-2"
        val url = "https://metan.by/ecogas-map/agnks_grodno2/"

        val dto = StationDto(
            id = 1,
            code = code,
            previewPicture = null,
            detailPicture = null,
            isActive = 1,
            isOperate = 1,
            type = "АГНКС",
            address = "",
            region = null,
            phone = null,
            service = null,
            workingTime = "",
            payment = null,
            latitude = "53.925653",
            longitude = "27.667294",
            busyOnMonday = "",
            busyOnTuesday = "",
            busyOnWednesday = "",
            busyOnThursday = "",
            busyOnFriday = "",
            busyOnSaturday = "",
            busyOnSunday = "",
            googleTag = "",
            googleMapsTag = "",
            yandexTag = "",
            title = title,
            dateCreated = System.currentTimeMillis(),
            url = "",
            distanceBetween = 0.0,
            isFavorite = false
        )

        Assert.assertEquals(id, dto.id)
        Assert.assertEquals(code, dto.code)
        Assert.assertEquals(title, dto.title)
        Assert.assertEquals(url, dto.url)
    }
}