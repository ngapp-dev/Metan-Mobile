package com.ngapp.metanmobile.domain.usecase.station.favorite

import androidx.annotation.VisibleForTesting
import com.ngapp.framework.usecase.LocalUseCase
import com.ngapp.metanmobile.data.repository.station.StationsRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class DeleteStationFavorite @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val repository: StationsRepository
) : LocalUseCase<DeleteStationFavorite.Params, Unit>() {

    data class Params(
        val stationId: Int
    )

    override suspend fun FlowCollector<Unit>.execute(params: Params) {
        repository.deleteFavoriteById(params.stationId)
        emit(Unit)
    }
}