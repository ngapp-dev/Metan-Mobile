package com.ngapp.metanmobile.data.local.di

import android.content.Context
import com.ngapp.metanmobile.data.local.dao.LocationDao
import com.ngapp.metanmobile.data.local.dao.ProfileDao
import com.ngapp.metanmobile.data.local.dao.StationFavoriteDao
import com.ngapp.metanmobile.data.local.db.MetanMobileDatabase
import com.ngapp.testutils.MockkUnitTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

class LocalModuleTest : MockkUnitTest() {

    private lateinit var localModule: LocalModule

    override fun onCreate() {
        super.onCreate()
        localModule = LocalModule()
    }

    @Test
    fun verifyProvideDatabaseName() {
        val databaseName = localModule.provideDatabaseName()
        assertEquals(databaseName, com.ngapp.metanmobile.BuildConfig.DB_NAME)
    }

    @Test
    fun verifyProvideDatabase() {
        val context: Context = mockk()
        val databaseName = localModule.provideDatabaseName()
        val database = localModule.provideDatabase(databaseName, context)

        Assert.assertNotNull(database.profileDao())
        Assert.assertNotNull(database.stationFavoriteDao())
        Assert.assertNotNull(database.locationDao())
    }

    @Test
    fun verifyProvideProfileDao() {
        val database: MetanMobileDatabase = mockk()
        val dao: ProfileDao = mockk()

        every { database.profileDao() } returns dao

        assertEquals(
            dao,
            localModule.provideProfileDao(database)
        )
        verify { database.profileDao() }
    }

    @Test
    fun verifyProvideStationFavoriteDao() {
        val database: MetanMobileDatabase = mockk()
        val dao: StationFavoriteDao = mockk()

        every { database.stationFavoriteDao() } returns dao

        assertEquals(
            dao,
            localModule.provideStationFavoriteDao(database)
        )
        verify { database.stationFavoriteDao() }
    }

    @Test
    fun verifyProvideLocationDao() {
        val database: MetanMobileDatabase = mockk()
        val dao: LocationDao = mockk()

        every { database.locationDao() } returns dao

        assertEquals(
            dao,
            localModule.provideLocationDao(database)
        )
        verify { database.locationDao() }
    }
}