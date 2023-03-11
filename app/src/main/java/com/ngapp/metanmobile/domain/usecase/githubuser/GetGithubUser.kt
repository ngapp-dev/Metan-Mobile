package com.ngapp.metanmobile.domain.usecase.githubuser

import androidx.annotation.VisibleForTesting
import com.ngapp.framework.network.DataState
import com.ngapp.framework.network.apiCall
import com.ngapp.framework.usecase.DataStateUseCase
import com.ngapp.metanmobile.data.model.dto.githubuser.GithubUserDto
import com.ngapp.metanmobile.data.model.dto.githubuser.toGitHubUserDto
import com.ngapp.metanmobile.data.repository.settings.SettingsRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetGithubUser @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val settingsRepo: SettingsRepository,
) : DataStateUseCase<Unit, GithubUserDto>() {

    override suspend fun FlowCollector<DataState<GithubUserDto>>.execute(params: Unit) {
        val getGithubUser = settingsRepo.getGithubUser().toGitHubUserDto()
        val service = apiCall { getGithubUser }

        emit(service)
    }
}