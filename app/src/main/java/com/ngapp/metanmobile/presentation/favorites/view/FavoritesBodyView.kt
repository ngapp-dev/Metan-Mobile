package com.ngapp.metanmobile.presentation.favorites.view

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ngapp.metanmobile.app.ads.ListBannersAds
import com.ngapp.metanmobile.app.component.ContentBottomExpanderView
import com.ngapp.metanmobile.app.theme.MetanMobileTheme
import com.ngapp.metanmobile.app.widget.MetanMobileDivider
import com.ngapp.metanmobile.data.model.dto.station.StationDto
import com.ngapp.metanmobile.presentation.favorites.FavoritesViewModel

@Composable
fun FavoritesBodyView(
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel = hiltViewModel(),
    sortedFavoriteStations: List<StationDto>,
    locationPermissionGranted: Boolean,
    isGoogleServicesAvailable: Boolean,
    onPermissionRequestAgain: () -> Unit = {},
    onGoogleServicesRequest: () -> Unit = {},
    onDetailClick: (String) -> Unit = {},
    onDeleteClick: (StationDto) -> Unit = {},
) {
    val searchedText = viewModel.searchQuery

    val stationsResult = if (searchedText.isEmpty()) {
        sortedFavoriteStations
    } else {
        val resultList = mutableListOf<StationDto>()
        for (station in sortedFavoriteStations) {
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
            modifier = modifier
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
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 18.dp)
                ) {
                    stationsResult.let { sortedFavoriteStations ->
                        sortedFavoriteStations.forEachIndexed { i, station ->
                            Column(modifier = Modifier.fillMaxWidth()) {
                                if (i % 8 == 0 && i != 0) {
                                    ListBannersAds()
                                    MetanMobileDivider(
                                        modifier = Modifier.padding(
                                            horizontal = 16.dp
                                        )
                                    )
                                }
                                FavoriteRow(
                                    station = station,
                                    locationPermissionGranted = locationPermissionGranted,
                                    isGoogleServicesAvailable = isGoogleServicesAvailable,
                                    onPermissionRequestAgain = onPermissionRequestAgain,
                                    onGoogleServicesRequest = onGoogleServicesRequest,
                                    onDetailClick = { onDetailClick.invoke(station.code) },
                                    onDeleteClick = { onDeleteClick.invoke(station) }
                                )
                                if (i < sortedFavoriteStations.size - 1) {
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
        ContentBottomExpanderView()
    }
}

@Preview(
    showBackground = true,
    name = "Light Mode"
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode"
)
@Composable
fun StationListBodyViewPreview() {
    MetanMobileTheme {
        FavoritesBodyView(
            sortedFavoriteStations = emptyList(),
            locationPermissionGranted = false,
            isGoogleServicesAvailable = true
        )
    }
}