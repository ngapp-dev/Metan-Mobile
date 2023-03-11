package com.ngapp.metanmobile.presentation.stations.list

import androidx.paging.PagingData
import com.ngapp.metanmobile.data.model.dto.location.LocationDto
import com.ngapp.metanmobile.data.model.dto.station.StationDto
import com.ngapp.metanmobile.domain.mockdata.MockData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Test

class StationsContractTest {
    private lateinit var event: StationsEvent

    private lateinit var state: StationsViewState

    @Test
    fun verifyEventLoadStations() {
        event = StationsEvent.LoadStations

        val eventLoadDetail = event as StationsEvent.LoadStations
        Assert.assertEquals(event, eventLoadDetail)
    }

    @Test
    fun verifyEventAddOrRemoveFavorite() {
        val dto = MockData.getStationDto()
        event = StationsEvent.AddOrRemoveFavorite(dto)

        val eventAddOrRemoveFavorite = event as StationsEvent.AddOrRemoveFavorite
        Assert.assertEquals(dto, eventAddOrRemoveFavorite.stationDto)
    }

    @Test
    fun verifyEventLoadFavorites() {
        event = StationsEvent.LoadFavorites

        val eventLoadFavorites = event as StationsEvent.LoadFavorites
        Assert.assertEquals(event, eventLoadFavorites)
    }

    @Test
    fun verifyEventDeleteFavorite() {
        val id = 1
        event = StationsEvent.DeleteFavorite(id)

        val eventDeleteFavorite = event as StationsEvent.DeleteFavorite
        Assert.assertEquals(id, eventDeleteFavorite.id)
    }

    @Test
    fun verifyState() {
        val stationsData: List<StationDto> = emptyList()
        val locationsData: Flow<List<LocationDto>> = emptyFlow()
        state = StationsViewState(stationsData, locationsData, emptyList())

        Assert.assertEquals(stationsData, state.stationsData)
        Assert.assertEquals(locationsData, state.locationsData)
        Assert.assertEquals(0, state.favoriteStationList.size)
    }
}