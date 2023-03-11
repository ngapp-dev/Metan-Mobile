package com.ngapp.metanmobile.presentation.welcome.onboarding

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.ngapp.framework.base.mvvm.MvvmViewModel
import com.ngapp.metanmobile.domain.usecase.welcome.ReadOnBoarding
import com.ngapp.metanmobile.domain.usecase.welcome.SaveOnBoarding
import com.ngapp.metanmobile.presentation.welcome.navgraph.WelcomeScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val saveOnBoarding: SaveOnBoarding,
    private val readOnBoarding: ReadOnBoarding
) : MvvmViewModel() {

    private val _startDestination: MutableState<String> =
        mutableStateOf(WelcomeScreen.OnBoarding.route)
    val startDestination: State<String> = _startDestination

    fun saveOnBoardingState(completed: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        val params = SaveOnBoarding.Params(completed)
        call(saveOnBoarding(params))
    }

    fun readOnBoardingState() = viewModelScope.launch {
        readOnBoarding(Unit).collect { completed ->
            if (completed) {
                _startDestination.value = WelcomeScreen.Environment.route
            } else {
                _startDestination.value = WelcomeScreen.OnBoarding.route
            }
        }
    }
}