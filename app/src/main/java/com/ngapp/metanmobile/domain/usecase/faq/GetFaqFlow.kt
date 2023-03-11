package com.ngapp.metanmobile.domain.usecase.faq

import androidx.annotation.VisibleForTesting
import com.ngapp.framework.usecase.FlowListUseCase
import com.ngapp.metanmobile.data.model.dto.faq.FaqDto
import com.ngapp.metanmobile.data.repository.profile.ProfileRepository
import com.ngapp.metanmobile.data.repository.settings.SettingsRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetFaqFlow @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val settingsRepo: SettingsRepository,
) : FlowListUseCase<Unit, List<FaqDto>>() {

    override suspend fun FlowCollector<List<FaqDto>>.execute(params: Unit) {
        val getFaq = settingsRepo.getFaqList()
        emit(getFaq)
    }
}