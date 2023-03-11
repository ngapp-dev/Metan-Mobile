package com.ngapp.metanmobile.presentation.settings.view.about.view

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ngapp.metanmobile.app.component.ContentBottomExpanderView
import com.ngapp.metanmobile.presentation.settings.view.about.AboutViewState
import com.ngapp.metanmobile.provider.NavigationProvider

@Composable
fun AboutContent(
    data: AboutViewState
) {
    LazyColumn {
        data.githubUser?.let { githubUser ->
            item("contentInfo") {
                AboutBodyView(
                    modifier = Modifier.fillMaxHeight(),
                    githubUser = githubUser
                )
            }
            item {
                ContentBottomExpanderView()
            }
        }
    }
}