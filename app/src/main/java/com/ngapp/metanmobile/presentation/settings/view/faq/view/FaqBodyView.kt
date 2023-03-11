package com.ngapp.metanmobile.presentation.settings.view.faq.view

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.ngapp.metanmobile.app.ads.ListBannersAds
import com.ngapp.metanmobile.app.component.ContentBottomExpanderView
import com.ngapp.metanmobile.app.component.SmallSpacer
import com.ngapp.metanmobile.app.theme.MetanMobileColors
import com.ngapp.metanmobile.app.theme.cardBackgroundColor
import com.ngapp.metanmobile.app.widget.MetanMobileDivider
import com.ngapp.metanmobile.data.model.dto.faq.FaqDto


@Composable
fun FaqBodyView(
    modifier: Modifier = Modifier,
    faqItems: List<FaqDto>,
) {
    if (faqItems.isNotEmpty()) {
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
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 6.dp)
                ) {
                    faqItems.let { faq ->
                        faq.forEachIndexed { i, faqItem ->
                            Column {
                                if (i % 8 == 0 && i != 0) {
                                    Card(
                                        modifier = Modifier
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
                                        AndroidView(
                                            modifier = Modifier.fillMaxWidth(),
                                            factory = { context ->
                                                AdView(context).apply {
                                                    setAdSize(AdSize.BANNER)
                                                    adUnitId =
                                                        "ca-app-pub-3940256099942544/6300978111"
                                                    loadAd(AdRequest.Builder().build())
                                                }
                                            }
                                        )
                                    }
                                    MetanMobileDivider(modifier = Modifier.padding(horizontal = 16.dp))
                                }

                                FaqRow(faqItem = faqItem)

                                if (i < faqItems.size - 1) {
                                    MetanMobileDivider(modifier = Modifier.padding(horizontal = 16.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
        ContentBottomExpanderView()
    }
}