package com.example.core.di.networking.api_service

import com.example.core.data.entity.character.RickAndMortyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceCharacter {

    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): Response<RickAndMortyResponse>


}