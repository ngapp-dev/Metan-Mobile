package com.ngapp.metanmobile.presentation.settings.view.calculators.view

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.theme.MetanMobileColors
import com.ngapp.metanmobile.app.theme.textColor
import com.ngapp.metanmobile.app.widget.MetanMobileCalculators


@Composable
fun CalculatorsBodyView(
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .animateContentSize()
            .padding(top = 5.dp),
        elevation = 1.dp,
        shape = RoundedCornerShape(
            bottomStart = 0.dp,
            bottomEnd = 0.dp,
            topStart = 12.dp,
            topEnd = 12.dp
        )
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 12.dp)
            ) {
                MetanMobileCalculators(
                    tabRowIndicatorColor = MetanMobileColors.textColor,
                    tabNameColor = MetanMobileColors.textColor
                )
            }
        }
    }
}