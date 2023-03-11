package com.ngapp.metanmobile.presentation.settings.view.legalregulations.softwarelicense

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
import com.ngapp.metanmobile.presentation.settings.view.legalregulations.softwarelicense.view.SoftwareLicenseContent
import com.ngapp.metanmobile.provider.NavigationProvider
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun SoftwareLicenseScreen(
    modifier: Modifier = Modifier,
    navigator: NavigationProvider,
) {
    SoftwareLicenseBody(modifier, navigator) { padding ->
        SoftwareLicenseContent(padding)
    }
}

@Composable
private fun SoftwareLicenseBody(
    modifier: Modifier = Modifier,
    navigator: NavigationProvider,
    pageContent: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            MetanMobileToolbarWithNavIcon(
                titleResId = R.string.toolbar_software_licence_title,
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
fun SoftwareLicenseScreen() {
    MetanMobileTheme {
        Surface {
//            SoftwareLicenseBody {
//            }
        }
    }
}