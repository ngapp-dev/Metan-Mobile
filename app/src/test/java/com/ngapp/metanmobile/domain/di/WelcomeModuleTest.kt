package com.ngapp.metanmobile.domain.di

import com.ngapp.metanmobile.data.repository.welcome.WelcomeRepository
import com.ngapp.testutils.MockkUnitTest
import io.mockk.impl.annotations.MockK
import org.junit.Assert
import org.junit.Test

class WelcomeModuleTest : MockkUnitTest() {
    private lateinit var module: WelcomeModule

    @MockK
    private lateinit var repository: WelcomeRepository

    override fun onCreate() {
        super.onCreate()
        module = WelcomeModule()
    }

    @Test
    fun verifyProvideSaveOnBoarding() {
        val saveOnBoarding = module.provideSaveOnBoarding(repository)

        Assert.assertEquals(repository, saveOnBoarding.repository)
    }

    @Test
    fun verifyProvideReadOnBoarding() {
        val readOnBoarding = module.provideReadOnBoarding(repository)

        Assert.assertEquals(repository, readOnBoarding.repository)
    }
}