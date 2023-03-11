package com.ngapp.metanmobile.presentation.settings.view.faq

import com.ngapp.metanmobile.data.model.dto.faq.FaqDto

data class FaqViewState(
    val faq: List<FaqDto> = emptyList(),
)

sealed class FaqEvent {
    object LoadFaq : FaqEvent()
}