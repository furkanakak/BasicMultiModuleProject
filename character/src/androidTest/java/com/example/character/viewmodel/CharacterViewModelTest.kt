package com.example.character.viewmodel

import com.example.character.ui.CharacterViewModel
import com.example.core.data.entity.character.Info
import com.example.core.data.entity.character.Location
import com.example.core.data.entity.character.Origin
import com.example.core.data.entity.character.Result
import com.example.core.data.entity.character.RickAndMortyResponse
import com.example.core.data.repository.Repository
import com.example.core.di.networking.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CharacterViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var viewModel: CharacterViewModel
    private val repository = mockk<Repository>(relaxed = true)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = CharacterViewModel(repository)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun testGetCharactersSuccess() = runTest {

        val testResponse = mockk<RickAndMortyResponse>()
        val expected = Resource.Success(testResponse)

        coEvery { repository.getCharacters(any()) } returns expected
        viewModel.getCharacters(1)
        assertEquals(expected, viewModel.characters.value)
    }

    @Test
    fun testGetCharactersError() = runTest {

        val exceptionMessage = "API error"
        coEvery { repository.getCharacters(any()) } throws Exception(exceptionMessage)
        viewModel.getCharacters(1)
        val result = viewModel.characters.value
        assertTrue(result is Resource.Error)
        assertEquals(exceptionMessage, (result as Resource.Error).errorMessage)
    }
}