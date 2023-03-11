package com.ngapp.metanmobile.domain.usecase.faq

import androidx.annotation.VisibleForTesting
import com.ngapp.framework.network.DataState
import com.ngapp.framework.network.apiCall
import com.ngapp.framework.usecase.DataStateUseCase
import com.ngapp.metanmobile.data.model.dto.faq.FaqDto
import com.ngapp.metanmobile.data.repository.profile.ProfileRepository
import com.ngapp.metanmobile.data.repository.settings.SettingsRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetFaq @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val settingsRepo: SettingsRepository
) : DataStateUseCase<Unit, List<FaqDto>>() {

    override suspend fun FlowCollector<DataState<List<FaqDto>>>.execute(params: Unit) {
        val getFaqList = settingsRepo.getFaqList()

        val service = apiCall { getFaqList }
        emit(service)
    }
}