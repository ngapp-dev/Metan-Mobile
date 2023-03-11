package com.ngapp.metanmobile.presentation.news.list

import app.cash.turbine.test
import com.ngapp.framework.base.mvi.BaseViewState
import com.ngapp.metanmobile.domain.mockdata.MockData
import com.ngapp.testutils.MockkUnitTest
import com.google.common.truth.Truth
import com.ngapp.framework.network.apiCall
import com.ngapp.metanmobile.domain.usecase.news.GetNews
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

class NewsViewModelTest : MockkUnitTest() {
    @RelaxedMockK
    lateinit var getNews: GetNews

    @SpyK
    @InjectMockKs
    lateinit var viewModel: NewsViewModel

    @Test
    fun verifyOnTriggerEventLoadLocations() = runTest {
        // Arrange (Given)
        coEvery { getNews.invoke(any()) } returns flow {
            emit(apiCall { MockData.getNewsDtoList() })
        }

        // Act (When)
        viewModel.onTriggerEvent(NewsEvent.LoadNews)

        // Assert (Then)
        coVerify { getNews.invoke(any()) }
    }

    @Test
    fun verifyCheckState() = runTest {
        // Arrange (Given)
        val response = apiCall { MockData.getNewsDtoList() }
        coEvery { getNews(any()) } returns flow { emit(response) }

        // Act (When)
        viewModel.onTriggerEvent(NewsEvent.LoadNews)

        // Assert (Then)
        viewModel.uiState.test {
            awaitItem().apply {
                Truth.assertThat(this).isNotNull()
                Truth.assertThat(this).isInstanceOf(BaseViewState::class.java)
            }
        }
    }
}