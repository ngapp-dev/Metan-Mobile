package com.ngapp.metanmobile.presentation.settings.view.legalregulations.softwarelicense.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ngapp.metanmobile.app.component.ContentBottomExpanderView

@Composable
fun SoftwareLicenseContent(
    paddingValues: PaddingValues
) {
    LazyColumn(
        contentPadding = paddingValues,
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            SoftwareLicenseBodyView(
                modifier = Modifier.fillMaxHeight(),
            )
        }
        item {
            ContentBottomExpanderView()
        }
    }
}
