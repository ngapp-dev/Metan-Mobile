package com.ngapp.metanmobile.presentation.settings.view.contacts.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.ngapp.jetframework.rememberFlowWithLifecycle
import com.ngapp.metanmobile.app.widget.SingleHeaderView
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.component.ContentBottomExpanderView
import com.ngapp.metanmobile.app.widget.DimensionMeasureSubcomposeLayout
import com.ngapp.metanmobile.data.model.dto.profile.ProfileDto
import com.ngapp.metanmobile.presentation.settings.view.contacts.ContactsViewState
import com.ngapp.metanmobile.presentation.settings.view.profile.ProfileViewState

@Composable
fun ContactsContent(
    viewState: ContactsViewState,
    paddingValues: PaddingValues,
) {
    val contactsItem = viewState.contactsData

    LazyColumn(
        contentPadding = paddingValues,
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            ContactsBodyView(
                modifier = Modifier.fillMaxHeight(),
                contacts = contactsItem,
            )
        }
        item {
            ContentBottomExpanderView()
        }
    }
}
