package com.ngapp.metanmobile.presentation.stations.detail.view

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.extension.createLocationIntent
import com.ngapp.metanmobile.app.theme.*
import com.ngapp.metanmobile.app.widget.ButtonWithIcon
import com.ngapp.metanmobile.data.model.dto.station.StationDto

@Composable
fun StationDetailFooterView(
    station: StationDto,
) {

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MetanMobileColors.cardBackgroundColor)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {

        ButtonWithIcon(
            icon = Icons.Filled.Navigation,
            iconTint = White,
            buttonText = R.string.button_navigate,
            buttonBackgroundColor = Blue,
            fontColor = White,
            borderStrokeColor = Blue,
            modifier = Modifier.padding(vertical = 18.dp),
            onClick = { context.createLocationIntent(station) }
        )
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
fun StationDetailFooterPreiew() {
    MetanMobileTheme {
        Surface(color = MetanMobileColors.background) {
            StationDetailFooterView(
                StationDto.init()
            )
        }
    }
}