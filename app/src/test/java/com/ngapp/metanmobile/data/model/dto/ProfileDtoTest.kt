package com.ngapp.metanmobile.data.model.dto

import com.ngapp.metanmobile.data.model.dto.profile.ProfileDto
import org.junit.Assert
import org.junit.Test

class ProfileDtoTest {

    @Test
    fun checkCorrectAttributes() {
        val id = 1
        val name = "User"

        val dto = ProfileDto(
            id = id,
            name = name,
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

        Assert.assertEquals(id, dto.id)
        Assert.assertEquals(name, dto.name)
    }
}