package com.ngapp.metanmobile.presentation.settings.view.legalregulations.locationinformation.view

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.theme.*


@Composable
fun LocationInformationBodyView(
    modifier: Modifier = Modifier
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
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = stringResource(R.string.title_how_do_we_use_location),
                    modifier = Modifier.fillMaxWidth(),
                    style = MetanMobileTypography.h2
                )
                Text(
                    text = stringResource(id = R.string.text_location_explanation),
                    modifier = Modifier.fillMaxWidth(),
                    style = MetanMobileTypography.h4
                )
            }
        }
    }
}