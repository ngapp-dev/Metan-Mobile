package com.ngapp.metanmobile.presentation.stations.list.view

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.ngapp.metanmobile.presentation.stations.list.StationsViewModel
import com.ngapp.metanmobile.presentation.stations.list.StationsViewState
import com.google.maps.android.compose.rememberCameraPositionState
import com.ngapp.framework.extension.isGoogleServicesAvailable
import com.ngapp.metanmobile.app.widget.alertdialogs.MapItemsFilterAlertDialog
import com.ngapp.metanmobile.data.model.dto.station.StationDto
import com.ngapp.metanmobile.presentation.stations.list.StationsEvent

@Composable
fun StationMapContent(
    viewModel: StationsViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    viewState: StationsViewState,
    selectItem: (String) -> Unit = {},
    locationPermissionGranted: Boolean
) {
    val context = LocalContext.current

    val center = LatLng(53.90309661691656, 27.55363993274304)
    val mapItems = viewState.stationsData

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(center, 10f)
    }

    val locationsItems by viewState.locationData.collectAsState(emptyList())

    runCatching {
        context.isGoogleServicesAvailable()
    }.onFailure {
        Toast.makeText(context, it.message.toString(), Toast.LENGTH_SHORT).show()
    }

    if (locationPermissionGranted) {
        val currentLocation =
            if (locationsItems.isNotEmpty()) locationsItems[locationsItems.size - 1] else null

        if (currentLocation != null) {
            LaunchedEffect(key1 = true) {
                val location = LatLng(currentLocation.latitude, currentLocation.longitude)
                cameraPositionState.animate(
                    update = CameraUpdateFactory.newCameraPosition(
                        CameraPosition(location, 15f, 0f, 0f)
                    )
                )
            }
        }
    }

    var filteredStations by remember { mutableStateOf(mapItems) }
    val showDialogState by viewModel.showDialog.collectAsState()

    viewModel.sortedStations = filteredStations

    MapItemsFilterAlertDialog(
        show = showDialogState,
        stationsItems = mapItems,
        onDismiss = { viewModel.onTriggerEvent(StationsEvent.DismissAlertDialog) },
        onConfirm = { selected ->
            viewModel.onTriggerEvent(StationsEvent.ConfirmAlertDialog)
            filteredStations = selected
        }
    )

    val searchedText = viewModel.searchQuery

    val stationsResult = if (searchedText.isEmpty()) {
        filteredStations
    } else {
        val resultList = mutableListOf<StationDto>()
        for (station in filteredStations) {
            if (station.title.contains(searchedText, true) ||
                (station.address.contains(searchedText, true))
            ) {
                resultList.add(station)
            }
        }
        resultList
    }

    Card(
        modifier = Modifier
            .animateContentSize()
            .padding(top = 5.dp),
        elevation = 1.dp,
        shape = RoundedCornerShape(
            bottomStart = 0.dp,
            bottomEnd = 0.dp,
            topStart = 12.dp,
            topEnd = 12.dp
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
        ) {
            GoogleMapView(
                modifier = Modifier
                    .matchParentSize()
                    .fillMaxHeight(),
                cameraPositionState = cameraPositionState,
                mapItems = stationsResult,
                locationPermissionGranted = locationPermissionGranted,
                onRequirePermissions = { viewModel.onTriggerEvent(StationsEvent.PermissionsRequestAgain) },
                onMyLocationClick = {
                    val currentLocation =
                        if (locationsItems.isNotEmpty()) locationsItems[locationsItems.size - 1] else null
                    if (currentLocation != null) {
                        val location =
                            LatLng(currentLocation.latitude, currentLocation.longitude)
                        cameraPositionState.move(
                            update = CameraUpdateFactory.newCameraPosition(
                                CameraPosition(location, 15f, 0f, 0f)
                            )
                        )
                    }
                },
                onDetailClick = selectItem,
            )
        }
    }
}

