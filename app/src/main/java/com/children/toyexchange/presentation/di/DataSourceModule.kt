package com.children.toyexchange.presentation.di

import com.children.toyexchange.data.api.KakaoAddressApi
import com.children.toyexchange.data.repository.datasource.RemoteFirebaseDataSource
import com.children.toyexchange.data.repository.datasource.RemotePostDataSource
import com.children.toyexchange.data.repository.datasource.RemoteSplashDataSource
import com.children.toyexchange.data.repository.datasource.RemoteToyUploadDataSource
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

    @Provides
    @Singleton
    fun provideRemotePostDataSource(
        firebaseAuth: FirebaseAuth,
        firebaseRtdb: FirebaseDatabase,
        firebaseStorage: FirebaseStorage,
        fireStore: FirebaseFirestore
    ) = RemotePostDataSource(firebaseAuth, firebaseRtdb, firebaseStorage,fireStore)

    @Provides
    @Singleton
    fun provideRemoteSplashDataSource(
        firebaseAuth: FirebaseAuth,
        firebaseRtdb: FirebaseDatabase,
        firebaseStorage: FirebaseStorage,
        fireStore: FirebaseFirestore
    ) = RemoteSplashDataSource(firebaseAuth, firebaseRtdb, firebaseStorage,fireStore)

    @Provides
    @Singleton
    fun provideRemoteToyUploadDataSource(
        addressApi : KakaoAddressApi,
        firebaseStorage: FirebaseStorage
    ) = RemoteToyUploadDataSource(addressApi,firebaseStorage)
}