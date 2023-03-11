package com.ngapp.metanmobile.domain.usecase.news

import com.ngapp.metanmobile.data.repository.news.NewsRepository
import com.ngapp.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetCharactersTest : MockkUnitTest() {

    @MockK(relaxed = true)
    lateinit var newsRepo: NewsRepository

    @SpyK
    @InjectMockKs
    private lateinit var getNewsList: GetNews

    @Test
    fun verifyExecute() = runTest {
        // Arrange (Given)


        // Act (When)
        getNewsList.invoke(Unit)

        // Assert (Then)
        coVerify { newsRepo.getNewsList() }
    }
}