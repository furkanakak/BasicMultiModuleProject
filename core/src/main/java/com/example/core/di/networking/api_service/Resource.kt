package com.example.core.di.networking.api_service
sealed class Resource<T>(
    val data: T? = null,
    val error: Throwable? = null
) {
    class Loading<T> : Resource<T>()
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(throwable: Throwable, data: T? = null) : Resource<T>(data, throwable)
}