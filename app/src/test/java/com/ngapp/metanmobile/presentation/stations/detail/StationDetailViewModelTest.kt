package com.ngapp.metanmobile.presentation.stations.detail

import app.cash.turbine.test
import com.ngapp.framework.base.mvi.BaseViewState
import com.ngapp.framework.network.DataState
import com.ngapp.testutils.MockkUnitTest
import com.google.common.truth.Truth
import com.ngapp.metanmobile.data.model.dto.station.StationDto
import com.ngapp.metanmobile.domain.usecase.station.GetStationDetail
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

class StationDetailViewModelTest : MockkUnitTest() {
    @RelaxedMockK
    lateinit var getStationDetail: GetStationDetail

    @SpyK
    @InjectMockKs
    lateinit var viewModel: StationDetailViewModel

    @Test
    fun verifyOnTriggerEventLoadDetailWithDetailId() = runTest {
        // Arrange (Given)
        val code = "agnks_grodno2"

        // Act (When)
        viewModel.onTriggerEvent(StationDetailEvent.LoadDetail(code))

        // Assert (Then)
        coVerify { getStationDetail.invoke(GetStationDetail.Params(stationCode = code)) }
    }

    @Test
    fun verifyOnTriggerEventLoadDetail_CheckState() = runTest {
        // Arrange (Given)
        val code = "agnks_grodno2"
        val dto = mockk<StationDto>()
        val params = GetStationDetail.Params(stationCode = code)
        coEvery { getStationDetail.invoke(params) } returns flow {
            emit(DataState.Success(dto))
        }

        // Act (When)
        viewModel.onTriggerEvent(StationDetailEvent.LoadDetail(code))

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
        val code = "agnks_grodno2"
        coEvery { getStationDetail(any()) } returns flow {
            emit(DataState.Error(IOException("this is a test exception.")))
        }

        // when
        viewModel.onTriggerEvent(StationDetailEvent.LoadDetail(code))

        // then
        coVerify(exactly = 1) { getStationDetail(any()) }
        confirmVerified(getStationDetail)

        // assertThrows<RuntimeException> { viewModel.uiState as BaseViewState.Error }
    }
}