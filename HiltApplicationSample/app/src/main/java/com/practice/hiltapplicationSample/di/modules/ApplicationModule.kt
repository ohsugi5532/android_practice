package com.practice.hiltapplicationSample.di.modules

import com.practice.hiltapplicationSample.domains.repositories.AvatarRepository
import com.practice.hiltapplicationSample.infrastructures.repositories.AvatarRepositoryImpl
import com.practice.hiltapplicationSample.usecases.inputports.CreateAvatarUseCase
import com.practice.hiltapplicationSample.usecases.inputports.ListAvatarUseCase
import com.practice.hiltapplicationSample.usecases.inputports.SaveAvatarUseCase
import com.practice.hiltapplicationSample.usecases.interactors.CreateAvatarUseCaseImpl
import com.practice.hiltapplicationSample.usecases.interactors.ListAvatarUseCaseImpl
import com.practice.hiltapplicationSample.usecases.interactors.SaveAvatarUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {
    @Provides
    fun provideAvatarRepository(): AvatarRepository = AvatarRepositoryImpl()

    @Provides
    fun provideCreateAvatarUseCase(
        avatarRepository: AvatarRepository
    ): CreateAvatarUseCase = CreateAvatarUseCaseImpl(
        repository = avatarRepository
    )

    @Provides
    fun provideListAvatarUseCase(
        avatarRepository: AvatarRepository
    ): ListAvatarUseCase = ListAvatarUseCaseImpl(
        repository = avatarRepository
    )

    @Provides
    fun provideSaveAvatarUseCase(
        avatarRepository: AvatarRepository
    ): SaveAvatarUseCase = SaveAvatarUseCaseImpl(
        repository = avatarRepository
    )
}