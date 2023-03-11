package com.ngapp.metanmobile.domain.di

import com.ngapp.metanmobile.data.repository.profile.ProfileRepository
import com.ngapp.testutils.MockkUnitTest
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class ProfileDomainModuleTest : MockkUnitTest() {

    private lateinit var module: ProfileDomainModule

    override fun onCreate() {
        super.onCreate()
        module = ProfileDomainModule()
    }

    @Test
    fun verifyProvideGetProfile() {
        val repository = mockk<ProfileRepository>()
        val getProfileFlow = module.provideGetProfile(repository)

        Assert.assertEquals(repository, getProfileFlow.profileRepo)
    }
}