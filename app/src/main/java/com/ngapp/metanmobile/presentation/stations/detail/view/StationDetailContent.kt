package com.ngapp.metanmobile.presentation.stations.detail.view

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ngapp.framework.extension.isGoogleServicesAvailable
import com.ngapp.metanmobile.app.theme.*
import com.ngapp.metanmobile.presentation.stations.detail.StationDetailViewState
import com.ngapp.metanmobile.app.widget.WidgetPriceAndLocationView
import com.ngapp.metanmobile.presentation.stations.detail.StationDetailViewModel
import com.ngapp.metanmobile.provider.NavigationProvider

@Composable
fun StationDetailContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    viewModel: StationDetailViewModel = hiltViewModel(),
    viewState: StationDetailViewState,
    listState: LazyListState = rememberLazyListState(),
    locationPermissionGranted: Boolean,
    selectNewsItem: (Int) -> Unit = {}
) {
    val stationItem = viewState.stationData
    var isGoogleServicesAvailable by remember { mutableStateOf(true) }

    viewModel.shareStationDto = stationItem

    runCatching {
        LocalContext.current.isGoogleServicesAvailable()
    }.onFailure {
        isGoogleServicesAvailable = false
        Toast.makeText(LocalContext.current, it.message.toString(), Toast.LENGTH_SHORT).show()
    }

    LazyColumn(
        state = listState,
        contentPadding = paddingValues,
        modifier = Modifier
            .fillMaxSize()
            .background(MetanMobileColors.cardBackgroundColor)
    ) {
        stationItem?.let { station ->
            item("contentImage") {
                StationDetailImageView(station = station)
            }
            item("contentHeader") {
                StationDetailHeaderView(station = station)
            }
            item("contentBody") {
                StationDetailBodyView(station = station)
            }
            item("widgetPriceAndLocation") {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize(),
                    elevation = 0.dp,
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                ) {
                    WidgetPriceAndLocationView(
                        station = station,
                        locationPermissionGranted = locationPermissionGranted,
                        isGoogleServicesAvailable = isGoogleServicesAvailable,
                        backgroundColor = MetanMobileColors.smallWidgetBackgroundColor,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp)
                    )
                }
            }
            item("contentRelatedNews") {
                StationDetailRelatedNewsView(
                    relatedNews = station.relatedNewsDtoList,
                    onDetailClick = selectNewsItem
                )
            }
            item("contentFooter") {
                StationDetailFooterView(station = station)
            }
        }
    }
}