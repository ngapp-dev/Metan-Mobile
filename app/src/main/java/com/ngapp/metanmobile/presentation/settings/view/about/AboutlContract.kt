package com.ngapp.metanmobile.presentation.settings.view.about

import com.ngapp.metanmobile.data.model.dto.githubuser.GithubUserDto

data class AboutViewState(
    val githubUser: GithubUserDto? = null
)

sealed class AboutEvent {
    object LoadGithubUser : AboutEvent()
}