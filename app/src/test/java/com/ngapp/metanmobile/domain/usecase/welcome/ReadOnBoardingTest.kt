package com.ngapp.metanmobile.domain.usecase.welcome

import com.ngapp.metanmobile.data.repository.welcome.WelcomeRepository
import com.ngapp.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ReadOnBoardingTest : MockkUnitTest() {
    @RelaxedMockK
    lateinit var repository: WelcomeRepository

    @SpyK
    @InjectMockKs
    private lateinit var readOnBoarding: ReadOnBoarding

    @Test
    fun verifyExecute() = runTest {
        // Arrange (Given)

        // Act (When)
        readOnBoarding.invoke(Unit)

        // Assert (Then)
        coVerify { repository.readOnBoardingState() }
    }
}