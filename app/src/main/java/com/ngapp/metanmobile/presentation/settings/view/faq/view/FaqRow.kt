package com.ngapp.metanmobile.presentation.settings.view.faq.view

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ngapp.jetframework.htmltext.HtmlText
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.theme.MetanMobileColors
import com.ngapp.metanmobile.app.theme.MetanMobileTypography
import com.ngapp.metanmobile.app.theme.toolbarIconColor
import com.ngapp.metanmobile.data.model.dto.faq.FaqDto

@Composable
fun FaqRow(
    modifier: Modifier = Modifier,
    faqItem: FaqDto,
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        onClick = { expanded = !expanded },
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = 0.dp,
        shape = RoundedCornerShape(
            bottomStart = 0.dp,
            bottomEnd = 0.dp,
            topStart = 0.dp,
            topEnd = 0.dp
        )
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = modifier.weight(1f)
            ) {
                Text(
                    text = faqItem.title.orEmpty(),
                    modifier = Modifier.padding(vertical = 4.dp),
                    style = MetanMobileTypography.h6
                )
                if (expanded) {
                    HtmlText(
                        text = faqItem.content.orEmpty(),
                        modifier = Modifier.padding(top = 4.dp),
                        style = MetanMobileTypography.subtitle1
                    )
                }
            }
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) {
                    stringResource(R.string.button_show_less)
                } else {
                    stringResource(R.string.button_show_more)
                },
                tint = MetanMobileColors.toolbarIconColor,
                modifier = Modifier.wrapContentSize()
            )
        }
    }
}