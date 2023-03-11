package com.ngapp.metanmobile.presentation.settings.view.legalregulations.locationinformation.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ngapp.metanmobile.app.component.ContentBottomExpanderView

@Composable
fun LocationInformationContent(
    paddingValues: PaddingValues
) {

    LazyColumn(
        contentPadding = paddingValues,
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            LocationInformationBodyView(
                modifier = Modifier.fillMaxHeight(),
            )
        }
        item {
            ContentBottomExpanderView()
        }
    }
}
