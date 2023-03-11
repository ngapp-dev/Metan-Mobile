package com.ngapp.metanmobile.app.widget

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ngapp.metanmobile.data.model.dto.station.StationDto
import com.ngapp.metanmobile.app.theme.Blue
import com.ngapp.metanmobile.app.theme.MetanMobileTheme
import com.ngapp.metanmobile.app.theme.Red

@Composable
fun StationStatusView(station: StationDto) {
    Box {
        Row(
            modifier = Modifier.padding(horizontal = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            StationStatusDotView(station = station)
        }
    }
}

@Composable
fun StationStatusDotView(station: StationDto) {
    StationStatusDotContentView(
        isOperate = station.isOperate == 1,
        isNotOperate = station.isOperate == 0
    )
}

@Composable
private fun StationStatusDotContentView(
    isOperate: Boolean,
    isNotOperate: Boolean,
) {
    Spacer(
        Modifier
            .size(8.dp)
            .clip(CircleShape)
            .background(
                when {
                    isOperate -> Blue
                    isNotOperate -> Red
                    else -> Color.Gray
                }
            )
    )
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
fun StationStatusViewPreview() {
    MetanMobileTheme {
        StationStatusView(StationDto.init())
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
fun StationStatusDotViewPreview() {
    MetanMobileTheme {
        StationStatusDotView(StationDto.init())
    }
}