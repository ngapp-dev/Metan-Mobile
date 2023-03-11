package com.ngapp.metanmobile.data.model.local.location

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = LocationEntity.TABLE_NAME)
data class LocationEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID) val id: Int = 0,
    @ColumnInfo(name = COLUMN_TIME) val time: Long,
    @ColumnInfo(name = COLUMN_LATITUDE) val latitude: Double,
    @ColumnInfo(name = COLUMN_LONGITUDE) val longitude: Double,
) {
    companion object {
        const val TABLE_NAME = "location"

        const val COLUMN_ID = "id"
        const val COLUMN_TIME = "time"
        const val COLUMN_LATITUDE = "latitude"
        const val COLUMN_LONGITUDE = "longitude"
    }
}

