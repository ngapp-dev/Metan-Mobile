package com.ngapp.metanmobile.presentation.settings.view.legalregulations.view

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.theme.Gray400
import com.ngapp.metanmobile.app.theme.MetanMobileTheme
import com.ngapp.metanmobile.app.theme.MetanMobileTypography

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemPageRow(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    onPageItemClick: () -> Unit = {},
) {
    Card(
        onClick = { onPageItemClick.invoke() },
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
                text = stringResource(id = title),
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(vertical = 4.dp),
                style = MetanMobileTypography.h6
            )
            Icon(
                painter = rememberVectorPainter(Icons.Default.KeyboardArrowRight),
                contentDescription = null,
                tint = Gray400,
                modifier = Modifier.wrapContentSize()
            )
        }
    }
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
fun sItemPageRow() {
    MetanMobileTheme {
        ItemPageRow(
            title = R.string.text_faq,
        )
    }
}