package com.example.core.di.networking

import retrofit2.Response
import java.io.IOException

suspend fun <T> safeApiCall(call: suspend () -> Response<T>): Resource<T> {
    return try {
        val response = call.invoke()
        if (response.isSuccessful) {
            Resource.Success(response.body()!!)
        } else {
            Resource.Error(IOException("API call failed"))
        }
    } catch (e: Exception) {
        Resource.Error(e)
    }
}