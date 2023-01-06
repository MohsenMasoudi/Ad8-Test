package com.ad8.app.di.module

import com.ad8.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {
    @Provides
    fun sozerApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)


}