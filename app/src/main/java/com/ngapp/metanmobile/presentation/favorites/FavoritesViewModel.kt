@file:OptIn(ExperimentalPermissionsApi::class)

package com.ngapp.metanmobile.presentation.favorites

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
import com.ngapp.metanmobile.domain.usecase.station.favorite.DeleteStationFavorite
import com.ngapp.metanmobile.domain.usecase.station.favorite.GetStationFavorites
import com.ngapp.metanmobile.domain.usecase.station.favorite.UpdateStationFavorite
import com.ngapp.metanmobile.presentation.home.HomeViewState
import com.ngapp.metanmobile.presentation.stations.list.StationsEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val updateStationFavorite: UpdateStationFavorite,
    private val getStationFavorites: GetStationFavorites,
    private val deleteStationFavorite: DeleteStationFavorite,
    private val getLocations: GetLocationsFlow,
    val permissionsHandler: PermissionsHandler
) : MviViewModel<BaseViewState<FavoritesViewState>, FavoritesEvent>() {

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

    override fun onTriggerEvent(eventType: FavoritesEvent) {
        when (eventType) {
            is FavoritesEvent.AddOrRemoveStationFavorite -> onAddOrRemoveStationFavorite(eventType.stationDto)
            is FavoritesEvent.DeleteStationFavorite -> onDeleteStationFavorite(eventType.id)
            is FavoritesEvent.LoadStationFavorites -> onLoadStationFavorites()
            is FavoritesEvent.OpenAlertDialog -> onOpenAlertDialogClicked()
            is FavoritesEvent.ConfirmAlertDialog -> onAlertDialogConfirm()
            is FavoritesEvent.DismissAlertDialog -> onAlertDialogDismiss()
            is FavoritesEvent.UpdateSearchQuery -> onUpdateSearchQuery(eventType.input)
            is FavoritesEvent.PermissionsRequestAgain -> onPermissionsRequestAgain()
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

    private  fun onAlertDialogConfirm() {
        _showDialog.value = false
    }

    private fun onAlertDialogDismiss() {
        _showDialog.value = false
    }

    private fun onAddOrRemoveStationFavorite(dto: StationDto) = safeLaunch {
        val params = UpdateStationFavorite.Params(dto)
        call(updateStationFavorite(params))
    }

    private fun onLoadStationFavorites() = safeLaunch {
        setState(BaseViewState.Loading)
        val locationPermissionGranted = permissionsState.value.multiplePermissionsState?.allPermissionsGranted ?: false
        val locationsListFlow = getLocations(locationPermissionGranted)
        call(getStationFavorites(locationPermissionGranted)) {
            if (it.isEmpty()) {
                setState(BaseViewState.Empty)
            } else {
                setState(BaseViewState.Data(FavoritesViewState(favoriteStationList = it, locationData = locationsListFlow)))
            }
        }
    }

    private fun onDeleteStationFavorite(id: Int) = safeLaunch {
        call(deleteStationFavorite(DeleteStationFavorite.Params(id))) {
            onTriggerEvent(FavoritesEvent.LoadStationFavorites)
        }
    }

    private fun onPermissionsRequestAgain() {
        permissionsHandler.TriggerEvent(PermissionsHandler.PermissionHandlerEvent.PermissionsRequestAgain)
    }
}