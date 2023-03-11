package com.ngapp.metanmobile.presentation.settings.view.legalregulations.softwarelicense.view

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ngapp.metanmobile.app.extension.createInternetIntent
import com.ngapp.metanmobile.app.theme.*


@Composable
fun SoftwareLicenseBodyView(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

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
                    text = "Confirmation",
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth(),
                    style = MetanMobileTypography.h2
                )
                Text(
                    text = "This application uses the following external components:",
                    modifier = Modifier.fillMaxWidth(),
                    style = MetanMobileTypography.h4
                )
                Text(
                    text = "Google Maps",
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth(),
                    style = MetanMobileTypography.h2
                )
                Text(
                    text = "http://developers.google.com/maps/terms",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { context.createInternetIntent("http://developers.google.com/maps/terms") },
                    style = MetanMobileTypography.h4,
                    textDecoration = TextDecoration.Underline,
                    color = Blue
                )
                Text(
                    text = "Google Analytics",
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth(),
                    style = MetanMobileTypography.h2
                )
                Text(
                    text = "http://www.google.com/analytics/terms/ru.html",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { context.createInternetIntent("http://www.google.com/analytics/terms/ru.html") },
                    style = MetanMobileTypography.h4,
                    textDecoration = TextDecoration.Underline,
                    color = Blue
                )
                Text(
                    text = "Apache",
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth(),
                    style = MetanMobileTypography.h2
                )
                Text(
                    text = "https://www.apache.org/licenses/LICENSE-2.0",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { context.createInternetIntent("https://www.apache.org/licenses/LICENSE-2.0") },
                    style = MetanMobileTypography.h4,
                    textDecoration = TextDecoration.Underline,
                    color = Blue
                )
            }
        }
    }
}