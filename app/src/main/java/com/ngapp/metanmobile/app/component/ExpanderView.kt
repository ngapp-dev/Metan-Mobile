package com.ngapp.metanmobile.app.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ngapp.metanmobile.app.theme.MetanMobileColors
import com.ngapp.metanmobile.app.theme.cardBackgroundColor

@Composable
fun AppbarBottomExpanderView() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .padding(bottom = 5.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(
            bottomStart = 12.dp,
            bottomEnd = 12.dp,
            topStart = 0.dp,
            topEnd = 0.dp
        )
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .background(color = MetanMobileColors.cardBackgroundColor)
                .clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
        ) {
        }
    }
}

@Composable
fun ContentBottomExpanderView() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .padding(bottom = 10.dp),
        elevation = 1.dp,
        shape = RoundedCornerShape(
            bottomStart = 12.dp,
            bottomEnd = 12.dp,
            topStart = 0.dp,
            topEnd = 0.dp
        )
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
        ) {
        }
    }
}