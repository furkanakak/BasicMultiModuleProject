package com.example.core.di.networking.api_service

import com.example.core.data.entity.location.OriginResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiServiceOrigin {
    @GET("location")
    suspend fun getOrigins(): Response<OriginResponse>
}