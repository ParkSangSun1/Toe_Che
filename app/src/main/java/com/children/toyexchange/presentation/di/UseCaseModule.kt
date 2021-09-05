package com.children.toyexchange.presentation.di

import com.children.toyexchange.domain.repository.FirebaseRepository
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
    fun provideCheckUserNickNameUseCase(repository: FirebaseRepository) = CheckUserNickNameUseCase(repository)

    @Provides
    @Singleton
    fun provideSaveUserInfoUseCase(repository: FirebaseRepository) = SaveUserInfoUseCase(repository)

    @Provides
    @Singleton
    fun provideSaveUserNickNameUseCase(repository: FirebaseRepository) = SaveUserNickNameUseCase(repository)

    @Provides
    @Singleton
    fun provideSaveUserProfileUseCase(repository: FirebaseRepository) = SaveUserProfileUseCase(repository)
}