package com.example.core.data.remote

import com.example.core.data.entity.character.*
import com.example.core.data.entity.location.OriginResponse
import com.example.core.di.networking.Resource
import com.example.core.di.networking.api_service.ApiServiceCharacter
import com.example.core.di.networking.api_service.ApiServiceOrigin
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import retrofit2.Response

class RemoteDataSourceTest {

    private lateinit var apiServiceCharacter: ApiServiceCharacter
    private lateinit var apiServiceOrigin: ApiServiceOrigin
    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setup() {
        apiServiceCharacter = mockk()
        apiServiceOrigin = mockk()
        remoteDataSource = RemoteDataSource(apiServiceCharacter, apiServiceOrigin)
    }


    @Test
    fun testGetCharacters() = runBlocking {
        val testInfo = Info(10, "nextPageUrl", 20, null)
        val testResult = Result("createdDate", listOf("episode1", "episode2"), "gender", 1, "imageUrl", Location("name", "url"), "name", Origin("name", "url"), "species", "status", "type", "url")
        val expectedResponse = RickAndMortyResponse(testInfo, arrayListOf(testResult))
        val expected = Response.success(expectedResponse)
        coEvery { apiServiceCharacter.getCharacters(any()) } returns expected
        val result = remoteDataSource.getCharacters(1)
        assertEquals(expectedResponse, result.data)
    }

    @Test
    fun testGetOrigins() = runBlocking {
        val testInfo = com.example.core.data.entity.location.Info(10, "nextPageUrl", 20, null)
        val testResult = com.example.core.data.entity.location.Result("created", "dimension",1,"name",listOf("resident1", "resident2"),"type","url")
        val expectedResponse = OriginResponse(testInfo, arrayListOf(testResult))
        val expected = Response.success(expectedResponse)
        coEvery { apiServiceOrigin.getOrigins() } returns expected
        val result = remoteDataSource.getOrigins()
        assertEquals(expectedResponse, result.data)
    }
}