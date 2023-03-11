package com.ngapp.metanmobile.presentation.news.detail

import app.cash.turbine.test
import com.ngapp.framework.base.mvi.BaseViewState
import com.ngapp.framework.network.DataState
import com.ngapp.testutils.MockkUnitTest
import com.google.common.truth.Truth
import com.ngapp.metanmobile.app.tools.Analytics
import com.ngapp.metanmobile.data.model.dto.news.NewsDto
import com.ngapp.metanmobile.domain.usecase.news.GetNewsDetail
import com.ngapp.metanmobile.domain.usecase.news.ShareNews
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException

class NewsDetailViewModelTest : MockkUnitTest() {
    @RelaxedMockK
    lateinit var getNewsDetail: GetNewsDetail

    @RelaxedMockK
    lateinit var shareNews: ShareNews

    @RelaxedMockK
    lateinit var analytics: Analytics

    @SpyK
    @InjectMockKs
    lateinit var viewModel: NewsDetailViewModel

    @Test
    fun verifyOnTriggerEventShareNews() = runTest {
        // Arrange (Given)
        val params = ShareNews.Params(NewsDto.init())

        // Act (When)
        viewModel.onTriggerEvent(NewsDetailEvent.ShareNews)

        // Assert (Then)
        coVerify { shareNews(params) }
    }

    @Test
    fun verifyOnTriggerEventLoadDetailWithDetailId() = runTest {
        // Arrange (Given)
        val detailId = 1

        // Act (When)
        viewModel.onTriggerEvent(NewsDetailEvent.LoadDetail(detailId))

        // Assert (Then)
        analytics.trackScreenView(
            screenName = "NewsDetail->onLoadDetail",
            className = "NewsDetailScreen"
        )
        coVerify { getNewsDetail.invoke(GetNewsDetail.Params(detailId = detailId)) }
    }

    @Test
    fun verifyOnTriggerEventLoadDetail_CheckState() = runTest {
        // Arrange (Given)
        val detailId = 1
        val dto = mockk<NewsDto>()
        val params = GetNewsDetail.Params(detailId = detailId)
        coEvery { getNewsDetail.invoke(params) } returns flow {
            emit(DataState.Success(dto))
        }

        // Act (When)
        viewModel.onTriggerEvent(NewsDetailEvent.LoadDetail(detailId))

        // Assert (Then)
        viewModel.uiState.test {
            awaitItem().apply {
                Truth.assertThat(this).isNotNull()
                Truth.assertThat(this).isInstanceOf(BaseViewState::class.java)
            }
        }
    }

    @Test
    fun verifyOnTriggerEventLoadDetail_CheckError() = runTest {
        // given
        val detailId = 1
        coEvery { getNewsDetail(any()) } returns flow {
            emit(DataState.Error(IOException("this is a test exception.")))
        }

        // when
        viewModel.onTriggerEvent(NewsDetailEvent.LoadDetail(detailId))

        // then
        coVerify(exactly = 1) { getNewsDetail(any()) }
        confirmVerified(getNewsDetail)

        // assertThrows<RuntimeException> { viewModel.uiState as BaseViewState.Error }
    }
}