package com.ngapp.metanmobile.presentation.settings.view.contacts.view

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import com.ngapp.jetframework.htmltext.HtmlText
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.extension.createInternetIntent
import com.ngapp.metanmobile.app.theme.*
import com.ngapp.metanmobile.data.model.dto.contacts.ContactsDto


@Composable
fun ContactsBodyView(
    modifier: Modifier = Modifier,
    contacts: List<ContactsDto>
) {
    val contact = if (contacts.isNotEmpty()) contacts[0] else null
    val context = LocalContext.current

    Card(
        modifier = modifier
            .animateContentSize()
            .padding(top = 5.dp),
        elevation = 1.dp,
        shape = RoundedCornerShape(
            bottomStart = 0.dp,
            bottomEnd = 0.dp,
            topStart = 12.dp,
            topEnd = 12.dp
        )
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(MetanMobileColors.cardBackgroundColor)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 18.dp)
            ) {
                Text(
                    text = stringResource(R.string.title_contacts_info),
                    modifier = Modifier.padding(bottom = 16.dp),
                    style = MetanMobileTypography.h1
                )
                HtmlText(
                    text = contact?.content.orEmpty(),
                    modifier = Modifier.padding(top = 8.dp),
                    linkClicked = { context.createInternetIntent(it) },
                    flags = HtmlCompat.FROM_HTML_MODE_LEGACY,
                    style = MetanMobileTypography.h6,
                )
            }
        }
    }
}