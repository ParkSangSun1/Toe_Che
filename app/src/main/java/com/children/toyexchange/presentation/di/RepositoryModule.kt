package com.children.toyexchange.presentation.di

import com.children.toyexchange.data.repository.FirebaseRepositoryImpl
import com.children.toyexchange.data.repository.SplashRepositoryImpl
import com.children.toyexchange.data.repository.ToyUploadRepositoryImpl
import com.children.toyexchange.data.repository.datasource.RemoteFirebaseDataSource
import com.children.toyexchange.data.repository.datasource.RemoteSplashDataSource
import com.children.toyexchange.data.repository.datasource.RemoteToyUploadDataSource
import com.children.toyexchange.domain.repository.FirebaseRepository
import com.children.toyexchange.domain.repository.SplashRepository
import com.children.toyexchange.domain.repository.ToyUploadRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideFirebaseRepository(
        remoteFirebaseDataSource: RemoteFirebaseDataSource
    ): FirebaseRepository {
        return FirebaseRepositoryImpl(
            remoteFirebaseDataSource
        )
    }


    @Provides
    @Singleton
    fun provideToyUploadRepository(
        remoteToyUploadDataSource: RemoteToyUploadDataSource
    ): ToyUploadRepository {
        return ToyUploadRepositoryImpl(
            remoteToyUploadDataSource
        )
    }

    @Provides
    @Singleton
    fun provideSplashRepository(
        remoteSplashDataSource: RemoteSplashDataSource
    ): SplashRepository {
        return SplashRepositoryImpl(
            remoteSplashDataSource
        )
    }
}