package com.ngapp.metanmobile.app.widget.charts

import android.graphics.Paint
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
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ngapp.metanmobile.app.theme.*
import kotlinx.coroutines.delay

data class VerticalBarChartItem(
    val id: Int,
    val active: Boolean,
    val title: String,
    val value: Float,
)

@ExperimentalAnimationApi
@Composable
fun SimpleVerticalBarChartView(
    modifier: Modifier = Modifier,
    data: List<VerticalBarChartItem>,
    barCornersRadius: Float = 12f,
    barColor: Color = Blue,
    activeBarColor: Color = Red,
    barWidth: Float = 20f,
    labelOffset: Float = 30f,
    labelColor: Color = MetanMobileColors.textColor,
) {


    var screenSize by remember {
        mutableStateOf(Size.Zero)
    }

    var chosenBar by remember {
        mutableStateOf(-1)
    }
    var chosenBarKey by remember {
        mutableStateOf("")
    }

    LaunchedEffect(chosenBar) {
        delay(3000)
        chosenBarKey = ""
    }
    Canvas(modifier = modifier
        .fillMaxSize()
        .padding(
            top = 16.dp,
            bottom = 12.dp,
            start = 18.dp,
            end = 18.dp
        ),
        onDraw = {
            screenSize = size
            val spaceBetweenBars = (size.width - (data.size * barWidth)) / (data.size - 1)
            val maxBarHeight = data.maxOf { it.value }
            val barScale = size.height / maxBarHeight
            val paint = Paint().apply {
                this.color = labelColor.toArgb()
                textAlign = Paint.Align.CENTER
                textSize = 40f
            }

            var spaceStep = 0f

            for (item in data) {
                val topLeft = Offset(
                    x = spaceStep,
                    y = size.height - item.value * barScale - labelOffset
                )
                //--------------------(draw bars)--------------------//
                drawRoundRect(
                    color = if (item.active) {
                        activeBarColor
                    } else {
                        barColor
                    },
                    topLeft = topLeft,
                    size = Size(
                        width = barWidth,
                        height = size.height - topLeft.y - labelOffset
                    ),
                    cornerRadius = CornerRadius(barCornersRadius, barCornersRadius)
                )
                //--------------------(showing the x axis labels)--------------------//
                drawContext.canvas.nativeCanvas.drawText(
                    item.title,
                    spaceStep + barWidth / 2,
                    10f + size.height,
                    paint
                )

                spaceStep += spaceBetweenBars + barWidth
            }
        })
}


private fun detectPosition(screenSize: Size, offset: Offset, listSize: Int, itemWidth: Float): Int {
    val spaceBetweenBars =
        (screenSize.width - (listSize * itemWidth)) / (listSize - 1)
    var spaceStep = 0f
    for (i in 0 until listSize) {
        if (offset.x in spaceStep..(spaceStep + itemWidth)) {
            return i
        }
        spaceStep += spaceBetweenBars + itemWidth
    }
    return -1
}
