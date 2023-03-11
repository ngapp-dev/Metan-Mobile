package com.ngapp.metanmobile.presentation.home.view

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.theme.*
import com.ngapp.metanmobile.app.widget.*

@Composable
fun HomeWidgetCalculatorsView(
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize()
            .padding(vertical = 5.dp),
        elevation = 4.dp,
        shape = MetanMobileShapes.large
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(shape = MetanMobileShapes.large)
                .background(color = Blue)
                .animateContentSize()
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.title_calculate_profit),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier,
                    color = White,
                    style = MetanMobileTypography.h1
                )
                MetanMobileCalculators(
                    modifier = modifier,
                    tabRowIndicatorColor = White,
                    tabNameColor = White
                )
            }
        }
    }
}



