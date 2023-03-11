package com.ngapp.metanmobile.presentation.home.view

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.ngapp.framework.extension.isGoogleServicesAvailable
import com.ngapp.jetframework.rememberFlowWithLifecycle
import com.ngapp.metanmobile.presentation.home.HomeViewModel
import com.ngapp.metanmobile.presentation.home.HomeViewState

@Composable
fun HomeContent(
    viewModel: HomeViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    viewState: HomeViewState,
    selectItem: (Int) -> Unit = {},
    onShowAllNewsClick: () -> Unit = {},
    onSeeAllNewsClick: () -> Unit = {},
    onStationDetailClick: (String) -> Unit = {},
    locationPermissionGranted: Boolean
) {

    val lastNewsItems = viewState.newsData.filter { it.title != "" && it.isPinned == 0 }.take(3)
    val pinnedNewsItems = viewState.newsData.filter { it.isPinned == 1 }
    val pinnedFaqItems =
        rememberFlowWithLifecycle(viewState.faqData).collectAsState(emptyList()).value.filter { it.isPinned == 1 }
            .take(3)
    val stationsItems by rememberFlowWithLifecycle(viewState.stationsData).collectAsState(null)
    val profileItem by viewState.profileData.collectAsState(emptyList())
    val profile = if (profileItem.isNotEmpty()) profileItem[profileItem.size - 1] else null
    var isGoogleServicesAvailable by remember { mutableStateOf(true) }

    runCatching {
        LocalContext.current.isGoogleServicesAvailable()
    }.onFailure {
        isGoogleServicesAvailable = false
        Toast.makeText(LocalContext.current, it.message.toString(), Toast.LENGTH_SHORT).show()
    }

    LazyColumn(
        contentPadding = paddingValues,
        modifier = Modifier.fillMaxSize()
    ) {
        item("contentHeader") {
            HomeHeaderView(
                viewModel = viewModel,
                lastNewsItems = lastNewsItems,
                pinnedNews = pinnedNewsItems,
                onShowAllNewsClick = onShowAllNewsClick,
                selectItem = { selectItem(it) }
            )
        }
        item("contentWidgetUserLocation") {
            HomeWidgetUserLocationView(
                stations = stationsItems,
                profile = profile,
                onStationDetailClick = { onStationDetailClick(it) },
                locationPermissionGranted = locationPermissionGranted,
                isGoogleServicesAvailable = isGoogleServicesAvailable
            )
        }
        item("contentCalculators") {
            HomeWidgetCalculatorsView()
        }
        item("contentFqa") {
            HomeWidgetFaqView(
                pinnedFaqItems = pinnedFaqItems,
                onSeeAllClick = { onSeeAllNewsClick() }
            )
        }
    }
}