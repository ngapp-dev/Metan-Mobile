package com.ngapp.metanmobile.presentation.stations.list.view

import android.content.res.Configuration
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ngapp.metanmobile.app.theme.Gray500
import com.ngapp.metanmobile.app.theme.MetanMobileTheme
import com.ngapp.metanmobile.app.theme.Red
import com.ngapp.metanmobile.data.model.dto.station.StationDto
import com.ngapp.metanmobile.presentation.stations.list.StationsEvent
import com.ngapp.metanmobile.presentation.stations.list.StationsViewModel

@Composable
fun StationFavButton(
    viewModel: StationsViewModel = hiltViewModel(),
    station: StationDto
) {
    var isFavorite by rememberSaveable(station) { mutableStateOf(station.isFavorite) }

    IconButton(onClick = {
        isFavorite = !isFavorite
        station.isFavorite = isFavorite
        viewModel.onTriggerEvent(StationsEvent.AddOrRemoveFavorite(station))
    }) {
        val tintColor = if (isFavorite) Red else Gray500
        Icon(
            painter = rememberVectorPainter(Icons.Default.Favorite),
            contentDescription = null,
            tint = tintColor
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Light Mode"
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode"
)
@Composable
fun StationFavoriteButtonPreview() {
    MetanMobileTheme {
        StationFavButton(
            station = StationDto.init()
        )
    }
}