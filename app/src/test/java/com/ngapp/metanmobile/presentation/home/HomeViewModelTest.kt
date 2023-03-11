package com.ngapp.metanmobile.presentation.home

import app.cash.turbine.test
import com.ngapp.framework.base.mvi.BaseViewState
import com.ngapp.metanmobile.domain.mockdata.MockData
import com.ngapp.testutils.MockkUnitTest
import com.google.common.truth.Truth
import com.ngapp.framework.network.apiCall
import com.ngapp.metanmobile.domain.usecase.faq.GetFaqFlow
import com.ngapp.metanmobile.domain.usecase.news.GetNews
import com.ngapp.metanmobile.domain.usecase.station.GetStationsFlow
import com.ngapp.metanmobile.domain.usecase.profile.GetProfileFlow
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

class HomeViewModelTest : MockkUnitTest() {

    @RelaxedMockK
    lateinit var getNews: GetNews

    @RelaxedMockK
    lateinit var getFaq: GetFaqFlow

    @MockK(relaxed = true)
    lateinit var getProfile: GetProfileFlow

    @MockK(relaxed = true)
    lateinit var getStations: GetStationsFlow

    @SpyK
    @InjectMockKs
    lateinit var viewModel: HomeViewModel

    @Test
    fun verifyOnTriggerEventLoadHome() = runTest {
        // Arrange (Given)
        val profileId = 1
        coEvery {
            getNews.invoke(any())
        } returns flow {
            emit(apiCall { MockData.getNewsDtoList() })
        }
        coEvery {
            getFaq.invoke(any())
        } returns flow {
            emit(MockData.getFaqDtoList())
        }

        coEvery {
            getProfile.invoke(any())
        } returns flow {
            emit((MockData.getProfileDto()))
        }

        coEvery {
            getStations.invoke(any())
        } returns flow {
            emit((MockData.getStationDtoList()))
        }

        // Act (When)
        viewModel.onTriggerEvent(HomeEvent.LoadHome(profileId))

        // Assert (Then)
        coVerify {
            getNews.invoke(any())
        }

        coVerify {
            getFaq.invoke(any())
        }

        coVerify {
            getProfile.invoke(any())
        }

        coVerify {
            getStations.invoke(any())
        }
    }

    @Test
    fun verifyOnTriggerEventLoadHome_CheckState() = runTest {
        val profileId = 1
        // Arrange (Given)
        val news = apiCall { MockData.getNewsDtoList() }
        coEvery { getNews(any()) } returns flow { emit(news) }

        val faq = MockData.getFaqDtoList()
        coEvery { getFaq(any()) } returns flow { emit(faq) }

        val profile = MockData.getProfileDto()
        coEvery { getProfile(any()) } returns flow { emit(profile) }

        val stations = MockData.getStationDtoList()
        coEvery { getStations(any()) } returns flow { emit(stations) }

        // Act (When)
        viewModel.onTriggerEvent(HomeEvent.LoadHome(profileId))

        // Assert (Then)
        viewModel.uiState.test {
            awaitItem().apply {
                Truth.assertThat(this).isNotNull()
                Truth.assertThat(this).isInstanceOf(BaseViewState::class.java)
            }
        }
    }
}