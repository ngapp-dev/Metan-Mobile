package com.ngapp.metanmobile.domain.usecase.station.favorite

import androidx.annotation.VisibleForTesting
import com.ngapp.framework.extension.orZero
import com.ngapp.framework.usecase.LocalUseCase
import com.ngapp.metanmobile.data.model.dto.station.StationDto
import com.ngapp.metanmobile.data.model.dto.station.toStationEntity
import com.ngapp.metanmobile.data.repository.station.StationsRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class UpdateStationFavorite @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val repository: StationsRepository
) : LocalUseCase<UpdateStationFavorite.Params, Unit>() {

    data class Params(
        val station: StationDto
    )

    override suspend fun FlowCollector<Unit>.execute(params: Params) {
        val dto = params.station
        val station = repository.getFavorite(dto.id.orZero())
        if (station == null) {
            repository.saveFavorite(dto.toStationEntity())
        } else {
            repository.deleteFavoriteById(dto.id.orZero())
        }
        emit(Unit)
    }
}