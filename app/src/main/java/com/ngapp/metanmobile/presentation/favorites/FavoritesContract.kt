package com.ngapp.metanmobile.presentation.favorites

import com.ngapp.metanmobile.data.model.dto.location.LocationDto
import com.ngapp.metanmobile.data.model.dto.station.StationDto
import com.ngapp.metanmobile.presentation.stations.list.StationsEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class FavoritesViewState(
    val locationData: Flow<List<LocationDto>> = emptyFlow(),
    val favoriteStationList: List<StationDto> = emptyList()
)

sealed class FavoritesEvent {
    data class AddOrRemoveStationFavorite(val stationDto: StationDto) : FavoritesEvent()
    object LoadStationFavorites : FavoritesEvent()
    data class DeleteStationFavorite(val id: Int) : FavoritesEvent()
    object OpenAlertDialog: FavoritesEvent()
    object ConfirmAlertDialog: FavoritesEvent()
    object DismissAlertDialog: FavoritesEvent()
    data class UpdateSearchQuery(val input: String) : FavoritesEvent()
    object PermissionsRequestAgain : FavoritesEvent()
}