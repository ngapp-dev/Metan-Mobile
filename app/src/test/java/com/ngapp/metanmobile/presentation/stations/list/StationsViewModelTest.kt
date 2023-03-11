package com.ngapp.metanmobile.presentation.stations.list


import app.cash.turbine.test
import com.ngapp.framework.base.mvi.BaseViewState
import com.ngapp.metanmobile.domain.mockdata.MockData
import com.ngapp.testutils.MockkUnitTest
import com.google.common.truth.Truth
import com.ngapp.framework.network.apiCall
import com.ngapp.metanmobile.domain.usecase.station.GetStations
import com.ngapp.metanmobile.domain.usecase.station.favorite.DeleteStationFavorite
import com.ngapp.metanmobile.domain.usecase.station.favorite.GetStationFavorites
import com.ngapp.metanmobile.domain.usecase.station.favorite.UpdateStationFavorite
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

class StationsViewModelTest : MockkUnitTest() {
    @RelaxedMockK
    lateinit var getStations: GetStations

    @RelaxedMockK
    lateinit var updateFavorite: UpdateStationFavorite

    @MockK(relaxed = true)
    lateinit var getFavorites: GetStationFavorites

    @MockK(relaxed = true)
    lateinit var deleteFavorite: DeleteStationFavorite

    @SpyK
    @InjectMockKs
    lateinit var viewModel: StationsViewModel

    @Test
    fun verifyOnTriggerEventLoadStations() = runTest {
        // Arrange (Given)
        coEvery { getStations.invoke(any()) } returns flow {
            emit(apiCall { MockData.getStationDtoList() })
        }

        // Act (When)
        viewModel.onTriggerEvent(StationsEvent.LoadStations)

        // Assert (Then)
        coVerify { getStations.invoke(any()) }
    }

    @Test
    fun verifyOnTriggerEventAddOrRemoveStation() = runTest {
        // Arrange (Given)
        val dto = MockData.getStationDto()

        // Act (When)
        viewModel.onTriggerEvent(StationsEvent.AddOrRemoveFavorite(dto))

        // Assert (Then)
        coVerify { updateFavorite(UpdateStationFavorite.Params(dto)) }
    }

    @Test
    fun verifyOnTriggerEventGetFavorites() = runTest {
        // Arrange (Given)

        // Act (When)
        viewModel.onTriggerEvent(StationsEvent.LoadFavorites)

        // Assert (Then)
        coVerify { getFavorites.invoke(Unit) }
    }

    @Test
    fun verifyOnTriggerEventDeleteFavorite() = runTest {
        // Arrange (Given)
        val id = 1

        // Act (When)
        viewModel.onTriggerEvent(StationsEvent.DeleteFavorite(id))

        // Assert (Then)
        coVerify { deleteFavorite(DeleteStationFavorite.Params(id)) }
    }

    @Test
    fun verifyCheckState() = runTest {
        // Arrange (Given)
        val response = apiCall { MockData.getStationDtoList() }
        coEvery { getStations(any()) } returns flow { emit(response) }

        // Act (When)
        viewModel.onTriggerEvent(StationsEvent.LoadStations)

        // Assert (Then)
        viewModel.uiState.test {
            awaitItem().apply {
                Truth.assertThat(this).isNotNull()
                Truth.assertThat(this).isInstanceOf(BaseViewState::class.java)
            }
        }
    }
}