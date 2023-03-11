package com.ngapp.metanmobile.domain.di

import com.ngapp.metanmobile.data.repository.location.LocationRepository
import com.ngapp.metanmobile.data.repository.news.NewsRepository
import com.ngapp.metanmobile.data.repository.station.StationsRepository
import com.ngapp.testutils.MockkUnitTest
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class StationDomainModuleTest : MockkUnitTest() {
    private lateinit var module: StationDomainModule

    override fun onCreate() {
        super.onCreate()
        module = StationDomainModule()
    }

    @Test
    fun verifyProvideGetStations() {
        val stationsRepository = mockk<StationsRepository>()
        val locationRepository = mockk<LocationRepository>()
        val getStations = module.provideGetStations(stationsRepository, locationRepository)

        Assert.assertEquals(stationsRepository, getStations.stationsRepo)
        Assert.assertEquals(locationRepository, getStations.locationRepo)
    }

    @Test
    fun verifyProvideGetStationsFlow() {
        val stationsRepository = mockk<StationsRepository>()
        val locationRepository = mockk<LocationRepository>()
        val getStationsFlow = module.provideGetStationsFlow(stationsRepository, locationRepository)

        Assert.assertEquals(stationsRepository, getStationsFlow.stationsRepo)
        Assert.assertEquals(locationRepository, getStationsFlow.locationRepo)
    }

    @Test
    fun verifyProvideGetStationDetail() {
        val stationsRepository = mockk<StationsRepository>()
        val locationRepository = mockk<LocationRepository>()
        val newsRepository = mockk<NewsRepository>()
        val getStationDetail = module.provideGetStationDetail(stationsRepository, locationRepository, newsRepository)

        Assert.assertEquals(stationsRepository, getStationDetail.stationsRepo)
        Assert.assertEquals(locationRepository, getStationDetail.locationRepo)
        Assert.assertEquals(locationRepository, getStationDetail.newsRepo)
    }

    @Test
    fun verifyProvideAddFavorite() {
        val repo = mockk<StationsRepository>()
        val addFavorite = module.provideAddStationFavorite(repo)

        Assert.assertEquals(repo, addFavorite.repository)
    }

    @Test
    fun verifyProvideDeleteFavorite() {
        val repo = mockk<StationsRepository>()
        val deleteFavorite = module.provideDeleteStationFavorite(repo)

        Assert.assertEquals(repo, deleteFavorite.repository)
    }

    @Test
    fun verifyProvideGetFavorites() {
        val stationsRepository = mockk<StationsRepository>()
        val locationRepository = mockk<LocationRepository>()
        val getFavoriteList = module.provideGetStationFavorites(stationsRepository, locationRepository)

        Assert.assertEquals(stationsRepository, getFavoriteList.stationsRepo)
        Assert.assertEquals(locationRepository, getFavoriteList.locationsRepo)
    }


    @Test
    fun verifyProvideUpdateFavorite() {
        val repo = mockk<StationsRepository>()
        val updateFavorite = module.provideUpdateStationFavorite(repo)

        Assert.assertEquals(repo, updateFavorite.repository)
    }
}