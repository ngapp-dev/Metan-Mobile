package com.ngapp.metanmobile.presentation.stations.list

import com.ngapp.metanmobile.data.model.dto.location.LocationDto
import com.ngapp.metanmobile.data.model.dto.station.StationDto
import com.ngapp.metanmobile.presentation.home.HomeEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class StationsViewState(
    val stationsData: List<StationDto> = emptyList(),
    val locationData: Flow<List<LocationDto>> = emptyFlow(),
    val favoriteStationList: List<StationDto> = emptyList()
)

sealed class StationsEvent {
    object LoadStations: StationsEvent()
    data class AddOrRemoveFavorite(val stationDto: StationDto) : StationsEvent()
    object LoadFavorites : StationsEvent()
    data class DeleteFavorite(val id: Int) : StationsEvent()
    object OpenAlertDialog: StationsEvent()
    object ConfirmAlertDialog: StationsEvent()
    object DismissAlertDialog: StationsEvent()
    data class UpdateSearchQuery(val input: String) : StationsEvent()
    object PermissionsRequestAgain : StationsEvent()
}