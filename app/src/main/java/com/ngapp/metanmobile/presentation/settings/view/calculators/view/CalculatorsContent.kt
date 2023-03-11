package com.ngapp.metanmobile.presentation.settings.view.calculators.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CalculatorsContent(
    paddingValues: PaddingValues
) {

    LazyColumn(
        contentPadding = paddingValues,
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            CalculatorsBodyView(
                modifier = Modifier.fillMaxHeight(),
            )
        }
    }
}
