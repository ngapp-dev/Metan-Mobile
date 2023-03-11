package com.ngapp.metanmobile.data.local.dao

import androidx.room.Room
import com.ngapp.metanmobile.data.local.db.MetanMobileDatabase
import com.ngapp.metanmobile.data.local.mockdata.LocalMockData
import com.ngapp.testutils.TestRobolectric
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Test
import java.io.IOException

class StationFavoriteDaoTest : TestRobolectric() {

    private lateinit var database: MetanMobileDatabase

    private lateinit var dao: StationFavoriteDao

    override fun onCreate() {
        super.onCreate()
        database = Room
            .inMemoryDatabaseBuilder(context, MetanMobileDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.stationFavoriteDao()
    }

    @Throws(IOException::class)
    override fun onDestroy() {
        super.onDestroy()
        database.close()
    }

    @Test
    fun getFavoriteList_WithData() = runTest {
        val fakeFavorite = LocalMockData.getStationFavoriteList()
        dao.insert(fakeFavorite)
        val favorites = dao.getFavoriteList()
        Assert.assertEquals(fakeFavorite.size, favorites.size)
    }

    @Test
    fun getFavoriteList_WithoutData() = runTest {
        val favorites = dao.getFavoriteList()
        Assert.assertTrue(favorites.isNullOrEmpty())
    }

    @Test
    fun getFavoriteList_WithData_ShouldReturnSorted() = runTest {
        val fakeFavorite = LocalMockData.getStationFavoriteList()
        dao.insert(fakeFavorite)
        Assert.assertEquals(fakeFavorite, dao.getFavoriteList())
    }

    @Test
    fun getFavoriteById_WithoutData_ShouldNotFound() = runTest {
        val fakeFavorite = LocalMockData.getStationFavoriteList()
        val favoriteToFind = fakeFavorite.first()
        Assert.assertNull(dao.getFavorite(favoriteToFind.id))
    }

    @Test
    fun getFavoriteById_WithData_ShouldFound() = runTest {
        val fakeFavorite = LocalMockData.getStationFavoriteList()
        dao.insert(fakeFavorite)
        val favoriteToFind = fakeFavorite.first()
        Assert.assertEquals(favoriteToFind, dao.getFavorite(favoriteToFind.id))
    }

    @Test
    fun insertFavorite_ShouldAdd() = runTest {
        val fakeFavorite = LocalMockData.getStationFavoriteList()
        fakeFavorite.forEach { dao.insert(it) }

        Assert.assertEquals(fakeFavorite, dao.getFavoriteList())
    }

    @Test
    fun deleteFavoriteList_ShouldRemoveAll() = runTest {
        val fakeFavorite = LocalMockData.getStationFavoriteList()
        dao.insert(fakeFavorite)
        dao.deleteFavoriteList()

        Assert.assertTrue(dao.getFavoriteList().isNullOrEmpty())
    }

    @Test
    fun deleteFavorite_Stored_ShouldRemoveIt() = runTest {
        val fakeFavorite = LocalMockData.getStationFavoriteList()
        dao.insert(fakeFavorite)

        val favoriteToRemove = fakeFavorite.first()
        dao.delete(favoriteToRemove)

        MatcherAssert.assertThat(
            dao.getFavoriteList(),
            CoreMatchers.not(CoreMatchers.hasItem(favoriteToRemove))
        )
    }

    @Test
    fun deleteFavoriteById_Stored_ShouldRemoveIt() = runTest {
        val fakeFavorite = LocalMockData.getStationFavoriteList()
        dao.insert(fakeFavorite)

        val favoriteToRemove = fakeFavorite.first()
        dao.deleteFavoriteById(favoriteToRemove.id)

        MatcherAssert.assertThat(
            dao.getFavoriteList(),
            CoreMatchers.not(CoreMatchers.hasItem(favoriteToRemove))
        )
    }
}