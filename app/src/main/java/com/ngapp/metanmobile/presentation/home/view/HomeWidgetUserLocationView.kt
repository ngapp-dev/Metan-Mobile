package com.ngapp.metanmobile.presentation.home.view

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ngapp.metanmobile.app.widget.WidgetPriceAndLocationView
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.extension.shortFormatUnixDataToString
import com.ngapp.metanmobile.app.theme.*
import com.ngapp.metanmobile.data.model.dto.profile.ProfileDto
import com.ngapp.metanmobile.data.model.dto.station.StationDto

@Composable
fun HomeWidgetUserLocationView(
    modifier: Modifier = Modifier,
    stations: List<StationDto>?,
    profile: ProfileDto?,
    locationPermissionGranted: Boolean,
    isGoogleServicesAvailable: Boolean,
    onStationDetailClick: (String) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .padding(vertical = 5.dp),
        elevation = 4.dp,
        shape = MetanMobileShapes.large
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(shape = MetanMobileShapes.large)
                .background(color = Blue)
                .animateContentSize()
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .padding(bottom = 12.dp)
            ) {
                Text(
                    text = stringResource(
                        id = R.string.text_hello_user,
                        profile?.firstName ?: "User"
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier,
                    color = White,
                    style = MetanMobileTypography.h1
                )
                Text(
                    text = stringResource(
                        id = R.string.text_today_date,
                        shortFormatUnixDataToString(System.currentTimeMillis() / 1000)
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 8.dp),
                    color = White,
                    style = MetanMobileTypography.h3
                )
                PriceAndLocationView(
                    modifier,
                    stations,
                    locationPermissionGranted,
                    isGoogleServicesAvailable,
                    onStationDetailClick,
                )
            }
        }
    }
}

@Composable
fun PriceAndLocationView(
    modifier: Modifier = Modifier,
    stations: List<StationDto>?,
    locationPermissionGranted: Boolean,
    isGoogleServicesAvailable: Boolean,
    onStationDetailClick: (String) -> Unit = {},
) {
    val nearestStation = stations?.minBy { it.distanceBetween }
    WidgetPriceAndLocationView(
        modifier = Modifier.padding(top = 16.dp),
        station = nearestStation,
        isGoogleServicesAvailable = isGoogleServicesAvailable,
        onStationDetailClick = onStationDetailClick,
        locationPermissionGranted = locationPermissionGranted,
        backgroundColor = null
    )
}
