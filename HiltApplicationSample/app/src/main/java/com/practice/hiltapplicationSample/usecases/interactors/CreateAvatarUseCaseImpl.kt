package com.practice.hiltapplicationSample.usecases.interactors

import com.practice.hiltapplicationSample.domains.entities.Avatar
import com.practice.hiltapplicationSample.domains.repositories.AvatarRepository
import com.practice.hiltapplicationSample.usecases.inputports.CreateAvatarUseCase
import javax.inject.Inject

class CreateAvatarUseCaseImpl @Inject constructor(
    private val repository: AvatarRepository
): CreateAvatarUseCase {
    override fun invoke(name: String): Avatar {
        return repository
            .createAvatar(name)
            .getOrThrow()
    }
}
