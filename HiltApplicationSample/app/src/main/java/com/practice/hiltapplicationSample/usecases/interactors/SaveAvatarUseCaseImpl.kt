package com.practice.hiltapplicationSample.usecases.interactors

import com.practice.hiltapplicationSample.domains.entities.Avatar
import com.practice.hiltapplicationSample.domains.repositories.AvatarRepository
import com.practice.hiltapplicationSample.usecases.inputports.SaveAvatarUseCase
import javax.inject.Inject

class SaveAvatarUseCaseImpl @Inject constructor(
    private val repository: AvatarRepository
): SaveAvatarUseCase {
    override suspend fun invoke(avatar: Avatar): Result<Unit> {
       return repository.saveAvatar(avatar)
    }
}
