package com.ngapp.metanmobile.data.model.local.station

import org.junit.Assert
import org.junit.Test

class StationEntityTest {
    @Test
    fun checkCorrectAttributes() {
        val id = 1
        val code = "agnks_grodno2"
        val title = "АГНКС Гродно-2"
        val previewPicture = "https://metan.by/upload/iblock/d31/d315696ff381abdad3d77eab6e51b93f.jpg"
        val detailPicture = "https://metan.by/upload/iblock/d31/d315696ff381abdad3d77eab6e51b93f.jpg"
        val url = "https://metan.by/ecogas-map/agnks_grodno2/"

        val entity = StationEntity(
            id = 1,
            code = code,
            previewPicture = previewPicture,
            detailPicture = detailPicture,
            isActive = 1,
            isOperate = 1,
            type = "АГНКС",
            address = "г. Гродно, пр-т. Космонавтов, 87",
            region = "Гродненская область",
            phone = "+375 (152) 55-50-07, +375 (152) 55-50-08",
            service = "заправка КПГ",
            workingTime = "круглосуточно, без выходных,<br>\n" +
                    "перерывы: 09:00-09:30, 13:50-14:05, 21:00-21:30, 01:50-02:05",
            payment = "наличный расчет, по заключенным договорам, банковские карточки",
            latitude = "53.671096",
            longitude = "23.882400",
            busyOnMonday = "7,4,2,1,3,11,13,16,12,15,11,14,17,16,14,21,16,20,17,16,14,9,5,3",
            busyOnTuesday = "4,3,1,5,7,8,7,11,12,15,11,13,12,17,18,23,22,12,25,14,10,10,7,8",
            busyOnWednesday = "3,9,5,1,4,5,16,9,8,17,16,11,13,10,19,29,27,11,23,15,7,21,5,3",
            busyOnThursday = "8,6,1,4,2,14,12,16,14,20,16,17,10,21,13,14,16,23,22,10,11,13,13,7",
            busyOnFriday = "6,8,7,5,4,13,13,13,14,21,14,11,7,14,16,24,19,13,29,24,12,18,7,5",
            busyOnSaturday = "7,8,6,10,9,14,20,10,10,12,6,15,15,19,14,17,19,21,24,23,15,11,5,6",
            busyOnSunday = "10,5,12,9,6,17,12,9,7,13,8,7,8,14,12,17,21,26,17,9,7,15,4,13",
            googleTag = "AGNKS-Grodno",
            googleMapsTag = "16671412901506656138",
            yandexTag = "1028225046",
            title = title,
            dateCreated = System.currentTimeMillis(),
            url = url
        )

        Assert.assertEquals(id, entity.id)
        Assert.assertEquals(code, entity.code)
        Assert.assertEquals(title, entity.title)
        Assert.assertEquals(previewPicture, entity.previewPicture)
        Assert.assertEquals(detailPicture, entity.detailPicture)
        Assert.assertEquals(url, entity.url)
    }
}