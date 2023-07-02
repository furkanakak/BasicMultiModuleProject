package com.example.core.di.networking

import com.example.core.common.Constants
import com.example.core.di.networking.api_service.ApiServiceCharacter
import com.example.core.di.networking.api_service.ApiServiceOrigin
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(ActivityRetainedComponent::class)
class NetworkModule {

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }



    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }


    @Provides
    @Named("Character")
    fun provideRetrofitRickAndMorty(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_Rick_And_Morty)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Named("Origin")
    fun provideRetrofitMovie(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_Rick_And_Morty)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiServiceCharacter(@Named("Character") retrofit: Retrofit): ApiServiceCharacter {
        return retrofit.create(ApiServiceCharacter::class.java)
    }

    @Provides
    fun provideApiServiceMovie(@Named("Origin") retrofit: Retrofit): ApiServiceOrigin {
        return retrofit.create(ApiServiceOrigin::class.java)
    }


}