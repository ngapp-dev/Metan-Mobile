package com.ngapp.metanmobile.app.widget

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.component.AppbarBottomExpanderView
import com.ngapp.metanmobile.app.theme.*

@Composable
fun MetanMobileToolbarWithNavIcon(
    @StringRes titleResId: Int? = null,
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
    pressOnBack: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        TopAppBar(
            title = {
                if (titleResId != null) {
                    Text(
                        stringResource(titleResId),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp),
                        style = MetanMobileTypography.h2
                    )
                }
            },
            navigationIcon = {
                Icon(
                    rememberVectorPainter(Icons.Filled.ArrowBack),
                    contentDescription = null,
                    tint = MetanMobileColors.toolbarIconColor,
                    modifier = Modifier
                        .padding(8.dp)
                        .padding(top = 12.dp)
                        .clickable { pressOnBack.invoke() }
                )
            },
            backgroundColor = MetanMobileColors.primary,
            modifier = Modifier.fillMaxWidth(),
            elevation = elevation,
        )
        AppbarBottomExpanderView()
    }
}

@Composable
fun CabinetMetanMobileToolbarWithNavIcon(
    @StringRes titleResId: Int? = null,
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
    pressOnBack: () -> Unit,
    onOpenInBrowserClicked: () -> Unit,
    onGetAccessClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        TopAppBar(
            title = {
                if (titleResId != null) {
                    Text(
                        stringResource(titleResId),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp),
                        style = MetanMobileTypography.h2
                    )
                }
            },
            navigationIcon = {
                Icon(
                    rememberVectorPainter(Icons.Filled.ArrowBack),
                    contentDescription = null,
                    tint = MetanMobileColors.toolbarIconColor,
                    modifier = Modifier
                        .padding(8.dp)
                        .padding(top = 12.dp)
                        .clickable { pressOnBack.invoke() }
                )
            },
            actions = {
                CabinetMoreAction(
                    modifier = Modifier.padding(top = 12.dp),
                    onOpenInBrowserClicked = onOpenInBrowserClicked,
                    onGetAccessClicked = onGetAccessClicked
                )
            },
            backgroundColor = MetanMobileColors.primary,
            modifier = Modifier
                .fillMaxWidth(),
            elevation = elevation
        )
        AppbarBottomExpanderView()
    }
}

@Composable
fun HomeMetanMobileToolbar(
    @StringRes titleResId: Int? = null,
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
    onUserClicked: () -> Unit,
    onSettingsClicked: () -> Unit,
) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth()
                    .height(24.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(R.drawable.logo_full_solid)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }
        },
        actions = {
            UserAction(
                modifier = Modifier.padding(top = 12.dp),
                onUserClicked = onUserClicked
            )
            SettingsAction(
                modifier = Modifier.padding(top = 12.dp),
                onSettingsClicked = onSettingsClicked
            )
        },
        backgroundColor = MetanMobileColors.primary,
        modifier = Modifier
            .fillMaxWidth(),
        elevation = elevation
    )
}

@Composable
fun SettingsMetanMobileToolbar(
    @StringRes titleResId: Int? = null,
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
    pressOnBack: () -> Unit,
    onSupportClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        TopAppBar(
            title = {
                if (titleResId != null) {
                    Text(
                        stringResource(titleResId),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp),
                        style = MetanMobileTypography.h2
                    )
                }
            },
            navigationIcon = {
                Icon(
                    rememberVectorPainter(Icons.Filled.ArrowBack),
                    contentDescription = null,
                    tint = MetanMobileColors.toolbarIconColor,
                    modifier = Modifier
                        .padding(8.dp)
                        .padding(top = 12.dp)
                        .clickable { pressOnBack.invoke() }
                )
            },
            actions = {
                SupportAction(
                    modifier = Modifier.padding(top = 12.dp),
                    onSupportClicked = onSupportClicked
                )
            },
            backgroundColor = MetanMobileColors.primary,
            modifier = Modifier.fillMaxWidth(),
            elevation = elevation
        )
        AppbarBottomExpanderView()
    }
}

