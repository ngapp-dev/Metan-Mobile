package com.ngapp.metanmobile.domain.usecase.news

import com.ngapp.metanmobile.data.repository.news.NewsRepository
import com.ngapp.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetNewsDetailTest : MockkUnitTest() {

    @MockK(relaxed = true)
    lateinit var newsRepo: NewsRepository

    @SpyK
    @InjectMockKs
    private lateinit var newsDetail: GetNewsDetail

    @Test
    fun verifyExecute() = runTest {
        // Arrange (Given)
        val detailId = -1

        // Act (When)
        val params = GetNewsDetail.Params(detailId = detailId)
        newsDetail.invoke(params)

        // Assert (Then)
        coVerify { newsDetail.invoke(any()) }
    }

    @Test
    fun collectExecute() = runTest {
        // Arrange (Given)
        val detailId = 1

        // Act (When)
        val param = GetNewsDetail.Params(detailId = detailId)
        newsDetail.invoke(param).single()

        // Assert (Then)
        coVerify { newsRepo.getNews(newsId = detailId) }
    }
}