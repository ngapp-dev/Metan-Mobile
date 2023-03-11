package com.ngapp.metanmobile.presentation.settings.view.faq.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.widget.SingleHeaderView
import com.ngapp.metanmobile.presentation.settings.view.faq.FaqViewState

@Composable
fun FaqContent(
    paddingValues: PaddingValues,
    viewState: FaqViewState,
) {
    val faqItems = viewState.faq

    LazyColumn(
        contentPadding = paddingValues,
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            FaqBodyView(
                modifier = Modifier.fillMaxHeight(),
                faqItems = faqItems
            )
        }
    }
}