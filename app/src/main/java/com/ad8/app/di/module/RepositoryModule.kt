package com.ad8.app.di.module

import com.ad8.data.repository.ad8.AD8RemoteDataSource
import com.ad8.data.repository.ad8.AD8RepositoryImpl
import com.ad8.data.repository.stroopTest.StroopTestRemoteDataSource
import com.ad8.data.repository.stroopTest.StroopTestRepositoryImpl

import com.ad8.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {


    @Provides
    fun provideStroopTestRepository(remoteDataSource: StroopTestRemoteDataSource): StroopTestRepository =
        StroopTestRepositoryImpl(remoteDataSource)

    @Provides
    fun provideAD8Repository(remoteDataSource: AD8RemoteDataSource): AD8Repository =
        AD8RepositoryImpl(remoteDataSource)
}
