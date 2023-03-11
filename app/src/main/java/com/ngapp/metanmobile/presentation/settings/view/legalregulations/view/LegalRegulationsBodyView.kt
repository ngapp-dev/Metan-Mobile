package com.ngapp.metanmobile.presentation.settings.view.legalregulations.view

import androidx.compose.animation.animateContentSize
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
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.widget.MetanMobileDivider


@Composable
fun LegalRegulationsBodyView(
    modifier: Modifier = Modifier,
    onTermsAndConditionsPage: () -> Unit,
    onPrivacyPolicyPageClick: () -> Unit,
    onSoftwareLicensePageClick: () -> Unit,
    onLocationInformationPageClick: () -> Unit,
) {
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
                    .padding(top = 12.dp)
            ) {
                ItemPageRow(
                    title = R.string.toolbar_terms_and_conditions_title,
                    onPageItemClick = onTermsAndConditionsPage,
                )
                MetanMobileDivider(modifier = Modifier.padding(horizontal = 16.dp))
                ItemPageRow(
                    title = R.string.toolbar_privacy_policy_title,
                    onPageItemClick = onPrivacyPolicyPageClick,
                )
                MetanMobileDivider(modifier = Modifier.padding(horizontal = 16.dp))
                ItemPageRow(
                    title = R.string.toolbar_software_licence_title,
                    onPageItemClick = onSoftwareLicensePageClick,
                )
                MetanMobileDivider(modifier = Modifier.padding(horizontal = 16.dp))
                ItemPageRow(
                    title = R.string.toolbar_location_information_title,
                    onPageItemClick = onLocationInformationPageClick,
                )
                MetanMobileDivider(modifier = Modifier.padding(horizontal = 16.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
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
                                adUnitId = "ca-app-pub-3940256099942544/6300978111"
                                loadAd(AdRequest.Builder().build())
                            }
                        }
                    )
                }
            }
        }
    }
}