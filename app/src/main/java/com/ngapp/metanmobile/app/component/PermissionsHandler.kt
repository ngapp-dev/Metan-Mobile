package com.ngapp.metanmobile.app.component

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.annotation.StringRes
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.DialogProperties
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.ngapp.metanmobile.R
import com.ngapp.metanmobile.app.theme.MetanMobileTypography
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PermissionsHandler {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state

    fun TriggerEvent(permissionHandlerEventType: PermissionHandlerEvent) {
        when (permissionHandlerEventType) {
            is PermissionHandlerEvent.PermissionDenied -> onPermissionDenied()
            is PermissionHandlerEvent.PermissionDismissTapped -> onPermissionDismissTapped()
            is PermissionHandlerEvent.PermissionNeverAskAgain -> onPermissionNeverShowAgain()
            is PermissionHandlerEvent.PermissionRationaleOkTapped -> onPermissionRationaleOkTapped()
            is PermissionHandlerEvent.PermissionRequired -> onPermissionRequired()
            is PermissionHandlerEvent.PermissionSettingsTapped -> onPermissionSettingsTapped()
            is PermissionHandlerEvent.PermissionsGranted -> onPermissionGranted()
            is PermissionHandlerEvent.PermissionsRequestAgain -> onPermissionsRequestAgain()
            is PermissionHandlerEvent.PermissionsStateUpdated -> onPermissionsStateUpdated(
                permissionHandlerEventType.permissionsState
            )
        }
    }

    private fun onPermissionsStateUpdated(permissionState: MultiplePermissionsState) {
        _state.update { it.copy(multiplePermissionsState = permissionState) }
    }

    private fun onPermissionGranted() {
        _state.update { it.copy(permissionAction = Action.NO_ACTION) }
    }

    private fun onPermissionDenied() {
        _state.update { it.copy(permissionAction = Action.NO_ACTION) }
    }

    private fun onPermissionNeverShowAgain() {
        _state.update {
            it.copy(permissionAction = Action.SHOW_NEVER_ASK_AGAIN)
        }
    }

    private fun onPermissionRequired() {
        _state.value.multiplePermissionsState?.let {
            val permissionAction =
                if (!it.allPermissionsGranted && !it.shouldShowRationale && !it.permissionRequested) {
                    Action.REQUEST_PERMISSION
                } else if (!it.allPermissionsGranted && it.shouldShowRationale) {
                    Action.SHOW_RATIONALE
                } else {
                    Action.SHOW_NEVER_ASK_AGAIN
                }
            _state.update { it.copy(permissionAction = permissionAction) }
        }
    }

    private fun onPermissionRationaleOkTapped() {
        _state.update { it.copy(permissionAction = Action.REQUEST_PERMISSION) }
    }

    private fun onPermissionDismissTapped() {
        _state.update { it.copy(permissionAction = Action.NO_ACTION) }
    }

    private fun onPermissionSettingsTapped() {
        _state.update { it.copy(permissionAction = Action.NO_ACTION) }
    }

    private fun onPermissionsRequestAgain() {
        _state.value.multiplePermissionsState?.let {
            val permissionAction =
                if (!it.allPermissionsGranted && !it.shouldShowRationale && !it.permissionRequested) {
                    Action.REQUEST_PERMISSION
                } else if (!it.allPermissionsGranted && it.shouldShowRationale) {
                    Action.SHOW_RATIONALE
                } else {
                    Action.REQUEST_PERMISSION_AGAIN
                }
            _state.update { it.copy(permissionAction = permissionAction) }
        }
    }

    data class State(
        val multiplePermissionsState: MultiplePermissionsState? = null,
        val permissionAction: Action = Action.NO_ACTION
    )

    sealed class PermissionHandlerEvent {
        object PermissionDenied : PermissionHandlerEvent()
        object PermissionsGranted : PermissionHandlerEvent()
        object PermissionSettingsTapped : PermissionHandlerEvent()
        object PermissionNeverAskAgain : PermissionHandlerEvent()
        object PermissionDismissTapped : PermissionHandlerEvent()
        object PermissionRationaleOkTapped : PermissionHandlerEvent()
        object PermissionRequired : PermissionHandlerEvent()
        object PermissionsRequestAgain : PermissionHandlerEvent()

        data class PermissionsStateUpdated(val permissionsState: MultiplePermissionsState) :
            PermissionHandlerEvent()
    }

    enum class Action {
        REQUEST_PERMISSION, SHOW_RATIONALE, SHOW_NEVER_ASK_AGAIN, REQUEST_PERMISSION_AGAIN, NO_ACTION
    }

    companion object {
        val permissions = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        private val _showDialog = MutableStateFlow(false)
        val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()
    }
}