@Composable
fun MetanMobileToolbarWithNavIconAndShareButton(
    @StringRes titleResId: Int? = null,
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
    pressOnBack: () -> Unit,
    onShareActionClicked: () -> Unit,
) {
    TopAppBar(
        title = {
            if (titleResId != null) {
                Text(
                    stringResource(titleResId),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    style = MetanMobileTypography.h2
                )
            }
        },
        navigationIcon = {
            Icon(
                rememberVectorPainter(Icons.Filled.ArrowBack),
                contentDescription = null,
                tint = MetanMobileColors.toolbarIconColor,
                modifier = Modifier
                    .padding(8.dp)
                    .padding(top = 12.dp)
                    .clickable { pressOnBack.invoke() }
            )
        },
        actions = {
            ShareAction(
                modifier = Modifier.padding(top = 12.dp),
                onShareActionClicked = onShareActionClicked
            )
        },
        backgroundColor = MetanMobileColors.primary,
        modifier = Modifier.fillMaxWidth(),
        elevation = elevation
    )
}

@Composable
fun MetanMobileToolbarWithFilterAndSearchButtons(
    @StringRes titleResId: Int? = null,
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
    onSearchActionClicked: () -> Unit,
    onFilterActionClicked: () -> Unit,
) {
    TopAppBar(
        title = {
            if (titleResId != null) {
                Text(
                    stringResource(titleResId),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    style = MetanMobileTypography.h1
                )
            }
        },
        actions = {
            SearchButtonAction(
                modifier = Modifier.padding(top = 12.dp),
                onSearchActionClicked = onSearchActionClicked
            )
            FilterAction(
                modifier = Modifier.padding(top = 12.dp),
                onFilterActionClicked = onFilterActionClicked
            )
        },
        backgroundColor = MetanMobileColors.primary,
        modifier = Modifier.fillMaxWidth(),
        elevation = elevation
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MetanMobileToolbarWithFilterAndSearchField(
    @StringRes titleResId: Int? = null,
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
    searchText: String,
    placeholderText: String = "",
    onSearchTextChanged: (String) -> Unit = {},
    onClearClick: () -> Unit = {},
    onNavigateBack: () -> Unit = {},
    onFilterActionClicked: () -> Unit,
) {
    var showClearButton by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    TopAppBar(
        title = {
            if (titleResId != null) {
                Text(
                    stringResource(titleResId),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    style = MetanMobileTypography.h2
                )
            }
        },
        navigationIcon = {
            Icon(
                rememberVectorPainter(Icons.Filled.ArrowBack),
                contentDescription = null,
                tint = MetanMobileColors.toolbarIconColor,
                modifier = Modifier
                    .padding(8.dp)
                    .padding(top = 12.dp)
                    .clickable { onNavigateBack.invoke() }
            )
        },
        actions = {
            TextField(
                modifier = Modifier
                    .padding(top = 6.dp)
                    .weight(1f)
                    .onFocusChanged { focusState ->
                        showClearButton = (focusState.isFocused)
                    }
                    .focusRequester(focusRequester),
                value = searchText,
                onValueChange = onSearchTextChanged,
                placeholder = {
                    Text(
                        text = placeholderText,
                        color = Gray400
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Blue,
                    unfocusedIndicatorColor = Gray400,
                    backgroundColor = Color.Transparent,
                    cursorColor = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                ),
                trailingIcon = {
                    AnimatedVisibility(
                        visible = showClearButton,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        IconButton(
                            onClick = { onClearClick() }
                        ) {
                            Icon(
                                modifier = Modifier
                                    .padding(15.dp)
                                    .size(24.dp),
                                imageVector = Icons.Outlined.Close,
                                contentDescription = stringResource(R.string.placeholder_clear_search),
                                tint = MetanMobileColors.toolbarIconColor
                            )
                        }

                    }
                },
                maxLines = 1,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                }),
            )
            FilterAction(
                modifier = Modifier.padding(top = 12.dp),
                onFilterActionClicked = onFilterActionClicked
            )
        },
        backgroundColor = MetanMobileColors.primary,
        modifier = Modifier.fillMaxWidth(),
        elevation = elevation,
    )

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Composable
fun MetanMobileToolbarWithNavIconAndRemoveButton(
    @StringRes titleResId: Int? = null,
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
    pressOnBack: () -> Unit,
    onRemoveActionClicked: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        TopAppBar(
            title = {
                if (titleResId != null) {
                    Text(
                        stringResource(titleResId),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp),
                        style = MetanMobileTypography.h2
                    )
                }
            },
            navigationIcon = {
                Icon(
                    rememberVectorPainter(Icons.Filled.ArrowBack),
                    contentDescription = null,
                    tint = MetanMobileColors.toolbarIconColor,
                    modifier = Modifier
                        .padding(8.dp)
                        .padding(top = 12.dp)
                        .clickable { pressOnBack.invoke() }
                )
            },
            actions = {
                RemoveAction(
                    modifier = Modifier.padding(top = 12.dp),
                    onRemoveActionClicked = onRemoveActionClicked
                )
            },
            backgroundColor = MetanMobileColors.primary,
            modifier = Modifier.fillMaxWidth(),
            elevation = elevation
        )
        AppbarBottomExpanderView()
    }
}

