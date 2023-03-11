package com.ngapp.metanmobile.presentation.settings.view.language.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ngapp.metanmobile.app.theme.Gray400
import com.ngapp.metanmobile.app.theme.MetanMobileTypography
import com.ngapp.metanmobile.data.model.dto.language.LanguageDto

@Composable
fun LanguageItemRow(
    modifier: Modifier = Modifier,
    language: LanguageDto,
    onLanguageItemClick: () -> Unit = {},
) {
    Card(
        onClick = { onLanguageItemClick.invoke() },
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp),
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
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = language.name,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(vertical = 4.dp),
                style = MetanMobileTypography.h6
            )
            AnimatedVisibility(language.isSelected) {
                Icon(
                    painter = rememberVectorPainter(Icons.Default.Check),
                    contentDescription = null,
                    tint = Gray400,
                    modifier = Modifier.wrapContentSize()
                )
            }
        }
    }
}