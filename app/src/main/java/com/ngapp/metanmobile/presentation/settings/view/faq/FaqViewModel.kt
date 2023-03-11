package com.ngapp.metanmobile.presentation.settings.view.faq

import com.ngapp.framework.base.mvi.BaseViewState
import com.ngapp.framework.base.mvi.MviViewModel
import com.ngapp.metanmobile.domain.usecase.faq.GetFaq
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FaqViewModel @Inject constructor(
    private val getFaqList: GetFaq,
) : MviViewModel<BaseViewState<FaqViewState>, FaqEvent>() {

    override fun onTriggerEvent(eventType: FaqEvent) {
        when (eventType) {
            is FaqEvent.LoadFaq -> onLoadFaq()
        }
    }

    private fun onLoadFaq() = safeLaunch {
        setState(BaseViewState.Loading)
        execute(getFaqList(Unit)) { faqListDto ->
            setState(BaseViewState.Data(FaqViewState(faq = faqListDto)))
        }
    }
}