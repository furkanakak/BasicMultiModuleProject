package com.example.core.data.repository

import com.example.core.data.remote.RemoteDataSource
import javax.inject.Inject

class Repository @Inject constructor(private val remoteDataSource: RemoteDataSource) {

    suspend fun getCharacters(page: Int) =  remoteDataSource.getCharacters(page)
    suspend fun getOrigins() =  remoteDataSource.getOrigins()

}