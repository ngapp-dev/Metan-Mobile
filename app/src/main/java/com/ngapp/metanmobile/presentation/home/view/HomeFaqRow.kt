package com.ngapp.metanmobile.presentation.home.view

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import com.ngapp.metanmobile.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ngapp.jetframework.htmltext.HtmlText
import com.ngapp.metanmobile.app.theme.*
import com.ngapp.metanmobile.data.model.dto.faq.FaqDto

@Composable
fun HomeFaqRow(
    modifier: Modifier = Modifier,
    pinnedFaqItem: FaqDto,
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                expanded = !expanded
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {
                Text(
                    text = pinnedFaqItem.title.orEmpty(),
                    style = MetanMobileTypography.h6
                )
                if (expanded) {
                    HtmlText(
                        text = pinnedFaqItem.content.orEmpty(),
                        modifier = Modifier.padding(top = 4.dp),
                        style = MetanMobileTypography.subtitle1
                    )
                }
            }
            Icon(
                modifier = Modifier.padding(all = 4.dp),
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) {
                    stringResource(R.string.button_show_less)
                } else {
                    stringResource(R.string.button_show_more)
                },
                tint = MetanMobileColors.toolbarIconColor
            )
        }
    }
}