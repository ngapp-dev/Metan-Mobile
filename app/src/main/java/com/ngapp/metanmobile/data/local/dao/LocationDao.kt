package com.ngapp.metanmobile.data.local.dao

import androidx.room.*
import com.ngapp.metanmobile.data.model.local.location.LocationEntity

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLocation(location: LocationEntity)

    @Query("DELETE FROM ${LocationEntity.TABLE_NAME}")
    suspend fun deleteLocations()

    @Query("SELECT * FROM ${LocationEntity.TABLE_NAME} ORDER BY ${LocationEntity.COLUMN_TIME}")
    suspend fun getLocations(): List<LocationEntity>
}