@Composable
fun HandlePermissionsRequest(permissions: List<String>, permissionsHandler: PermissionsHandler) {

    val state by permissionsHandler.state.collectAsState()
    val permissionsState = rememberMultiplePermissionsState(permissions)

    LaunchedEffect(permissionsState) {
        permissionsHandler.TriggerEvent(
            PermissionsHandler.PermissionHandlerEvent.PermissionsStateUpdated(
                permissionsState
            )
        )
        when {
            permissionsState.allPermissionsGranted -> {
                permissionsHandler.TriggerEvent(PermissionsHandler.PermissionHandlerEvent.PermissionsGranted)
            }
            permissionsState.permissionRequested && !permissionsState.shouldShowRationale -> {
                permissionsHandler.TriggerEvent(PermissionsHandler.PermissionHandlerEvent.PermissionNeverAskAgain)
            }
            else -> {
                permissionsHandler.TriggerEvent(PermissionsHandler.PermissionHandlerEvent.PermissionDenied)
            }
        }
    }
    HandlePermissionAction(
        action = state.permissionAction,
        permissionStates = state.multiplePermissionsState,
        rationaleText = R.string.permission_rationale,
        neverAskAgainText = R.string.permission_rationale,
        onConfirmClick = { permissionsHandler.TriggerEvent(PermissionsHandler.PermissionHandlerEvent.PermissionRationaleOkTapped) },
        onSettingsClick = { permissionsHandler.TriggerEvent(PermissionsHandler.PermissionHandlerEvent.PermissionSettingsTapped) },
    )
}

@Composable
fun HandlePermissionAction(
    action: PermissionsHandler.Action,
    permissionStates: MultiplePermissionsState?,
    @StringRes rationaleText: Int,
    @StringRes neverAskAgainText: Int,
    onConfirmClick: () -> Unit,
    onSettingsClick: () -> Unit,
) {
    val context = LocalContext.current

    when (action) {
        PermissionsHandler.Action.REQUEST_PERMISSION -> {
            LaunchedEffect(true) {
                permissionStates?.launchMultiplePermissionRequest()
            }
        }
        PermissionsHandler.Action.SHOW_RATIONALE -> {
            PermissionRationaleDialog(
                message = stringResource(rationaleText),
                onConfirmClick = { onConfirmClick() },
            )
        }
        PermissionsHandler.Action.SHOW_NEVER_ASK_AGAIN -> {
//            Toast.makeText(context, R.string.permission_denied, Toast.LENGTH_SHORT).show()
        }
        PermissionsHandler.Action.REQUEST_PERMISSION_AGAIN -> {
            ShowGotoSettingsDialog(
                title = stringResource(R.string.permission_button_allow),
                message = stringResource(neverAskAgainText),
                onSettingsClick = {
                    onSettingsClick()
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                        data = Uri.fromParts("package", context.packageName, null)
                    }
                    context.startActivity(intent)
                },
            )
        }
        PermissionsHandler.Action.NO_ACTION -> Unit
    }
}

@Composable
fun PermissionRationaleDialog(
    message: String,
    title: String = stringResource(R.string.permission_button_allow),
    primaryButtonText: String = stringResource(R.string.permission_button_confirm),
    onConfirmClick: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = { },
        title = {
            Text(
                text = title,
                style = MetanMobileTypography.h2
            )
        },
        text = {
            Text(
                text = message,
                style = MetanMobileTypography.h6
            )
        },
        confirmButton = {
            TextButton(
                onClick = { onConfirmClick() }
            )
            {
                Text(text = primaryButtonText)
            }
        },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    )
}

@Composable
fun ShowGotoSettingsDialog(
    title: String,
    message: String,
    primaryButtonText: String = stringResource(R.string.permission_button_settings),
    onSettingsClick: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = { },
        title = {
            Text(
                text = title,
                style = MetanMobileTypography.h2
            )
        },
        text = {
            Text(
                text = message,
                style = MetanMobileTypography.h6
            )
        },
        confirmButton = {
            TextButton(
                onClick = { onSettingsClick() }
            )
            {
                Text(text = primaryButtonText)
            }
        },
    )
}
