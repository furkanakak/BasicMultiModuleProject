package com.example.core.data.repository
import com.example.core.data.entity.character.*
import com.example.core.data.entity.location.OriginResponse
import com.example.core.data.remote.RemoteDataSource
import com.example.core.di.networking.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals

class RepositoryTest {

    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var repository: Repository

    @Before
    fun setup() {
        remoteDataSource = mockk()
        repository = Repository(remoteDataSource)
    }


    @Test
    fun testGetCharacters() = runBlocking {
        val testInfo = Info(10, "nextPageUrl", 20, null)
        val testResult = Result("createdDate", listOf("episode1", "episode2"), "gender", 1, "imageUrl", Location("name", "url"), "name", Origin("name", "url"), "species", "status", "type", "url")
        val expectedResponse = RickAndMortyResponse(testInfo, arrayListOf(testResult))
        val expected = Resource.Success(expectedResponse)
        coEvery { remoteDataSource.getCharacters(any()) } returns expected
        val result = repository.getCharacters(0)
        assertEquals(expected, result)
    }


    @Test
    fun testGetOrigins() = runBlocking {
        val testInfo = com.example.core.data.entity.location.Info(10, "nextPageUrl", 20, null)
        val testResult = com.example.core.data.entity.location.Result("created", "dimension",1,"name",listOf("resident1", "resident2"),"type","url")
        val testOriginResponse = OriginResponse(testInfo, arrayListOf(testResult))
        val expected = Resource.Success(testOriginResponse)
        coEvery { remoteDataSource.getOrigins() } returns expected
        val result = repository.getOrigins()
        assertEquals(expected, result)
    }


    @Test
    fun testSuccessfulAPIResponseForGetCharacters() = runBlocking {
        val testInfo = Info(10, "nextPageUrl", 20, null)
        val testResult = Result("createdDate", listOf("episode1", "episode2"), "gender", 1, "imageUrl", Location("name", "url"), "name", Origin("name", "url"), "species", "status", "type", "url")
        val expectedResponse = RickAndMortyResponse(testInfo, arrayListOf(testResult))
        val expected = Resource.Success(expectedResponse)
        coEvery { remoteDataSource.getCharacters(any()) } returns expected
        val result = repository.getCharacters(1)
        assertEquals(expected, result)
    }

    @Test
    fun testFailedAPIResponseForGetCharacters() = runBlocking {
        val expected = Resource.Error<RickAndMortyResponse>("API Error")
        coEvery { remoteDataSource.getCharacters(any()) } returns expected
        val result = repository.getCharacters(1)
        assertEquals(expected.errorMessage, result.errorMessage)
    }


    @Test
    fun testSuccessfulAPIResponseForGetOrigins() = runBlocking {
        val testInfo = com.example.core.data.entity.location.Info(10, "nextPageUrl", 20, null)
        val testResult = com.example.core.data.entity.location.Result("created", "dimension",1,"name",listOf("resident1", "resident2"),"type","url")
        val testOriginResponse = OriginResponse(testInfo, arrayListOf(testResult))
        val expected = Resource.Success(testOriginResponse)
        coEvery { remoteDataSource.getOrigins() } returns expected
        val result = repository.getOrigins()
        assertEquals(expected, result)
    }

    @Test
    fun testFailedAPIResponseForGetOrigins() = runBlocking {
        val expected = Resource.Error<OriginResponse>("API Error")
        coEvery { remoteDataSource.getOrigins() } returns expected
        val result = repository.getOrigins()
        assertEquals(expected.errorMessage, result.errorMessage)
    }


}