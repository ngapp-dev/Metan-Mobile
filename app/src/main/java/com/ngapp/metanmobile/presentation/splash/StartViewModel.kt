package com.ngapp.metanmobile.presentation.splash

import com.ngapp.framework.base.mvvm.MvvmViewModel
import com.ngapp.metanmobile.domain.usecase.welcome.ReadOnBoarding
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val readOnBoarding: ReadOnBoarding
) : MvvmViewModel() {

    private val _startWelcome = MutableStateFlow(false)
    val startWelcome = _startWelcome.asStateFlow()

    init {
        readOnBoardingState()
    }

    private fun readOnBoardingState() = safeLaunch {
        call(readOnBoarding(Unit)) { completed ->
            _startWelcome.value = !completed
        }
    }
}