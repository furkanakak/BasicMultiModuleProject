package com.example.core.data.remote

import com.example.core.di.networking.api_service.ApiServiceCharacter
import com.example.core.di.networking.api_service.ApiServiceOrigin
import com.example.core.di.networking.safeApiCall
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiServiceCharacter: ApiServiceCharacter, private  val apiServiceOrigin: ApiServiceOrigin)  {
    suspend fun getCharacters(page: Int) = safeApiCall { apiServiceCharacter.getCharacters(page) }
    suspend fun getOrigins() = safeApiCall { apiServiceOrigin.getOrigins() }


}