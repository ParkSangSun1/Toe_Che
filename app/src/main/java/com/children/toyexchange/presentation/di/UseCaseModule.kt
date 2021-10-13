package com.children.toyexchange.presentation.di

import com.children.toyexchange.domain.repository.FirebaseRepository
import com.children.toyexchange.domain.repository.PostRepository
import com.children.toyexchange.domain.repository.SplashRepository
import com.children.toyexchange.domain.repository.ToyUploadRepository
import com.children.toyexchange.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideCallUserInfoUseCase(repository: FirebaseRepository) = CallUserInfoUseCase(repository)

    @Provides
    @Singleton
    fun provideCheckAppVersionUseCase(repository: SplashRepository) = CheckAppVersionUseCase(repository)

    @Provides
    @Singleton
    fun provideCheckUserNickNameUseCase(repository: FirebaseRepository) = CheckUserNickNameUseCase(repository)

    @Provides
    @Singleton
    fun provideGetStorePostUseCase(repository: PostRepository) = GetStorePostUseCase(repository)

    @Provides
    @Singleton
    fun provideGetToyCategoryUseCase(repository: FirebaseRepository) = GetToyCategoryUseCase(repository)

    @Provides
    @Singleton
    fun provideSavePostPhotoUseCase(repository: ToyUploadRepository) = SavePostPhotoUseCase(repository)

    @Provides
    @Singleton
    fun provideSaveUserInfoUseCase(repository: FirebaseRepository) = SaveUserInfoUseCase(repository)

    @Provides
    @Singleton
    fun provideSaveUserNickNameUseCase(repository: FirebaseRepository) = SaveUserNickNameUseCase(repository)

    @Provides
    @Singleton
    fun provideSaveUserProfileUseCase(repository: FirebaseRepository) = SaveUserProfileUseCase(repository)

    @Provides
    @Singleton
    fun provideSearchAddressUseCase(repository: ToyUploadRepository) = SearchAddressUseCase(repository)

    @Provides
    @Singleton
    fun provideToyUploadUseCase(repository: FirebaseRepository) = ToyUploadUseCase(repository)

}