@Composable
fun UserAction(
    modifier: Modifier,
    onUserClicked: () -> Unit,
) {
    IconButton(
        onClick = {
            onUserClicked.invoke()
        },
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.Person,
            contentDescription = "user_icon",
            tint = MetanMobileColors.toolbarIconColor
        )
    }
}

@Composable
fun RemoveAction(
    modifier: Modifier,
    onRemoveActionClicked: () -> Unit,
) {
    IconButton(
        onClick = {
            onRemoveActionClicked.invoke()
        },
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "remove_icon",
            tint = MetanMobileColors.toolbarIconColor
        )
    }
}

@Composable
fun SettingsAction(
    modifier: Modifier,
    onSettingsClicked: () -> Unit,
) {
    IconButton(
        onClick = {
            onSettingsClicked.invoke()
        },
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.Settings,
            contentDescription = "settings_icon",
            tint = MetanMobileColors.toolbarIconColor
        )
    }
}

@Composable
fun SupportAction(
    modifier: Modifier,
    onSupportClicked: () -> Unit,
) {
    IconButton(
        onClick = {
            onSupportClicked.invoke()
        },
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.Phone,
            contentDescription = "support_icon",
            tint = MetanMobileColors.toolbarIconColor,
        )
    }
}

@Composable
fun SearchButtonAction(
    modifier: Modifier,
    onSearchActionClicked: () -> Unit,
) {
    IconButton(
        onClick = {
            onSearchActionClicked.invoke()
        },
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Outlined.Search,
            contentDescription = "search_icon",
            tint = MetanMobileColors.toolbarIconColor,
        )
    }
}

@Composable
fun FilterAction(
    modifier: Modifier,
    onFilterActionClicked: () -> Unit,
) {
    IconButton(
        onClick = {
            onFilterActionClicked.invoke()
        },
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Outlined.FilterList,
            contentDescription = "filter_icon",
            tint = MetanMobileColors.toolbarIconColor,
        )
    }
}

@Composable
fun ShareAction(
    modifier: Modifier,
    onShareActionClicked: () -> Unit,
) {
    IconButton(
        onClick = {
            onShareActionClicked.invoke()
        },
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.Share,
            contentDescription = "share_icon",
            tint = MetanMobileColors.toolbarIconColor,
        )
    }
}

@Composable
fun CabinetMoreAction(
    modifier: Modifier,
    onOpenInBrowserClicked: () -> Unit,
    onGetAccessClicked: () -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    IconButton(
        onClick = {
            expanded = true
        },
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = "more_icon",
            tint = MetanMobileColors.toolbarIconColor
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onOpenInBrowserClicked.invoke()
                }
            ) {
                Text(text = stringResource(R.string.button_open_in_browser))
            }
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onGetAccessClicked.invoke()
                }
            ) {
                Text(text = stringResource(R.string.button_how_to_get_access))
            }
        }
    }
}

