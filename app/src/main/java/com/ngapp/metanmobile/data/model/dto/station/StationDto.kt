package com.ngapp.metanmobile.data.model.dto.station

import android.os.Parcelable
import com.ngapp.metanmobile.data.model.dto.news.NewsDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class StationDto(
    var id: Int,
    var code: String,
    var previewPicture: String?,
    var detailPicture: String?,
    var isActive: Int,
    var isOperate: Int,
    var type: String,
    var address: String,
    var region: String?,
    var phone: String?,
    var service: String?,
    var workingTime: String?,
    var payment: String?,
    var latitude: String?,
    var longitude: String?,
    var busyOnMonday: String?,
    var busyOnTuesday: String?,
    var busyOnWednesday: String?,
    var busyOnThursday: String?,
    var busyOnFriday: String?,
    var busyOnSaturday: String?,
    var busyOnSunday: String?,
    var googleTag: String?,
    var googleMapsTag: String?,
    var yandexTag: String?,
    var title: String,
    var dateCreated: Long,
    var url: String?,
    var relatedNewsDtoList: MutableList<NewsDto> = mutableListOf(),
    var distanceBetween: Double = 0.0,
    var isFavorite: Boolean = false
) : Parcelable {
    companion object {
        fun init() = StationDto(
            id = 1,
            code = "agnks_grodno2",
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
            title = "АГНКС Гродно-2",
            dateCreated = System.currentTimeMillis(),
            url = "",
            distanceBetween = 0.0,
            isFavorite = false
        )
    }
}