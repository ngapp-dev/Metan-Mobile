package com.ngapp.metanmobile.data.repository.di

import android.content.Context
import com.ngapp.metanmobile.data.local.dao.ProfileDao
import com.ngapp.metanmobile.data.local.dao.StationFavoriteDao
import com.ngapp.testutils.MockkUnitTest
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class RepositoryModuleTest : MockkUnitTest() {

    private lateinit var repositoryModule: RepositoryModule

    override fun onCreate() {
        super.onCreate()
        repositoryModule = RepositoryModule()
    }

    @Test
    fun verifyProvideStationsRepository() {
        val dao = mockk<StationFavoriteDao>()
        val context = mockk<Context>(relaxed = true)
        val repository = repositoryModule.provideStationsRepository(dao, context)

        Assert.assertEquals(dao, repository.dao)
        Assert.assertEquals(context, repository.context)
    }

    @Test
    fun verifyProvideNewsRepository() {
        val context = mockk<Context>(relaxed = true)
        val repository = repositoryModule.provideNewsRepository(context)

        Assert.assertEquals(context, repository.context)
    }

    @Test
    fun verifyProvideContactsRepository() {
        val context = mockk<Context>(relaxed = true)
        val repository = repositoryModule.provideContactsRepository(context)

        Assert.assertEquals(context, repository.context)
    }

    @Test
    fun verifyProvideProfileRepository() {
        val dao = mockk<ProfileDao>()
        val context = mockk<Context>(relaxed = true)
        val repository = repositoryModule.provideProfileRepository(dao,context)

        Assert.assertEquals(dao, repository.dao)
        Assert.assertEquals(context, repository.context)
    }
}