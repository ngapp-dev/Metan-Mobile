@file:OptIn(ExperimentalPermissionsApi::class)

package com.ngapp.metanmobile.presentation.stations.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.ngapp.framework.base.mvi.BaseViewState
import com.ngapp.framework.base.mvi.MviViewModel
import com.ngapp.metanmobile.app.component.PermissionsHandler
import com.ngapp.metanmobile.data.model.dto.station.StationDto
import com.ngapp.metanmobile.domain.usecase.location.GetLocationsFlow
import com.ngapp.metanmobile.domain.usecase.station.GetStations
import com.ngapp.metanmobile.domain.usecase.station.favorite.DeleteStationFavorite
import com.ngapp.metanmobile.domain.usecase.station.favorite.UpdateStationFavorite
import com.ngapp.metanmobile.presentation.home.HomeViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class StationsViewModel @Inject constructor(
    private val getStations: GetStations,
    private val updateStationFavorite: UpdateStationFavorite,
    private val deleteFavorite: DeleteStationFavorite,
    private val getLocations: GetLocationsFlow,
    val permissionsHandler: PermissionsHandler
) : MviViewModel<BaseViewState<StationsViewState>, StationsEvent>() {

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

    override fun onTriggerEvent(eventType: StationsEvent) {
        when (eventType) {
            is StationsEvent.AddOrRemoveFavorite -> onAddOrRemoveFavorite(eventType.stationDto)
            is StationsEvent.DeleteFavorite -> onDeleteFavorite(eventType.id)
            is StationsEvent.LoadStations -> onLoadStations()
            is StationsEvent.OpenAlertDialog -> onOpenAlertDialogClicked()
            is StationsEvent.ConfirmAlertDialog -> onAlertDialogConfirm()
            is StationsEvent.DismissAlertDialog -> onAlertDialogDismiss()
            is StationsEvent.UpdateSearchQuery -> onUpdateSearchQuery(eventType.input)
            is StationsEvent.PermissionsRequestAgain -> onPermissionsRequestAgain()
            else -> throw IllegalStateException("StationViewModel")
        }
    }

    var sortedStations by mutableStateOf<List<StationDto>>(emptyList())
    var searchQuery by mutableStateOf<String>("")


    private fun onUpdateSearchQuery(input: String) {
        searchQuery = input
    }

    fun onClearClick() {
        searchQuery = ""
    }

    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()

    private fun onOpenAlertDialogClicked() {
        _showDialog.value = true
    }

    private fun onAlertDialogConfirm() {
        _showDialog.value = false
    }

    private fun onAlertDialogDismiss() {
        _showDialog.value = false
    }

    private fun onLoadStations() = safeLaunch {
        setState(BaseViewState.Loading)
        val locationPermissionGranted = permissionsState.value.multiplePermissionsState?.allPermissionsGranted ?: false
        val locationsListFlow = getLocations(locationPermissionGranted)
        execute(getStations(locationPermissionGranted)) { stationListDto ->
            setState(
                BaseViewState.Data(
                    StationsViewState(
                        stationsData = stationListDto,
                        locationData = locationsListFlow
                    )
                )
            )
        }
    }

    private fun onAddOrRemoveFavorite(dto: StationDto) = safeLaunch {
        val params = UpdateStationFavorite.Params(dto)
        call(updateStationFavorite(params))
    }

    private fun onDeleteFavorite(id: Int) = safeLaunch {
        call(deleteFavorite(DeleteStationFavorite.Params(id))) {
            onTriggerEvent(StationsEvent.LoadFavorites)
        }
    }

    private fun onPermissionsRequestAgain() {
        permissionsHandler.TriggerEvent(PermissionsHandler.PermissionHandlerEvent.PermissionsRequestAgain)
    }
}