package com.ngapp.metanmobile.domain.di

import com.ngapp.metanmobile.data.repository.language.LanguageRepository
import com.ngapp.testutils.MockkUnitTest
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class LanguageModuleTest : MockkUnitTest() {
    private lateinit var module: LanguageModule

    override fun onCreate() {
        super.onCreate()
        module = LanguageModule()
    }

    @Test
    fun verifyProvideSaveLanguage() {
        val repository = mockk<LanguageRepository>()
        val saveLanguage = module.provideSaveLanguage(repository)

        Assert.assertEquals(repository, saveLanguage.repository)
    }

    @Test
    fun verifyProvideGetLanguage() {
        val repository = mockk<LanguageRepository>()
        val getLanguage = module.provideGetLanguage(repository)

        Assert.assertEquals(repository, getLanguage.repository)
    }
}