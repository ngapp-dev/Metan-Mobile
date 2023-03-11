package com.ngapp.metanmobile.app.widget

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ngapp.framework.extension.openGoogleServicesPlayStore
import com.ngapp.metanmobile.data.model.dto.station.StationDto
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.theme.*
import com.ngapp.metanmobile.presentation.home.HomeEvent
import com.ngapp.metanmobile.presentation.home.HomeViewModel

@Composable
fun WidgetPriceAndLocationView(
    modifier: Modifier = Modifier,
    station: StationDto?,
    locationPermissionGranted: Boolean,
    isGoogleServicesAvailable: Boolean,
    backgroundColor: Color?,
    onStationDetailClick: (String) -> Unit = {}
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Frame(
            station = station,
            locationPermissionGranted = locationPermissionGranted,
            isGoogleServicesAvailable = isGoogleServicesAvailable,
            onStationDetailClick = onStationDetailClick,
            backgroundColor = backgroundColor
        )
    }
}

@Composable
fun Frame(
    station: StationDto?,
    locationPermissionGranted: Boolean,
    isGoogleServicesAvailable: Boolean,
    backgroundColor: Color?,
    onStationDetailClick: (String) -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            8.dp, alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
    ) {
        LeftWidgetDataView(
            modifier = Modifier
                .weight(weight = 0.5f)
                .height(96.dp),
            backgroundColor = backgroundColor
        )
        RightWidgetDataView(
            modifier = Modifier
                .weight(weight = 0.5f)
                .height(96.dp),
            station = station,
            locationPermissionGranted = locationPermissionGranted,
            isGoogleServicesAvailable = isGoogleServicesAvailable,
            onStationDetailClick = onStationDetailClick,
            backgroundColor = backgroundColor
        )
    }
}

@Composable
fun LeftWidgetDataView(
    modifier: Modifier = Modifier,
    backgroundColor: Color? = null
) {
    LeftWidgetView(modifier = modifier, backgroundColor = backgroundColor)
}

@Composable
fun RightWidgetDataView(
    modifier: Modifier = Modifier,
    station: StationDto?,
    locationPermissionGranted: Boolean,
    isGoogleServicesAvailable: Boolean,
    onStationDetailClick: (String) -> Unit = {},
    backgroundColor: Color?
) {
    RightWidgetView(
        modifier = modifier,
        station = station,
        locationPermissionGranted = locationPermissionGranted,
        isGoogleServicesAvailable = isGoogleServicesAvailable,
        onStationDetailClick = onStationDetailClick,
        backgroundColor = backgroundColor
    )
}

@Composable
fun LeftWidgetView(
    modifier: Modifier = Modifier,
    backgroundColor: Color?
) {
    Card(
        modifier = modifier.apply { if (backgroundColor != null) background(backgroundColor) },
        elevation = 1.dp,
        shape = MetanMobileShapes.large
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = modifier.clip(MetanMobileShapes.large)
        ) {
            Column(
                modifier = modifier.padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier
                ) {
                    Text(
                        text = stringResource(id = R.string.text_value_byn, "1.06"),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MetanMobileTypography.h1,
                        modifier = Modifier
                    )
                }
                Text(
                    text = stringResource(R.string.text_cng_price),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }
    }
}

@Composable
fun RightWidgetView(
    modifier: Modifier = Modifier,
    station: StationDto?,
    locationPermissionGranted: Boolean,
    isGoogleServicesAvailable: Boolean,
    onStationDetailClick: (String) -> Unit = {},
    backgroundColor: Color?,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    Card(
        modifier = modifier.apply { if (backgroundColor != null) background(backgroundColor) },
        elevation = 1.dp,
        shape = MetanMobileShapes.large
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = modifier
                .clip(MetanMobileShapes.large)
                .clickable { onStationDetailClick.invoke(station?.code.orEmpty()) },

        ) {
            Column(modifier = modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
                if (!locationPermissionGranted) {
                    RequestPermissionOrGoogleServices(
                        modifier,
                        R.string.permission_text_denied,
                        R.string.permission_button_request
                    ) {
                        viewModel.onTriggerEvent(HomeEvent.PermissionsRequestAgain)
                    }
                } else if (!isGoogleServicesAvailable) {
                    RequestPermissionOrGoogleServices(
                        modifier,
                        R.string.text_google_service_unavailable,
                        R.string.button_google_service_update
                    ) { context.openGoogleServicesPlayStore() }
                } else {
                    if (station == null || station.distanceBetween == 0.0) {
                        LoadingView()
                    } else {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                        ) {

                            val distanceBetween =
                                String.format("%.1f", station.distanceBetween).replace('.', ',')
                            Text(
                                text = stringResource(id = R.string.text_value_km, distanceBetween),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MetanMobileTypography.h1,
                                modifier = Modifier
                            )
                            StationStatusView(station)
                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.weight(weight = 1f)) {
                                Text(
                                    text = stringResource(id = R.string.text_nearest_station),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    style = MaterialTheme.typography.h4,
                                    modifier = Modifier.padding(top = 2.dp)
                                )
                                Text(
                                    text = station.address,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    style = MaterialTheme.typography.h4,
                                    modifier = Modifier.padding(top = 2.dp)
                                )
                            }
                        }
                        IconButton(
                            modifier = Modifier,
                            onClick = { onStationDetailClick.invoke(station.code) }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Navigation,
                                contentDescription = "navigate_icon",
                                tint = MetanMobileColors.toolbarIconColor
                            )
                        }
                    }
                }
            }
        }
    }
}