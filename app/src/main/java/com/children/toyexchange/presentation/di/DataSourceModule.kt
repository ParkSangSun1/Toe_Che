package com.children.toyexchange.presentation.di

import com.children.toyexchange.data.repository.datasource.RemoteFirebaseDataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {
    @Provides
    @Singleton
    fun provideRemoteFirebaseDataSource(
        firebaseAuth: FirebaseAuth,
        firebaseRtdb: FirebaseDatabase,
        firebaseStorage: FirebaseStorage,
        fireStore: FirebaseFirestore
    ) = RemoteFirebaseDataSource(firebaseAuth, firebaseRtdb, firebaseStorage,fireStore)
}