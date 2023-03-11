package com.ngapp.metanmobile.data.local.db

import com.ngapp.metanmobile.data.local.dao.LocationDao
import com.ngapp.metanmobile.data.local.dao.ProfileDao
import com.ngapp.metanmobile.data.local.dao.StationFavoriteDao
import com.ngapp.testutils.TestRobolectric
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test

class MetanMobileDatabaseTest : TestRobolectric() {

    @MockK
    lateinit var database: MetanMobileDatabase

    @MockK
    lateinit var profileDao: ProfileDao

    @MockK
    lateinit var stationFavoriteDao: StationFavoriteDao

    @MockK
    lateinit var locationDao: LocationDao

    @Test
    fun checkProfileFavoriteDao() {
        every { database.profileDao() } returns profileDao

        MatcherAssert.assertThat(
            database.profileDao(),
            CoreMatchers.instanceOf(ProfileDao::class.java)
        )
    }

    @Test
    fun checkEpisodeFavoriteDao() {
        every { database.stationFavoriteDao() } returns stationFavoriteDao

        MatcherAssert.assertThat(
            database.stationFavoriteDao(),
            CoreMatchers.instanceOf(StationFavoriteDao::class.java)
        )
    }

    @Test
    fun checkLocationFavoriteDao() {
        every { database.locationDao() } returns locationDao

        MatcherAssert.assertThat(
            database.locationDao(),
            CoreMatchers.instanceOf(LocationDao::class.java)
        )
    }
}