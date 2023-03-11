package com.ngapp.metanmobile.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ngapp.metanmobile.data.local.converter.StringListConverter
import com.ngapp.metanmobile.data.local.dao.LocationDao
import com.ngapp.metanmobile.data.local.dao.StationFavoriteDao
import com.ngapp.metanmobile.data.local.dao.ProfileDao
import com.ngapp.metanmobile.data.model.local.location.LocationEntity
import com.ngapp.metanmobile.data.model.local.station.StationEntity
import com.ngapp.metanmobile.data.model.local.profile.ProfileEntity

@Database(
    entities = [
        ProfileEntity::class,
        StationEntity::class,
        LocationEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(StringListConverter::class)
abstract class MetanMobileDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
    abstract fun stationFavoriteDao(): StationFavoriteDao
    abstract fun locationDao(): LocationDao
}