package com.ngapp.metanmobile.presentation.stations.list.view

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.theme.MetanMobileTheme
import com.ngapp.metanmobile.app.theme.MetanMobileTypography

@Composable
fun StationListHeaderView(
    @StringRes titleResId: Int,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
            .height(40.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(titleResId),
            textAlign = TextAlign.Left,
            modifier = Modifier.fillMaxWidth()
                .padding(start = 16.dp),
            style = MetanMobileTypography.h2
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
fun StationListHeaderViewPreview() {
    MetanMobileTheme {
        StationListHeaderView(R.string.title_station_list)
    }
}