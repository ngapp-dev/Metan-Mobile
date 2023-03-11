@file:OptIn(ExperimentalPermissionsApi::class)

package com.ngapp.metanmobile.presentation.home

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.ngapp.metanmobile.app.component.PermissionsHandler
import com.ngapp.metanmobile.data.model.dto.faq.FaqDto
import com.ngapp.metanmobile.data.model.dto.location.LocationDto
import com.ngapp.metanmobile.data.model.dto.news.NewsDto
import com.ngapp.metanmobile.data.model.dto.profile.ProfileDto
import com.ngapp.metanmobile.data.model.dto.station.StationDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeViewState(
    val newsData: List<NewsDto> = emptyList(),
    val faqData: Flow<List<FaqDto>> = emptyFlow(),
    val profileData: Flow<List<ProfileDto>> = emptyFlow(),
    val stationsData: Flow<List<StationDto>> = emptyFlow(),

    val permissionRequestInFlight: Boolean = false,
    val hasLocationPermission: Boolean = false,
    val multiplePermissionsState: MultiplePermissionsState? = null,
    val permissionAction: PermissionsHandler.Action = PermissionsHandler.Action.NO_ACTION
)

sealed class HomeEvent {
    object LoadHome : HomeEvent()
    object PermissionRequired : HomeEvent()
    object PermissionsRequestAgain : HomeEvent()
}