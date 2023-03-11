package com.ngapp.metanmobile.presentation.stations.list.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.component.ProgressIndicatorSmall
import com.ngapp.metanmobile.app.theme.*
import com.ngapp.metanmobile.app.widget.StationStatusView
import com.ngapp.metanmobile.data.model.dto.station.StationDto

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StationRow(
    station: StationDto,
    locationPermissionGranted: Boolean,
    isGoogleServicesAvailable: Boolean,
    onPermissionRequestAgain: () -> Unit = {},
    onGoogleServicesRequest: () -> Unit = {},
    onDetailClick: () -> Unit = {},
) {
    Card(
        onClick = onDetailClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(86.dp)
            .padding(vertical = 4.dp),
        elevation = 0.dp,
        shape = RoundedCornerShape(
            bottomStart = 0.dp,
            bottomEnd = 0.dp,
            topStart = 0.dp,
            topEnd = 0.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(station.previewPicture)
                    .crossfade(true)
                    .build(),
                error = painterResource(R.drawable.logo_solid_mono_invert),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .size(82.dp)
                    .padding(vertical = 8.dp)
                    .padding(end = 8.dp)
                    .clip(MetanMobileShapes.large)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 4.dp)
            ) {

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = station.title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier,
                        style = MetanMobileTypography.h6
                    )
                    StationStatusView(station)
                }
                Text(
                    text = station.address,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 2.dp, end = 8.dp),
                    style = MetanMobileTypography.subtitle1
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (!locationPermissionGranted) {
                        CompositionLocalProvider(
                            LocalMinimumTouchTargetEnforcement provides false,
                        ) {
                            IconButton(
                                onClick = { onPermissionRequestAgain.invoke() },
                                modifier = Modifier.padding(all = 0.dp)
                            ) {
                                Icon(
                                    painter = rememberVectorPainter(Icons.Default.LocationOff),
                                    contentDescription = null,
                                    tint = Gray500
                                )
                            }
                        }
                    } else if (!isGoogleServicesAvailable) {
                        CompositionLocalProvider(
                            LocalMinimumTouchTargetEnforcement provides false,
                        ) {
                            IconButton(
                                onClick = { onGoogleServicesRequest.invoke() },
                                modifier = Modifier.padding(all = 0.dp)
                            ) {
                                Icon(
                                    painter = rememberVectorPainter(Icons.Default.LocationOff),
                                    contentDescription = null,
                                    tint = Gray500
                                )
                            }
                        }
                    } else {
                        if (station.distanceBetween == 0.0) {
                            ProgressIndicatorSmall(modifier = Modifier.padding(vertical = 4.dp))
                        } else {
                            val km =
                                String.format("%.1f", station.distanceBetween).replace('.', ',')
                            Text(
                                text = stringResource(id = R.string.text_value_km, km),
                                maxLines = 1,
                                overflow = TextOverflow.Visible,
                                style = MetanMobileTypography.subtitle1
                            )
                        }
                    }
                    StationFavButton(station = station)
                }
            }
        }
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
fun StationRowPreview() {
    MetanMobileTheme {
        StationRow(
            station = StationDto.init(),
            locationPermissionGranted = false,
            isGoogleServicesAvailable = true
        )
    }
}