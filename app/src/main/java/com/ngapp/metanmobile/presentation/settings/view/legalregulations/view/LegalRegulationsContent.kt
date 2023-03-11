package com.ngapp.metanmobile.presentation.settings.view.legalregulations.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ngapp.metanmobile.app.component.ContentBottomExpanderView

@Composable
fun LegalRegulationsContent(
    paddingValues: PaddingValues,
    onTermsAndConditionsPageClick: () -> Unit,
    onPrivacyPolicyPageClick: () -> Unit,
    onSoftwareLicensePageClick: () -> Unit,
    onLocationInformationPageClick: () -> Unit,
) {

    LazyColumn(
        contentPadding = paddingValues,
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            LegalRegulationsBodyView(
                modifier = Modifier.fillMaxHeight(),
                onTermsAndConditionsPage = onTermsAndConditionsPageClick,
                onPrivacyPolicyPageClick = onPrivacyPolicyPageClick,
                onSoftwareLicensePageClick = onSoftwareLicensePageClick,
                onLocationInformationPageClick = onLocationInformationPageClick,
            )
        }
        item {
            ContentBottomExpanderView()
        }
    }
}
