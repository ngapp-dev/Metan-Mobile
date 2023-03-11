package com.ngapp.metanmobile.domain.usecase.language

import com.ngapp.metanmobile.data.repository.language.LanguageRepository
import com.ngapp.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetLanguageTest : MockkUnitTest() {
    @RelaxedMockK
    lateinit var repository: LanguageRepository

    @SpyK
    @InjectMockKs
    private lateinit var getLanguage: GetLanguage

    @Test
    fun verifyExecute() = runTest {
        // Arrange (Given)

        // Act (When)
        getLanguage.invoke(Unit)

        // Assert (Then)
        coVerify { repository.getLanguage }
    }
}