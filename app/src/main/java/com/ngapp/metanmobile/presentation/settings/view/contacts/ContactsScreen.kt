package com.ngapp.metanmobile.presentation.settings.view.contacts

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ngapp.framework.base.mvi.BaseViewState
import com.ngapp.framework.extension.cast
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.theme.MetanMobileTheme
import com.ngapp.metanmobile.app.widget.*
import com.ngapp.metanmobile.presentation.settings.view.contacts.view.ContactsContent
import com.ngapp.metanmobile.provider.NavigationProvider
import com.ramcosta.composedestinations.annotation.DeepLink
import com.ramcosta.composedestinations.annotation.Destination

@Destination(
    deepLinks = [
        DeepLink(
            uriPattern = "https://metan.by/contacts/"
        )
    ]
)
@Composable
fun ContactsScreen(
    modifier: Modifier = Modifier,
    viewModel: ContactsViewModel = hiltViewModel(),
    navigator: NavigationProvider,
) {
    val uiState by viewModel.uiState.collectAsState()


    ContactsBody(modifier, navigator) { padding ->
        ContactsPage(modifier, uiState, viewModel, padding)
    }
}

@Composable
private fun ContactsPage(
    modifier: Modifier,
    uiState: BaseViewState<*>,
    viewModel: ContactsViewModel,
    paddings: PaddingValues,
) {
    when (uiState) {
        is BaseViewState.Data ->
            ContactsContent(
                viewState = uiState.cast<BaseViewState.Data<ContactsViewState>>().value,
                paddingValues = paddings
            )
        is BaseViewState.Empty -> EmptyView(modifier = modifier)
        is BaseViewState.Error -> ErrorView(
            e = uiState.cast<BaseViewState.Error>().throwable,
            action = {
                viewModel.onTriggerEvent(ContactsEvent.LoadContacts)
            }
        )
        is BaseViewState.Loading -> LoadingView()
    }
    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(ContactsEvent.LoadContacts)
    })
}

@Composable
private fun ContactsBody(
    modifier: Modifier = Modifier,
    navigator: NavigationProvider,
    pageContent: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            MetanMobileToolbarWithNavIcon(
                titleResId = R.string.toolbar_contacts_title,
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
fun ProfileScreenPreview() {
    MetanMobileTheme {
        Surface {
//            ContactsBody {
//            }
        }
    }
}