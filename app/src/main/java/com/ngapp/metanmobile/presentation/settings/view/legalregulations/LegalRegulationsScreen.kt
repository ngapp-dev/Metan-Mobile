package com.ngapp.metanmobile.presentation.settings.view.legalregulations

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.theme.MetanMobileTheme
import com.ngapp.metanmobile.app.widget.*
import com.ngapp.metanmobile.presentation.settings.view.legalregulations.view.LegalRegulationsContent
import com.ngapp.metanmobile.provider.NavigationProvider
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun LegalRegulationsScreen(
    modifier: Modifier = Modifier,
    navigator: NavigationProvider,
) {
    LegalRegulationsBody(modifier, navigator) { padding ->
        LegalRegulationsPage(modifier, navigator, padding)
    }
}

@Composable
private fun LegalRegulationsPage(
    modifier: Modifier,
    navigator: NavigationProvider,
    paddings: PaddingValues,
) {
    LegalRegulationsContent(
        paddingValues = paddings,
        onTermsAndConditionsPageClick = { navigator.openTermsAndConditions() },
        onPrivacyPolicyPageClick = { navigator.openPrivacyPolicy() },
        onSoftwareLicensePageClick = { navigator.openSoftwareLicense() },
        onLocationInformationPageClick = { navigator.openLocationInformation() }
    )
}

@Composable
private fun LegalRegulationsBody(
    modifier: Modifier = Modifier,
    navigator: NavigationProvider,
    pageContent: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            MetanMobileToolbarWithNavIcon(
                titleResId = R.string.toolbar_legal_regulations_title,
                pressOnBack = {
                    navigator.navigateUp()
                },
                elevation = 0.dp,
            )
        },
        content = { pageContent.invoke(it) }
    )
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
fun LegalRegulationsScreen() {
    MetanMobileTheme {
        Surface {
//            LegalRegulationsBody {
//            }
        }
    }
}