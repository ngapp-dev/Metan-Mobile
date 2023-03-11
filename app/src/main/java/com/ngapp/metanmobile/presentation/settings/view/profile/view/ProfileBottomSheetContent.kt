package com.ngapp.metanmobile.presentation.settings.view.profile.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.theme.MetanMobileColors
import com.ngapp.metanmobile.app.theme.Red
import com.ngapp.metanmobile.app.theme.Red700
import com.ngapp.metanmobile.app.theme.White
import com.ngapp.metanmobile.data.model.dto.profile.ProfileDto

@Composable
fun ProfileBottomSheetContent(
    profile: ProfileDto,
    onApprove: () -> Unit = {},
    onCancel: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MetanMobileColors.primary)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(0.7f)) {
                Text(
                    text = stringResource(id = R.string.text_profile_delete),
                    modifier = Modifier.padding(start = 16.dp),
                    fontWeight = FontWeight.Bold,
                    color = Red,
                    fontSize = 18.sp
                )
                Text(
                    text = (profile.firstName + " " + profile.lastName),
                    modifier = Modifier.padding(start = 16.dp),
                    fontWeight = FontWeight.SemiBold
                )
            }

            TextButton(
                onClick = { onCancel() },
                modifier = Modifier
                    .padding(end = 8.dp)
                    .weight(0.3f),
                contentPadding = PaddingValues(
                    start = 6.dp,
                    top = 0.dp,
                    end = 10.dp,
                    bottom = 0.dp
                ),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_close_circle),
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                    tint = Red700
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(
                    text = stringResource(id = R.string.button_cancel),
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Normal
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )

        Text(
            text = stringResource(
                id = R.string.text_delete_favor_description,
                (profile.firstName + " " + profile.lastName)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            fontWeight = FontWeight.Normal
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )

        Button(
            onClick = {
                onApprove.invoke()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Red),
            shape = RectangleShape
        ) {
            Text(
                text = stringResource(id = R.string.text_approve_remove),
                color = White,
                modifier = Modifier.padding(4.dp)
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )
    }
}