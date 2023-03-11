package com.ngapp.metanmobile.presentation.stations.detail.view

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.res.Configuration
import android.net.Uri
import androidx.annotation.StringRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ngapp.jetframework.htmltext.HtmlText
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.extension.fromStringToListFloat
import com.ngapp.metanmobile.app.theme.*
import com.ngapp.metanmobile.app.widget.charts.SimpleVerticalBarChartView
import com.ngapp.metanmobile.app.widget.charts.VerticalBarChartItem
import com.ngapp.metanmobile.data.model.dto.station.StationDto
import java.util.*

@Composable
fun StationDetailBodyView(station: StationDto) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MetanMobileColors.cardBackgroundColor)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            StationDetailSubTitleView(R.string.text_station_hours)
            HtmlText(
                text = station.workingTime.orEmpty(),
                modifier = Modifier.padding(top = 4.dp),
                style = MetanMobileTypography.h6,
            )

            StationDetailSubTitleView(R.string.text_station_address)
            StationDetailInfoContentView(station.address)

            StationDetailSubTitleView(R.string.text_station_payments)
            StationDetailInfoContentView(station.payment)

            StationDetailSubTitleView(R.string.text_station_phones)
            StationDetailPhoneView(station)

            StationDetailAttendanceChartView(station = station)
        }
    }
}

@Composable
private fun StationDetailSubTitleView(
    @StringRes subTitle: Int,
) {
    Text(
        text = stringResource(subTitle),
        style = MetanMobileTypography.h4,
        color = Gray400,
        modifier = Modifier.padding(top = 12.dp)
    )
}

@Composable
private fun StationDetailInfoContentView(stationContent: String?) {
    Text(
        text = stationContent ?: "",
        style = MetanMobileTypography.h6,
        lineHeight = 22.sp,
        modifier = Modifier.padding(top = 4.dp)
    )
}

@Composable
private fun StationDetailPhoneView(station: StationDto) {
    val context = LocalContext.current
    val number = station.phone?.split(",")?.toTypedArray()
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        number?.forEach {
            Text(
                text = it,
                style = MetanMobileTypography.h6,
                color = Blue,
                lineHeight = 20.sp,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable(
                    onClick = { context.makeCall(it) }
                )
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun StationDetailAttendanceChartView(
    modifier: Modifier = Modifier,
    station: StationDto,
) {
    if (createCharItems(station).isNotEmpty()) {
        StationDetailSubTitleView(R.string.text_station_attendance)
        Card(
            modifier = modifier.padding(vertical = 5.dp),
            elevation = 1.dp,
            shape = MetanMobileShapes.large
        ) {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .height(height = 88.dp)
                    .widthIn(0.dp, 400.dp)
                    .background(MetanMobileColors.smallWidgetBackgroundColor)
                    .clip(MetanMobileShapes.large)
            ) {
                SimpleVerticalBarChartView(data = createCharItems(station))
            }
        }
    }
}

private fun Context.makeCall(number: String) {
    val intent = Intent(Intent.ACTION_DIAL)
    intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
    intent.data = Uri.parse("tel:$number")
    this.startActivity(intent)
}

fun createCharItems(station: StationDto): List<VerticalBarChartItem> {
    val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

    val attendanceString = when (currentDay) {
        Calendar.MONDAY -> station.busyOnMonday
        Calendar.TUESDAY -> station.busyOnTuesday
        Calendar.WEDNESDAY -> station.busyOnWednesday
        Calendar.THURSDAY -> station.busyOnThursday
        Calendar.FRIDAY -> station.busyOnFriday
        Calendar.SATURDAY -> station.busyOnSaturday
        Calendar.SUNDAY -> station.busyOnSunday
        else -> ""
    }
    val readyCharItems = mutableListOf<VerticalBarChartItem>()
    attendanceString?.let {
        val attendanceList: List<Float?> = fromStringToListFloat(it)
        if (attendanceList.isNotEmpty()) {
            (0..23).forEach { index ->
                readyCharItems += when (index) {
                    0, 3, 6, 9, 12, 15, 18, 21 -> {
                        VerticalBarChartItem(
                            id = index,
                            active = currentHour == index,
                            title = index.toString(),
                            value = attendanceList[index] ?: 0f
                        )
                    }
                    else -> {
                        VerticalBarChartItem(
                            id = index,
                            active = currentHour == index,
                            title = "",
                            value = attendanceList[index] ?: 0f
                        )
                    }
                }
            }
        }
    }
    return readyCharItems
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
fun StationDetailBodyPreview() {
    MetanMobileTheme {
        Surface(color = MetanMobileColors.background) {
            StationDetailBodyView(
                StationDto.init()
            )
        }
    }
}