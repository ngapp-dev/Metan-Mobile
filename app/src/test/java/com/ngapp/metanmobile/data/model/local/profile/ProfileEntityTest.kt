package com.ngapp.metanmobile.data.model.local.profile

import org.junit.Assert
import org.junit.Test

class ProfileEntityTest {

    @Test
    fun checkCorrectAttributes() {
        val id = 1
        val name = "User"

        val entity = ProfileEntity(
            id = id,
            name = name,
            email = "",
            password = "",
            checkword = "",
            nickname = "",
            firstName = "",
            middleName = "",
            lastName = "",
            profileImage = null,
            bio = "",
            location = "",
            createDate = System.currentTimeMillis(),
            modDate = System.currentTimeMillis(),
            isActive = 1,
            lastLogin = "",
            permissionLevel = 1
        )

        Assert.assertEquals(id, entity.id)
        Assert.assertEquals(name, entity.name)
    }
}