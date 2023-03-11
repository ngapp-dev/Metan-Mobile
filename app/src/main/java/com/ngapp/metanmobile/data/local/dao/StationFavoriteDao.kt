package com.ngapp.metanmobile.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.ngapp.framework.room.dao.BaseDao
import com.ngapp.metanmobile.data.model.local.station.StationEntity

@Dao
interface StationFavoriteDao : BaseDao<StationEntity> {
    @Query("SELECT * FROM ${StationEntity.TABLE_NAME}")
    suspend fun getFavoriteList(): List<StationEntity>

    @Query("SELECT * FROM ${StationEntity.TABLE_NAME} WHERE id = :favoriteId")
    suspend fun getFavorite(favoriteId: Int): StationEntity?

    @Query("DELETE FROM ${StationEntity.TABLE_NAME}")
    suspend fun deleteFavoriteList()

    @Query("DELETE FROM ${StationEntity.TABLE_NAME} WHERE id = :favoriteId")
    suspend fun deleteFavoriteById(favoriteId: Int)
}