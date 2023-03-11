package com.ngapp.metanmobile.data.model.dto.profile

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileDto(
    var id: Int,
    var name: String?,
    var email: String?,
    var password: String,
    var checkword: String?,
    var nickname: String?,
    var firstName: String?,
    var middleName: String?,
    var lastName: String?,
    var profileImage: String?,
    var bio: String?,
    var location: String?,
    var createDate: Long,
    var modDate: Long?,
    var isActive: Int,
    var lastLogin: String?,
    var permissionLevel: Int
) : Parcelable {
    companion object {
        fun init() = ProfileDto(
            id = 1,
            name = "User",
            email = "",
            password = "",
            checkword = "",
            nickname = "",
            firstName = null,
            middleName = null,
            lastName = null,
            profileImage = null,
            bio = "",
            location = null,
            createDate = System.currentTimeMillis(),
            modDate = System.currentTimeMillis(),
            isActive = 1,
            lastLogin = "",
            permissionLevel = 1
        )
    }
}