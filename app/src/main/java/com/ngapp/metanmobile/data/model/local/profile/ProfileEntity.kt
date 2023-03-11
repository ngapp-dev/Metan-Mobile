package com.ngapp.metanmobile.data.model.local.profile

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ProfileEntity.TABLE_NAME)
data class ProfileEntity(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID) val id: Int,
    @ColumnInfo(name = COLUMN_NAME) var name: String?,
    @ColumnInfo(name = COLUMN_EMAIL) var email: String?,
    @ColumnInfo(name = COLUMN_PASSWORD) var password: String,
    @ColumnInfo(name = COLUMN_CHECKWORD) var checkword: String?,
    @ColumnInfo(name = COLUMN_NICKNAME) var nickname: String?,
    @ColumnInfo(name = COLUMN_FIRST_NAME) var firstName: String?,
    @ColumnInfo(name = COLUMN_MIDDLE_NAME) var middleName: String?,
    @ColumnInfo(name = COLUMN_LAST_NAME) var lastName: String?,
    @ColumnInfo(name = COLUMN_PROFILE_IMAGE) var profileImage: String?,
    @ColumnInfo(name = COLUMN_BIO) var bio: String?,
    @ColumnInfo(name = COLUMN_LOCATION) var location: String?,
    @ColumnInfo(name = COLUMN_CREATE_DATE) var createDate: Long,
    @ColumnInfo(name = COLUMN_MOD_DATE) var modDate: Long?,
    @ColumnInfo(name = COLUMN_IS_ACTIVE) var isActive: Int,
    @ColumnInfo(name = COLUMN_LAST_LOGIN) var lastLogin: String?,
    @ColumnInfo(name = COLUMN_PERMISSION_LEVEL) var permissionLevel: Int
) {
    companion object {
        const val TABLE_NAME = "profile"

        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_CHECKWORD = "checkword"
        const val COLUMN_NICKNAME = "nickname"
        const val COLUMN_FIRST_NAME = "first_name"
        const val COLUMN_MIDDLE_NAME = "middle_name"
        const val COLUMN_LAST_NAME = "last_name"
        const val COLUMN_PROFILE_IMAGE = "profile_image"
        const val COLUMN_BIO = "bio"
        const val COLUMN_LOCATION = "location"
        const val COLUMN_CREATE_DATE = "create_date"
        const val COLUMN_MOD_DATE = "mod_date"
        const val COLUMN_IS_ACTIVE = "is_active"
        const val COLUMN_LAST_LOGIN = "last_login"
        const val COLUMN_PERMISSION_LEVEL = "permission_level"
    }
}