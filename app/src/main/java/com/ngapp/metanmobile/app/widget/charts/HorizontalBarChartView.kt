package com.ngapp.metanmobile.app.widget.charts

import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ngapp.metanmobile.app.theme.*
import kotlinx.coroutines.delay

data class HorizontalBarChartItem(
    val id: Int,
    val active: Boolean,
    val title: String,
    val value: Float,
)

@ExperimentalAnimationApi
@Composable
fun SimpleHorizontalBarChartView(
    modifier: Modifier = Modifier,
    data: List<HorizontalBarChartItem>,
    barCornersRadius: Float = 12f,
    barColor: Color = Blue,
    activeBarColor: Color = Red,
    barHeight: Float = 52f,
    height: Dp,
    titleColor: Color = MetanMobileColors.textColor,
    valueColor: Color = MetanMobileColors.textColorInverted,
    backgroundColor: Color = Transparent,
    topStartRadius: Dp = 0.dp,
    topEndRadius: Dp = 0.dp,
    bottomStartRadius: Dp = 0.dp,
    bottomEndRadius: Dp = 0.dp,
) {

    val shape = RoundedCornerShape(
        topStart = topStartRadius,
        topEnd = topEndRadius,
        bottomEnd = bottomEndRadius,
        bottomStart = bottomStartRadius
    )

    var screenSize by remember {
        mutableStateOf(Size.Zero)
    }

    val chosenBar by remember {
        mutableStateOf(-1)
    }
    var chosenBarKey by remember {
        mutableStateOf("")
    }

    LaunchedEffect(chosenBar) {
        delay(3000)
        chosenBarKey = ""
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .clip(shape = shape)
            .background(
                color = backgroundColor,
                shape = shape
            )
            .animateContentSize()
    ) {

        Canvas(modifier = modifier
            .fillMaxSize()
            .padding(all = 8.dp),
            onDraw = {
                screenSize = size
                val spaceBetweenBars = (size.height - (data.size * barHeight)) / (data.size - 1)
                val maxBarWidth = data.maxOf { it.value }
                val barScale = size.width / maxBarWidth
                val paintTitle = Paint().apply {
                    this.color = titleColor.toArgb()
                    textAlign = Paint.Align.LEFT
                    textSize = 40f
                }
                val paintValue = Paint().apply {
                    this.color = valueColor.toArgb()
                    textAlign = Paint.Align.CENTER
                    textSize = 32f
                    typeface = Typeface.DEFAULT_BOLD
                }

                var spaceStep = 0f

                data.forEach { item ->
                    val topLeft = Offset(
                        x = size.width - item.value * barScale,
                        y = spaceStep
                    )
                    //--------------------(showing the y axis titles)--------------------//
                    drawContext.canvas.nativeCanvas.drawText(
                        item.title,
                        0f,
                        spaceStep - barHeight / 2,
                        paintTitle,
                    )
                    //--------------------(draw bars)--------------------//
                    drawRoundRect(
                        color = if (item.active) {
                            activeBarColor
                        } else {
                            barColor
                        },
                        topLeft = Offset(0f, spaceStep),
                        size = Size(
                            width = size.width - topLeft.x,
                            height = barHeight
                        ),
                        cornerRadius = CornerRadius(barCornersRadius, barCornersRadius)
                    )
                    //--------------------(showing the y axis values)--------------------//
                    drawContext.canvas.nativeCanvas.drawText(
                        "${item.value.toInt()} КМ",
                        (size.width - topLeft.x) / 2,
                        spaceStep + (barHeight / 2 + 12f),
                        paintValue,
                    )
                    spaceStep += spaceBetweenBars + barHeight
                }
            })
    }
}