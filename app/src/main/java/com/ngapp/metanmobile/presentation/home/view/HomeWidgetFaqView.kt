package com.ngapp.metanmobile.presentation.home.view

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.theme.*
import com.ngapp.metanmobile.app.widget.MetanMobileDivider
import com.ngapp.metanmobile.data.model.dto.faq.FaqDto

@Composable
fun HomeWidgetFaqView(
    modifier: Modifier = Modifier,
    pinnedFaqItems: List<FaqDto>,
    onSeeAllClick: () -> Unit = {},
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .padding(vertical = 5.dp)
            .padding(bottom = 5.dp),
        elevation = 4.dp,
        shape = MetanMobileShapes.large
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(shape = MetanMobileShapes.large)
                .background(color = Green)
                .animateContentSize()
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .padding(bottom = 12.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.text_faq),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier,
                    color = White,
                    style = MetanMobileTypography.h1
                )
                Spacer(modifier = Modifier.padding(top = 8.dp))
                Card(
                    modifier = modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    elevation = 0.dp,
                    shape = MetanMobileShapes.large
                ) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clip(shape = MetanMobileShapes.large)
                ) {
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                    ) {
                        pinnedFaqItems.forEachIndexed { i, faqDto ->
                            HomeFaqRow(pinnedFaqItem = faqDto)
                            if (i < pinnedFaqItems.size - 1) MetanMobileDivider()
                        }
                    }
                }
            }
                Text(
                    text = stringResource(id = R.string.button_show_all).uppercase(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = White,
                    textAlign = TextAlign.Center,
                    style = MetanMobileTypography.h3,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                        .clickable { onSeeAllClick.invoke() },
                )
            }
        }
    }
}
