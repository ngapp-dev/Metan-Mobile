package com.ngapp.metanmobile.app.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ngapp.jetframework.Delayed
import com.ngapp.metanmobile.app.component.ProgressIndicator
import com.ngapp.metanmobile.app.theme.MetanMobileTheme

@Composable
fun LoadingView(modifier: Modifier = Modifier, delayMillis: Long = 100L) {
    Delayed(delayMillis = delayMillis) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = when (modifier == Modifier) {
                true -> Modifier.fillMaxSize()
                false -> modifier
            }
        ) {
            ProgressIndicator()
        }
    }
}

@Preview(
    showBackground = true,
    name = "Light Mode"
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode"
)
@Composable
fun LoadingViewPreview() {
    MetanMobileTheme {
        LoadingView()
    }
}