package com.ngapp.metanmobile.app.ads

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun ListBannersAds() {
    // on below line creating a variable for location.
    // on below line creating a column for our maps.
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(vertical = 4.dp),
        elevation = 0.dp,
        shape = RoundedCornerShape(
            bottomStart = 0.dp,
            bottomEnd = 0.dp,
            topStart = 0.dp,
            topEnd = 0.dp
        )
    ) {
        // on below line adding admob banner ads.
        AndroidView(
//             on below line specifying width for ads.
            modifier = Modifier.fillMaxWidth(),
            factory = { context ->
//                 on below line specifying ad view.
                AdView(context).apply {
                    // on below line specifying ad size
                    setAdSize(AdSize.BANNER)
                    // on below line specifying ad unit id
                    // currently added a test ad unit id.
                    adUnitId = "ca-app-pub-3940256099942544/6300978111"
                    // calling load ad to load our ad.
                    loadAd(AdRequest.Builder().build())
                }
            }
        )
    }
}