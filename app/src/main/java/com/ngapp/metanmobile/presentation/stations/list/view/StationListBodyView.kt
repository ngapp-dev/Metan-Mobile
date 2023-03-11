package com.ngapp.metanmobile.presentation.stations.list.view

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ngapp.metanmobile.app.ads.ListBannersAds
import com.ngapp.metanmobile.app.component.ContentBottomExpanderView
import com.ngapp.metanmobile.app.widget.MetanMobileDivider
import com.ngapp.metanmobile.data.model.dto.station.StationDto
import com.ngapp.metanmobile.presentation.stations.list.StationsViewModel

@Composable
fun StationListBodyView(
    modifier: Modifier = Modifier,
    viewModel: StationsViewModel = hiltViewModel(),
    sortedStations: List<StationDto>,
    locationPermissionGranted: Boolean,
    isGoogleServicesAvailable: Boolean,
    onPermissionRequestAgain: () -> Unit = {},
    onGoogleServicesRequest: () -> Unit = {},
    onDetailClick: (String) -> Unit = {},
) {
    val searchedText = viewModel.searchQuery

    val stationsResult = if (searchedText.isEmpty()) {
        sortedStations
    } else {
        val resultList = mutableListOf<StationDto>()
        for (station in sortedStations) {
            if (station.title.contains(searchedText, true) ||
                (station.address.contains(searchedText, true))
            ) {
                resultList.add(station)
            }
        }
        resultList
    }
    if (stationsResult.isNotEmpty()) {
        Card(
            modifier = modifier.padding(top = 5.dp),
            elevation = 1.dp,
            shape = RoundedCornerShape(
                bottomStart = 0.dp,
                bottomEnd = 0.dp,
                topStart = 12.dp,
                topEnd = 12.dp
            )
        ) {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 18.dp)
                ) {
                    stationsResult.let { sortedStations ->
                        sortedStations.forEachIndexed { i, station ->
                            Box(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column {
                                    if (i % 8 == 0 && i != 0) {
                                        ListBannersAds()
                                        MetanMobileDivider(
                                            modifier = Modifier.padding(
                                                horizontal = 16.dp
                                            )
                                        )
                                    }
                                    StationRow(
                                        station = station,
                                        locationPermissionGranted = locationPermissionGranted,
                                        isGoogleServicesAvailable = isGoogleServicesAvailable,
                                        onPermissionRequestAgain = onPermissionRequestAgain,
                                        onGoogleServicesRequest = onGoogleServicesRequest,
                                        onDetailClick = { onDetailClick.invoke(station.code) }
                                    )
                                    if (i < sortedStations.size - 1) {
                                        MetanMobileDivider(
                                            modifier = Modifier.padding(
                                                horizontal = 16.dp
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        ContentBottomExpanderView()
    }
}