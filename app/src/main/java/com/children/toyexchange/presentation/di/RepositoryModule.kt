package com.children.toyexchange.presentation.di

import com.children.toyexchange.data.repository.FirebaseRepositoryImpl
import com.children.toyexchange.data.repository.datasource.RemoteFirebaseDataSource
import com.children.toyexchange.domain.repository.FirebaseRepository
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
}