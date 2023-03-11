package com.ngapp.metanmobile.presentation.settings.view.language.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.ngapp.metanmobile.app.component.ContentBottomExpanderView
import com.ngapp.metanmobile.data.model.dto.language.LanguageDto

@Composable
fun LanguageContent(
    paddingValues: PaddingValues,
    langs: MutableState<List<LanguageDto>>,
) {
    LazyColumn(
        contentPadding = paddingValues,
        modifier = Modifier.fillMaxSize(),
    ) {
        item {
            LanguageBodyView(
                modifier = Modifier.fillMaxHeight(),
                langs = langs
            )
        }
        item {
            ContentBottomExpanderView()
        }
    }
}
