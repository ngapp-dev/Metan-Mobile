package com.ngapp.metanmobile.presentation.stations.detail

import com.ngapp.metanmobile.domain.mockdata.MockData
import com.ngapp.metanmobile.presentation.stations.list.StationsEvent
import org.junit.Assert
import org.junit.Test

class  StationDetailContractTest {
    private lateinit var event: StationDetailEvent

    private lateinit var state: StationDetailViewState

    @Test
    fun verifyEventLoadDetail() {
        val code = "agnks_grodno2"
        event = StationDetailEvent.LoadDetail(code)

        val eventLoadDetail = event as StationDetailEvent.LoadDetail
        Assert.assertEquals(code, eventLoadDetail.code)
    }

    @Test
    fun verifyEventAddOrRemoveFavorite() {
        val dto = MockData.getStationDto()
        event = StationDetailEvent.AddOrRemoveFavorite(dto)

        val eventAddOrRemoveFavorite = event as StationDetailEvent.AddOrRemoveFavorite
        Assert.assertEquals(dto, eventAddOrRemoveFavorite.stationDto)
    }

    @Test
    fun verifyEventLoadFavorites() {
        event = StationDetailEvent.LoadFavorites

        val eventLoadFavorites = event as StationDetailEvent.LoadFavorites
        Assert.assertEquals(event, eventLoadFavorites)
    }

    @Test
    fun verifyEventDeleteFavorite() {
        val id = 1
        event = StationDetailEvent.DeleteFavorite(id)

        val eventDeleteFavorite = event as StationDetailEvent.DeleteFavorite
        Assert.assertEquals(id, eventDeleteFavorite.id)
    }

    @Test
    fun verifyState() {
        val stationData = MockData.getStationDto()
        val locationData = MockData.getLocationDtoList()
        state = StationDetailViewState(stationData, locationData, emptyList())

        Assert.assertEquals(stationData, state.stationData)
        Assert.assertEquals(locationData, state.locationsData)
        Assert.assertEquals(0, state.favoriteStationList.size)
    }
}