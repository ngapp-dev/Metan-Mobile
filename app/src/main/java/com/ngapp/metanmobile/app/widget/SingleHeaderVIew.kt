package com.ngapp.metanmobile.app.widget

import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ngapp.metanmobile.app.theme.MetanMobileColors
import com.ngapp.metanmobile.app.theme.MetanMobileTypography
import com.ngapp.metanmobile.app.theme.cardBackgroundColor

@Composable
fun SingleHeaderView(
    modifier: Modifier = Modifier,
    @StringRes title: Int
) {

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
                .background(color = MetanMobileColors.cardBackgroundColor)
                .clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
        ) {
            Text(
                text = stringResource(id = title),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, start = 16.dp, end = 16.dp),
                style = MetanMobileTypography.h1
            )
        }
    }
}