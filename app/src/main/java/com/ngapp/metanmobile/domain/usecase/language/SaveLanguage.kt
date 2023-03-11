package com.ngapp.metanmobile.domain.usecase.language

import androidx.annotation.VisibleForTesting
import com.ngapp.framework.usecase.LocalUseCase
import com.ngapp.metanmobile.data.repository.language.LanguageRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class SaveLanguage @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val repository: LanguageRepository
) : LocalUseCase<SaveLanguage.Params, Unit>() {
    data class Params(
        val language: String
    )

    override suspend fun FlowCollector<Unit>.execute(params: Params) {
        repository.setLanguage(params.language)
        emit(Unit)
    }
}