package com.practice.hiltapplicationSample.usecases.interactors

import com.practice.hiltapplicationSample.domains.entities.Avatar
import com.practice.hiltapplicationSample.domains.repositories.AvatarRepository
import com.practice.hiltapplicationSample.usecases.inputports.SaveAvatarUseCase
import javax.inject.Inject

class SaveAvatarUseCaseImpl @Inject constructor(
    private val repository: AvatarRepository
): SaveAvatarUseCase {
    override fun invoke(avatar: Avatar) {
        repository.saveAvatar(avatar)
    }
}
