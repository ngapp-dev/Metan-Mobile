package com.ngapp.metanmobile.presentation.stations.list.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.LocationDisabled
import androidx.compose.material.icons.outlined.MyLocation
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.*
import com.google.maps.android.compose.*
import com.ngapp.framework.extension.orZero
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.extension.bitmapDescriptor
import com.ngapp.metanmobile.app.theme.MetanMobileColors
import com.ngapp.metanmobile.app.theme.MetanMobileTheme
import com.ngapp.metanmobile.app.theme.toolbarIconColor
import com.ngapp.metanmobile.data.model.dto.station.StationDto
import com.ngapp.metanmobile.data.parsing.StationType
import timber.log.Timber

@Composable
fun GoogleMapView(
    modifier: Modifier = Modifier,
    cameraPositionState: CameraPositionState = rememberCameraPositionState(),
    mapItems: List<StationDto>?,
    locationPermissionGranted: Boolean,
    onDetailClick: (String) -> Unit = {},
    onRequirePermissions: () -> Unit = {},
    onMyLocationClick: () -> Unit = {}
) {

    val uiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                compassEnabled = false,
                zoomControlsEnabled = false,
                myLocationButtonEnabled = false
            )
        )
    }

    val mapProperties by remember {
        mutableStateOf(
            MapProperties(
                mapType = MapType.NORMAL,
                isMyLocationEnabled = locationPermissionGranted
            )
        )
    }
    val mapVisible by remember { mutableStateOf(true) }

    if (mapVisible) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        if (!locationPermissionGranted) {
                            onRequirePermissions.invoke()
                        } else {
                            onMyLocationClick.invoke()
                        }
                    },
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = MaterialTheme.colors.onPrimary
                ) {
                    Icon(
                        imageVector = if (!locationPermissionGranted) {
                            Icons.Outlined.LocationDisabled
                        } else {
                            Icons.Outlined.MyLocation
                        },
                        contentDescription = "Add FAB",
                        tint = MetanMobileColors.toolbarIconColor,
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            isFloatingActionButtonDocked = false,
            content = { padding ->
                GoogleMap(
                    modifier = modifier,
                    cameraPositionState = cameraPositionState,
                    properties = mapProperties,
                    uiSettings = uiSettings,
                    onPOIClick = {
                        Timber.d("POI clicked: ${it.name}")
                    }
                ) {
                    mapItems?.forEach {
                        when {
                            it.type == StationType.CLFS.typeName && it.isOperate == 1 -> {
                                GoogleMapMarkerView(
                                    cameraPositionState = cameraPositionState,
                                    marker = it,
                                    iconResourceId = R.drawable.ic_station_clfs,
                                    onMarkerClick = onDetailClick
                                )
                            }
                            it.type == StationType.CNG.typeName && it.isOperate == 1 -> {
                                GoogleMapMarkerView(
                                    cameraPositionState = cameraPositionState,
                                    marker = it,
                                    iconResourceId = R.drawable.ic_station_cng,
                                    onMarkerClick = onDetailClick
                                )
                            }
                            it.type == StationType.SERVICE.typeName && it.isOperate == 1 -> {
                                GoogleMapMarkerView(
                                    cameraPositionState = cameraPositionState,
                                    marker = it,
                                    iconResourceId = R.drawable.ic_station_service,
                                    onMarkerClick = onDetailClick
                                )
                            }
                            else -> {
                                GoogleMapMarkerView(
                                    cameraPositionState = cameraPositionState,
                                    marker = it,
                                    iconResourceId = R.drawable.ic_station_not_working,
                                    onMarkerClick = onDetailClick
                                )
                            }
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun GoogleMapMarkerView(
    modifier: Modifier = Modifier,
    cameraPositionState: CameraPositionState,
    marker: StationDto,
    @DrawableRes iconResourceId: Int,
    onMarkerClick: (String) -> Unit = {},
) {
    val icon = bitmapDescriptor(
        LocalContext.current, iconResourceId
    )

    val markerClick: (Marker) -> Boolean = {
        cameraPositionState.projection?.let { projection ->
            onMarkerClick.invoke(it.snippet.orEmpty())
        }
        false
    }
    MarkerInfoWindowContent(
        state = MarkerState(
            position = LatLng(
                marker.latitude?.toDouble().orZero(),
                marker.longitude?.toDouble().orZero()
            )
        ),
        icon = icon,
        snippet = marker.code,
        title = marker.title,
        onClick = markerClick,
        draggable = true,
    ) {
        Text(it.title ?: "Title", color = Color.Red)
    }
}

@Preview
@Composable
fun GoogleMapViewPreview() {
    MetanMobileTheme {
        GoogleMapView(
            Modifier.fillMaxSize(),
            mapItems = listOf(StationDto.init()),
            locationPermissionGranted = false
        )
    }
}


@Composable
fun FabButton() {

}