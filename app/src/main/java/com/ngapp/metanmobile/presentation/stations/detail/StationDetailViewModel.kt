@file:OptIn(ExperimentalPermissionsApi::class)

package com.ngapp.metanmobile.presentation.stations.detail

import androidx.lifecycle.viewModelScope
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.ngapp.framework.base.mvi.BaseViewState
import com.ngapp.framework.base.mvi.MviViewModel
import com.ngapp.metanmobile.app.component.PermissionsHandler
import com.ngapp.metanmobile.app.tools.Analytics
import com.ngapp.metanmobile.data.model.dto.news.NewsDto
import com.ngapp.metanmobile.data.model.dto.station.StationDto
import com.ngapp.metanmobile.domain.usecase.news.ShareNews
import com.ngapp.metanmobile.domain.usecase.station.GetStationDetail
import com.ngapp.metanmobile.domain.usecase.station.ShareStation
import com.ngapp.metanmobile.presentation.home.HomeViewState
import com.ngapp.metanmobile.presentation.news.detail.NewsDetailEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class StationDetailViewModel @Inject constructor(
    private val getStationDetail: GetStationDetail,
    private val shareStation: ShareStation,
    private val analytics: Analytics,
    val permissionsHandler: PermissionsHandler
) : MviViewModel<BaseViewState<StationDetailViewState>, StationDetailEvent>() {

    private val _permissionsState = MutableStateFlow(HomeViewState())
    val permissionsState: StateFlow<HomeViewState> = _permissionsState

    init {
        permissionsHandler
            .state
            .onEach { handlerState ->
                Timber.e(handlerState.toString())
                _permissionsState.update { it.copy(multiplePermissionsState = handlerState.multiplePermissionsState) }
            }
            .catch { Timber.e(it) }
            .launchIn(viewModelScope)
    }

    override fun onTriggerEvent(eventType: StationDetailEvent) {
        when (eventType) {
            is StationDetailEvent.LoadDetail -> onLoadDetail(eventType.code)
            is StationDetailEvent.ShareStation -> onShareStation()
            else -> throw IllegalStateException("StationDetailViewModel")
        }
    }

    private val _shareStationDto: MutableStateFlow<StationDto?> = MutableStateFlow(null)
    var shareStationDto: StationDto? = _shareStationDto.value

    private fun onShareStation() = safeLaunch {
        val params = ShareStation.Params(shareStationDto)
        call(shareStation(params))
    }

    private fun onLoadDetail(code: String) = safeLaunch {
        analytics.trackScreenView(
            screenName = "StationDetail->onLoadDetail",
            className = "StationDetailScreen"
        )
        setState(BaseViewState.Loading)
        val locationPermissionGranted = permissionsState.value.multiplePermissionsState?.allPermissionsGranted ?: false
        val params = GetStationDetail.Params(stationCode = code, locationPermissionGranted = locationPermissionGranted)
        execute(getStationDetail(params)) { stationDto ->
            setState(BaseViewState.Data(StationDetailViewState(stationData = stationDto)))
        }
    }
}