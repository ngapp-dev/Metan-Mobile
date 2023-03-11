package com.ngapp.metanmobile.presentation.settings.view.profile.view

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.theme.*
import com.ngapp.metanmobile.app.widget.ButtonWithIcon
import com.ngapp.metanmobile.data.model.dto.profile.ProfileDto
import com.ngapp.metanmobile.presentation.settings.view.profile.ProfileEvent
import com.ngapp.metanmobile.presentation.settings.view.profile.ProfileViewModel


@Composable
fun ProfileBodyView(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel,
    profile: ProfileDto?,
    onUpdateProfileClick: (ProfileDto) -> Unit
) {

    if (profile != null) {
        if (profile.firstName != "" && profile.firstName != null) {
            viewModel.firstName = profile.firstName!!
        }
        if (profile.lastName != "" && profile.lastName != null) {
            viewModel.lastName = profile.lastName!!
        }
    }

//    val userNameHasError by viewModel.userNameHasError.collectAsStateWithLifecycle()
//    val userNameHasLocalError by viewModel.userNameHasLocalError.collectAsStateWithLifecycle()

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
                ProfileHeaderView(viewModel = viewModel, profile = profile)
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp, horizontal = 16.dp),
                    value = viewModel.firstName,
                    onValueChange = { newValue ->
                        viewModel.onTriggerEvent(ProfileEvent.UpdateFirstname(newValue))
                    },
                    placeholder = {
                        Text(stringResource(id = R.string.placeholder_firstname))
                    },
                    shape = MetanMobileShapes.large,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        cursorColor = Gray400,
                        focusedBorderColor = Blue,
                        unfocusedBorderColor = Gray400
                    ),
                )
//                if (userNameHasError) {
//                    Text(
//                        text = "Username not available. Please choose a different one.",
//                        color = Red700
//                    )
//                } else if (userNameHasLocalError) {
//                    Text(
//                        text = "Username contains invalid characters.",
//                        color = Red700
//                    )
//                }
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp, horizontal = 16.dp),
                    value = viewModel.lastName,
                    onValueChange = { newValue ->
                        viewModel.onTriggerEvent(ProfileEvent.UpdateLastname(newValue))
                    },
                    placeholder = {
                        Text(stringResource(id = R.string.placeholder_lastname))
                    },
                    shape = MetanMobileShapes.large,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        cursorColor = Gray400,
                        focusedBorderColor = Blue,
                        unfocusedBorderColor = Gray400
                    ),
                )
                ButtonWithIcon(
                    icon = Icons.Filled.Save,
                    iconTint = White,
                    buttonText = R.string.button_update_profile,
                    buttonBackgroundColor = Blue,
                    fontColor = White,
                    borderStrokeColor = Blue,
                    modifier = Modifier.padding(vertical = 18.dp, horizontal = 16.dp),
                    onClick = {
                        val prof = ProfileDto(
                            id = 1,
                            name = null,
                            email = null,
                            password = "",
                            checkword = null,
                            nickname = null,
                            firstName = viewModel.firstName,
                            middleName = null,
                            lastName = viewModel.lastName,
                            profileImage = null,
                            bio = null,
                            location = null,
                            createDate = System.currentTimeMillis(),
                            modDate = System.currentTimeMillis(),
                            isActive = 1,
                            lastLogin = null,
                            permissionLevel = 1
                        )
                        onUpdateProfileClick(prof)
                    }
                )
            }
        }
    }
}