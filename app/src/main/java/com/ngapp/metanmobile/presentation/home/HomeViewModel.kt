@file:OptIn(ExperimentalPermissionsApi::class)

package com.ngapp.metanmobile.presentation.home

import androidx.lifecycle.viewModelScope
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.ngapp.framework.base.mvi.BaseViewState
import com.ngapp.framework.base.mvi.MviViewModel
import com.ngapp.metanmobile.app.component.PermissionsHandler
import com.ngapp.metanmobile.domain.usecase.faq.GetFaqFlow
import com.ngapp.metanmobile.domain.usecase.location.GetLocationsFlow
import com.ngapp.metanmobile.domain.usecase.news.GetNews
import com.ngapp.metanmobile.domain.usecase.station.GetStationsFlow
import com.ngapp.metanmobile.domain.usecase.profile.GetProfileFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNews: GetNews,
    private val getFaq: GetFaqFlow,
    private val getProfile: GetProfileFlow,
    private val getStations: GetStationsFlow,
    val permissionsHandler: PermissionsHandler
) : MviViewModel<BaseViewState<HomeViewState>, HomeEvent>() {

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

    override fun onTriggerEvent(eventType: HomeEvent) {
        when (eventType) {
            is HomeEvent.LoadHome -> onLoadHome()
            is HomeEvent.PermissionRequired -> onPermissionRequired()
            is HomeEvent.PermissionsRequestAgain -> onPermissionsRequestAgain()
        }
    }

    private fun onLoadHome() = safeLaunch {
        setState(BaseViewState.Loading)
        val profileFlow = getProfile(Unit)
        val faqListFlow = getFaq(Unit)
        val locationPermissionGranted =
            permissionsState.value.multiplePermissionsState?.allPermissionsGranted ?: false
        val stationsListFlow = getStations(locationPermissionGranted)
        execute(getNews(Unit)) { lastNewsListDto ->
            setState(
                BaseViewState.Data(
                    HomeViewState(
                        faqData = faqListFlow,
                        profileData = profileFlow,
                        newsData = lastNewsListDto,
                        stationsData = stationsListFlow
                    )
                )
            )
        }
    }

    private fun onPermissionRequired() {
        permissionsHandler.TriggerEvent(PermissionsHandler.PermissionHandlerEvent.PermissionRequired)
    }

    private fun onPermissionsRequestAgain() {
        permissionsHandler.TriggerEvent(PermissionsHandler.PermissionHandlerEvent.PermissionsRequestAgain)
    }
}