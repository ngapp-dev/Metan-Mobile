package com.ngapp.metanmobile.domain.usecase.language

import androidx.annotation.VisibleForTesting
import com.ngapp.framework.usecase.ReturnUseCase
import com.ngapp.metanmobile.data.repository.language.LanguageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLanguage @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val repository: LanguageRepository
) : ReturnUseCase<Unit, String>() {

    override suspend fun execute(params: Unit): Flow<String> {
        return repository.getLanguage
    }
}