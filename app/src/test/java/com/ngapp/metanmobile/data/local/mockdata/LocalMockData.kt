package com.ngapp.metanmobile.data.local.mockdata

import com.ngapp.metanmobile.data.model.local.station.StationEntity

object LocalMockData {

    fun getStationFavoriteList(): List<StationEntity> {
        return listOf(
            StationEntity(
                id = 1,
                code = "agnks_grodno1",
                previewPicture = "https://metan.by/upload/iblock/d31/d315696ff381abdad3d77eab6e51b93f.jpg",
                detailPicture = "https://metan.by/upload/iblock/d31/d315696ff381abdad3d77eab6e51b93f.jpg",
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
                title = "АГНКС Гродно-1",
                dateCreated = System.currentTimeMillis(),
                url = "https://metan.by/ecogas-map/agnks_grodno1/"
            ),
            StationEntity(
                id = 2,
                code = "agnks_grodno2",
                previewPicture = "https://metan.by/upload/iblock/d31/d315696ff381abdad3d77eab6e51b93f.jpg",
                detailPicture = "https://metan.by/upload/iblock/d31/d315696ff381abdad3d77eab6e51b93f.jpg",
                isActive = 1,
                isOperate = 1,
                type = "АГНКС",
                address = "г. Гродно, Индурское шоссе, 15",
                region = "Гродненская область",
                phone = "+375 (152) 66-33-18, +375 (152) 66-24-47",
                service = "заправка КПГ",
                workingTime = "круглосуточно, без выходных,<br>\n" +
                        "перерывы: 08:30-09:00, 14:05-14:20, 20:30-21:00, 02:05-02:20<br>\n" +
                        " <span style=\"color: #ff0000;\">не работает 23.02.2023 с 10:00 до 13:00</span>",
                payment = "наличный расчет, по заключенным договорам, банковские карточки",
                latitude = "53.638017",
                longitude = "23.841898",
                busyOnMonday = "20,13,6,3,8,13,9,9,5,23,13,7,12,9,17,20,17,15,22,26,16,27,10,13",
                busyOnTuesday = "10,8,5,2,10,16,18,12,3,25,17,12,9,11,12,14,16,21,26,21,22,28,9,13",
                busyOnWednesday = "14,13,3,6,8,9,13,4,10,19,14,8,5,9,14,12,17,21,26,22,10,26,11,10",
                busyOnThursday = "10,6,7,4,10,4,13,8,13,13,18,11,11,14,18,14,16,17,21,22,21,23,16,14",
                busyOnFriday = "14,14,6,6,5,10,10,6,11,20,16,11,16,19,19,14,15,26,26,24,19,27,18,16",
                busyOnSaturday = "13,23,18,18,12,23,25,5,8,14,11,14,19,22,23,22,24,24,21,17,18,21,15,14",
                busyOnSunday = "16,25,30,16,13,21,25,15,8,8,18,14,9,13,16,17,20,28,34,23,13,22,17,4",
                googleTag = "AGNKS-Grodno-2",
                googleMapsTag = "15479851427510838583",
                yandexTag = "1199082518",
                title = "АГНКС Гродно-2",
                dateCreated = System.currentTimeMillis(),
                url = "https://metan.by/ecogas-map/agnks_grodno2/"
            ),
        )
    }
}