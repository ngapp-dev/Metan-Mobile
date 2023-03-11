package com.ngapp.metanmobile.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ngapp.metanmobile.data.model.local.profile.ProfileEntity
import com.ngapp.framework.room.dao.BaseDao

@Dao
interface ProfileDao : BaseDao<ProfileEntity> {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProfile(profile: ProfileEntity)

    @Query("DELETE FROM ${ProfileEntity.TABLE_NAME}")
    suspend fun deleteProfile()

    @Query("SELECT * FROM ${ProfileEntity.TABLE_NAME}")
    suspend fun getProfile(): List<ProfileEntity>


}