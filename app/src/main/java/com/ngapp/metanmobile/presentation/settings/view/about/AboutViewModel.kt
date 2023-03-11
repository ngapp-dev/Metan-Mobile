package com.ngapp.metanmobile.presentation.settings.view.about

import com.ngapp.framework.base.mvi.BaseViewState
import com.ngapp.framework.base.mvi.MviViewModel
import com.ngapp.metanmobile.app.tools.Analytics
import com.ngapp.metanmobile.domain.usecase.githubuser.GetGithubUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(
    private val getGithubUser: GetGithubUser,
    private val analytics: Analytics
) : MviViewModel<BaseViewState<AboutViewState>, AboutEvent>() {

    override fun onTriggerEvent(eventType: AboutEvent) {
        when (eventType) {
            is AboutEvent.LoadGithubUser -> onLoadGithubUser()
        }
    }

    private fun onLoadGithubUser() = safeLaunch {
        analytics.trackScreenView(
            screenName = "About->onLoadGithubUser",
            className = "AboutScreen"
        )
        execute(getGithubUser(Unit)) { dto ->
            setState(BaseViewState.Data(AboutViewState(githubUser = dto)))
        }
    }
}