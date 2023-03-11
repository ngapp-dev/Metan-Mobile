package com.ngapp.metanmobile.app.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ngapp.metanmobile.app.theme.MetanMobileColors
import com.ngapp.metanmobile.app.theme.MetanMobileTheme
import com.ngapp.metanmobile.app.theme.dividerColor

@Composable
fun MetanMobileDivider(modifier: Modifier = Modifier) {
    Divider(
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp),
        color = MetanMobileColors.dividerColor
    )
}

@Preview("default", showBackground = true)
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun DividerPreview() {
    MetanMobileTheme {
        Box(Modifier.size(height = 10.dp, width = 100.dp)) {
            MetanMobileDivider(Modifier.align(Alignment.Center))
        }
    }
}