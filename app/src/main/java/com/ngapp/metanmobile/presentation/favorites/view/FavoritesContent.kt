package com.ngapp.metanmobile.presentation.favorites.view

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.ngapp.framework.extension.isGoogleServicesAvailable
import com.ngapp.framework.extension.openGoogleServicesPlayStore
import com.ngapp.metanmobile.app.widget.alertdialogs.StationsSortAndFilterAlertDialog
import com.ngapp.metanmobile.data.model.dto.station.StationDto
import com.ngapp.metanmobile.presentation.favorites.FavoritesEvent
import com.ngapp.metanmobile.presentation.favorites.FavoritesViewModel
import com.ngapp.metanmobile.presentation.favorites.FavoritesViewState
import kotlinx.coroutines.launch

@Composable
fun FavoritesContent(
    viewModel: FavoritesViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    viewState: FavoritesViewState,
    selectedStationFavorite: MutableState<StationDto>,
    locationPermissionGranted: Boolean,
    onDetailItemClick: (String) -> Unit = {},
    onDeleteItemClick: (String) -> Unit = {}
) {
    val context = LocalContext.current

    val favoriteStationsItems = viewState.favoriteStationList
    val locationsItems by viewState.locationData.collectAsState(emptyList())
    val currentLocation =
        if (locationsItems.isNotEmpty()) locationsItems[locationsItems.size - 1] else null

    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    var sortedFavoriteStations by remember { mutableStateOf(favoriteStationsItems.sortedByDescending { it.title }) }
    val showDialogState: Boolean by viewModel.showDialog.collectAsState()
    var isGoogleServicesAvailable by remember { mutableStateOf(true) }

    viewModel.sortedStations = sortedFavoriteStations

    runCatching {
        context.isGoogleServicesAvailable()
    }.onFailure {
        isGoogleServicesAvailable = false
        Toast.makeText(context, it.message.toString(), Toast.LENGTH_SHORT).show()
    }

    StationsSortAndFilterAlertDialog(
        show = showDialogState,
        stationsItems = favoriteStationsItems,
        currentLocation = currentLocation,
        locationPermissionGranted = locationPermissionGranted,
        onDismiss = { viewModel.onTriggerEvent(FavoritesEvent.DismissAlertDialog) },
        onConfirm = { selected ->
            viewModel.onTriggerEvent(FavoritesEvent.ConfirmAlertDialog)
            sortedFavoriteStations = selected
            coroutineScope.launch {
                listState.animateScrollToItem(index = 0)
            }
        }
    )

    LazyColumn(
        contentPadding = paddingValues,
        modifier = Modifier.fillMaxSize(),
    ) {
        item {
            FavoritesBodyView(
                modifier = Modifier.fillMaxHeight(),
                sortedFavoriteStations = sortedFavoriteStations,
                locationPermissionGranted = locationPermissionGranted,
                isGoogleServicesAvailable = isGoogleServicesAvailable,
                onPermissionRequestAgain = { viewModel.onTriggerEvent(FavoritesEvent.PermissionsRequestAgain) },
                onGoogleServicesRequest = { context.openGoogleServicesPlayStore() },
                onDetailClick = onDetailItemClick,
                onDeleteClick = {
                    selectedStationFavorite.value = it
                    onDeleteItemClick.invoke(it.code)
                }
            )
        }
    }
}