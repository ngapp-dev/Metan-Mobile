package com.ngapp.metanmobile.presentation.stations.detail

import com.ngapp.metanmobile.data.model.dto.location.LocationDto
import com.ngapp.metanmobile.data.model.dto.station.StationDto

data class StationDetailViewState(
    val stationData: StationDto? = null,
    val locationsData: List<LocationDto> = emptyList(),
    val favoriteStationList: List<StationDto> = emptyList()
)

sealed class StationDetailEvent {
    data class LoadDetail(val code: String) : StationDetailEvent()
    data class AddOrRemoveFavorite(val stationDto: StationDto) : StationDetailEvent()
    object LoadFavorites : StationDetailEvent()
    data class DeleteFavorite(val id: Int) : StationDetailEvent()
    object ShareStation: StationDetailEvent()
}