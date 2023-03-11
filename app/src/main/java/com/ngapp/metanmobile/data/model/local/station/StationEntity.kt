package com.ngapp.metanmobile.data.model.local.station

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = StationEntity.TABLE_NAME)
data class StationEntity(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID) val id: Int,
    @ColumnInfo(name = COLUMN_CODE) val code: String,
    @ColumnInfo(name = COLUMN_PREVIEW_PICTURE) var previewPicture: String?,
    @ColumnInfo(name = COLUMN_DETAIL_PICTURE) var detailPicture: String?,
    @ColumnInfo(name = COLUMN_IS_ACTIVE) var isActive: Int,
    @ColumnInfo(name = COLUMN_IS_OPERATE) var isOperate: Int,
    @ColumnInfo(name = COLUMN_TYPE) var type: String,
    @ColumnInfo(name = COLUMN_ADDRESS) var address: String,
    @ColumnInfo(name = COLUMN_REGION) var region: String?,
    @ColumnInfo(name = COLUMN_PHONE) var phone: String?,
    @ColumnInfo(name = COLUMN_SERVICE) var service: String?,
    @ColumnInfo(name = COLUMN_WORKING_TIME) var workingTime: String?,
    @ColumnInfo(name = COLUMN_PAYMENT) var payment: String?,
    @ColumnInfo(name = COLUMN_LATITUDE) var latitude: String?,
    @ColumnInfo(name = COLUMN_LONGITUDE) var longitude: String?,
    @ColumnInfo(name = COLUMN_BUSY_ON_MONDAY) var busyOnMonday: String?,
    @ColumnInfo(name = COLUMN_BUSY_ON_TUESDAY) var busyOnTuesday: String?,
    @ColumnInfo(name = COLUMN_BUSY_ON_WEDNESDAY) var busyOnWednesday: String?,
    @ColumnInfo(name = COLUMN_BUSY_ON_THURSDAY) var busyOnThursday: String?,
    @ColumnInfo(name = COLUMN_BUSY_ON_FRIDAY) var busyOnFriday: String?,
    @ColumnInfo(name = COLUMN_BUSY_ON_SATURDAY) var busyOnSaturday: String?,
    @ColumnInfo(name = COLUMN_BUSY_ON_SUNDAY) var busyOnSunday: String?,
    @ColumnInfo(name = COLUMN_GOOGLE_TAG) var googleTag: String?,
    @ColumnInfo(name = COLUMN_GOOGLE_MAPS_TAG) var googleMapsTag: String?,
    @ColumnInfo(name = COLUMN_YANDEX_TAG) var yandexTag: String?,
    @ColumnInfo(name = COLUMN_TITLE) var title: String,
    @ColumnInfo(name = COLUMN_DATE_CREATED) var dateCreated: Long,
    @ColumnInfo(name = COLUMN_URL) var url: String?,
) {
    companion object {
        const val TABLE_NAME = "stations_favorite"

        const val COLUMN_ID = "id"
        const val COLUMN_CODE = "code"
        const val COLUMN_PREVIEW_PICTURE = "preview_picture"
        const val COLUMN_DETAIL_PICTURE = "detail_picture"
        const val COLUMN_IS_ACTIVE = "is_active"
        const val COLUMN_IS_OPERATE = "is_operate"
        const val COLUMN_TYPE = "type"
        const val COLUMN_ADDRESS = "address"
        const val COLUMN_REGION = "region"
        const val COLUMN_PHONE = "phone"
        const val COLUMN_SERVICE = "service"
        const val COLUMN_WORKING_TIME = "working_time"
        const val COLUMN_PAYMENT = "payment"
        const val COLUMN_LATITUDE = "latitude"
        const val COLUMN_LONGITUDE = "longitude"
        const val COLUMN_BUSY_ON_MONDAY = "busy_on_monday"
        const val COLUMN_BUSY_ON_TUESDAY = "busy_on_tuesday"
        const val COLUMN_BUSY_ON_WEDNESDAY = "busy_on_wednesday"
        const val COLUMN_BUSY_ON_THURSDAY = "busy_on_thursday"
        const val COLUMN_BUSY_ON_FRIDAY = "busy_on_friday"
        const val COLUMN_BUSY_ON_SATURDAY = "busy_on_saturday"
        const val COLUMN_BUSY_ON_SUNDAY = "busy_on_sunday"
        const val COLUMN_GOOGLE_TAG = "google_tag"
        const val COLUMN_GOOGLE_MAPS_TAG = "google_maps_tag"
        const val COLUMN_YANDEX_TAG = "yandex_tag"
        const val COLUMN_TITLE = "title"
        const val COLUMN_DATE_CREATED = "date_created"
        const val COLUMN_URL = "url"
    }
}