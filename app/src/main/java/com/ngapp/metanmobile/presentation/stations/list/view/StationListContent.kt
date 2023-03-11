package com.ngapp.metanmobile.presentation.stations.list.view

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.ngapp.framework.extension.isGoogleServicesAvailable
import com.ngapp.framework.extension.openAppOnPlayStore
import com.ngapp.framework.extension.openGoogleServicesPlayStore
import com.ngapp.metanmobile.presentation.stations.list.StationsViewModel
import com.ngapp.metanmobile.presentation.stations.list.StationsViewState
import com.ngapp.metanmobile.app.widget.alertdialogs.StationsSortAndFilterAlertDialog
import com.ngapp.metanmobile.presentation.stations.list.StationsEvent
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StationListContent(
    viewModel: StationsViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    viewState: StationsViewState,
    locationPermissionGranted: Boolean,
    selectItem: (String) -> Unit = {},
) {
    val context = LocalContext.current

    val stationsItems = viewState.stationsData
    val locationsItems by viewState.locationData.collectAsState(emptyList())

    val currentLocation =
        if (locationsItems.isNotEmpty()) locationsItems[locationsItems.size - 1] else null

    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    var sortedStations by remember { mutableStateOf(stationsItems.sortedBy { it.title }) }
    val showDialogState: Boolean by viewModel.showDialog.collectAsState()
    var isGoogleServicesAvailable by remember { mutableStateOf(true) }

    viewModel.sortedStations = sortedStations

    runCatching {
        context.isGoogleServicesAvailable()
    }.onFailure {
        isGoogleServicesAvailable = false
        Toast.makeText(context, it.message.toString(), Toast.LENGTH_SHORT).show()
    }

    StationsSortAndFilterAlertDialog(
        show = showDialogState,
        stationsItems = stationsItems,
        currentLocation = currentLocation,
        locationPermissionGranted = locationPermissionGranted,
        onDismiss = { viewModel.onTriggerEvent(StationsEvent.DismissAlertDialog) },
        onConfirm = { selected ->
            viewModel.onTriggerEvent(StationsEvent.ConfirmAlertDialog)
            sortedStations = selected
            coroutineScope.launch {
                listState.animateScrollToItem(index = 0)
            }
        }
    )
    LazyColumn(
        state = listState,
        contentPadding = paddingValues,
        modifier = Modifier.fillMaxSize(),
    ) {
        item {
            StationListBodyView(
                modifier = Modifier
                    .fillMaxSize()
                    .animateItemPlacement(),
                viewModel = viewModel,
                sortedStations = sortedStations,
                locationPermissionGranted = locationPermissionGranted,
                isGoogleServicesAvailable = isGoogleServicesAvailable,
                onPermissionRequestAgain = { viewModel.onTriggerEvent(StationsEvent.PermissionsRequestAgain) },
                onGoogleServicesRequest = { context.openGoogleServicesPlayStore() },
                onDetailClick = selectItem,
            )
        }
    }
}