package com.example.origin.viewmodel
import com.example.core.data.entity.location.OriginResponse
import com.example.core.data.repository.Repository
import com.example.core.di.networking.Resource
import com.example.origin.ui.LocationViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Test
import org.junit.Before
import org.junit.Assert.*


@ExperimentalCoroutinesApi
class LocationViewModelTest {
    private val testDispatcher = TestCoroutineDispatcher()
    private val repository = mockk<Repository>(relaxed = true)
    private lateinit var viewModel: LocationViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = LocationViewModel(repository)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun testGetOriginsSuccess() = runTest {
        val testResponse = mockk<OriginResponse>()
        val expected = Resource.Success(testResponse)

        coEvery { repository.getOrigins() } returns expected
        viewModel.getOrigins()
        assertEquals(expected, viewModel.origins.first())
    }

    @Test
    fun testGetOriginsError() = runTest {
        val exceptionMessage = "API error"
        val expected = Resource.Error<OriginResponse>(exceptionMessage)

        coEvery { repository.getOrigins() } returns expected
        viewModel.getOrigins()
        assertEquals(expected, viewModel.origins.first())
    }
}